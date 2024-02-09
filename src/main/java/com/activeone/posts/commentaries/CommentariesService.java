package com.activeone.posts.commentaries;

import com.activeone.posts.exceptions.HttpNotFoundException;
import com.activeone.posts.posts.PostsEntity;
import com.activeone.posts.posts.PostsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentariesService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CommentariesRepository commentariesRepository;
    private final PostsRepository postsRepository;

    @Autowired
    public CommentariesService(CommentariesRepository commentariesRepository,
                               PostsRepository postsRepository) {
        this.commentariesRepository = commentariesRepository;
        this.postsRepository = postsRepository;
    }

    public List<CommentariesResponse> getAll(Integer postId) {
        validatePostId(postId);

        return commentariesRepository.findAll()
                .stream()
                .map(commentariesEntity -> modelMapper.map(commentariesEntity, CommentariesResponse.class))
                .collect(Collectors.toList());
    }

    public CommentariesResponse getById(Integer postId, Integer id) {
        CommentariesEntity existingEntity = getEntity(postId, id);

        return modelMapper.map(existingEntity, CommentariesResponse.class);
    }

    public CommentariesResponse create(Integer postId, CommentariesRequest request) {
        validatePostId(postId);

        CommentariesEntity newEntity = modelMapper.map(request, CommentariesEntity.class);

        newEntity.setPostsId(postId);
        newEntity.setFecha_creacion(LocalDateTime.now());
        newEntity.setFecha_actualizacion(LocalDateTime.now());

        CommentariesEntity savedPost = this.commentariesRepository.save(newEntity);

        return modelMapper.map(savedPost, CommentariesResponse.class);
    }

    public CommentariesResponse update(Integer postId, Integer id, CommentariesRequest request) {
        CommentariesEntity existingEntity = getEntity(postId, id);

        modelMapper.map(request, existingEntity);
        existingEntity.setFecha_actualizacion(LocalDateTime.now());

        CommentariesEntity save = commentariesRepository.save(existingEntity);

        return modelMapper.map(save, CommentariesResponse.class);
    }

    public CommentariesResponse delete(Integer postId, Integer id) {
        CommentariesEntity commentariesEntity = getEntity(postId, id);

        commentariesRepository.deleteById(id);

        return modelMapper.map(commentariesEntity, CommentariesResponse.class);
    }

    private CommentariesEntity getEntity(Integer postId, Integer id) {
        validatePostId(postId);

        Optional<CommentariesEntity> entityOptional = commentariesRepository.findById(id);

        if (entityOptional.isPresent()) {
            return entityOptional.get();
        }

        throw new HttpNotFoundException("Commentary with id %s was not found.".formatted(id));
    }

    private void validatePostId(Integer postId) {
        Optional<PostsEntity> entityOptional = postsRepository.findById(postId);

        if (entityOptional.isEmpty()) {
            throw new HttpNotFoundException("Post with id %s was not found.".formatted(postId));
        }
    }
}
