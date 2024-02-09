package com.activeone.posts.posts;

import com.activeone.posts.exceptions.HttpNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final PostsRepository postsRepository;

    @Autowired
    public PostService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<PostsResponse> getAll() {
        return postsRepository.findAll()
                .stream()
                .map(postsEntity -> modelMapper.map(postsEntity, PostsResponse.class))
                .collect(Collectors.toList());
    }

    public PostsResponse getById(Integer id) {
        PostsEntity existingEntity = getEntity(id);

        return modelMapper.map(existingEntity, PostsResponse.class);
    }

    public PostsResponse create(PostsRequest request) {
        PostsEntity newEntity = modelMapper.map(request, PostsEntity.class);

        newEntity.setFecha_creacion(LocalDateTime.now());
        newEntity.setFecha_actualizacion(LocalDateTime.now());

        PostsEntity savedPost = this.postsRepository.save(newEntity);

        return modelMapper.map(savedPost, PostsResponse.class);
    }

    public PostsResponse update(Integer id, PostsRequest request) {
        PostsEntity existingPost = getEntity(id);

        modelMapper.map(request, existingPost);
        existingPost.setFecha_actualizacion(LocalDateTime.now());

        PostsEntity savedEntity = postsRepository.save(existingPost);

        return modelMapper.map(savedEntity, PostsResponse.class);
    }

    public PostsResponse delete(Integer id) {
        PostsEntity postsEntity = getEntity(id);

        postsRepository.deleteById(id);

        return modelMapper.map(postsEntity, PostsResponse.class);
    }

    private PostsEntity getEntity(Integer id) {
        Optional<PostsEntity> entityOptional = postsRepository.findById(id);

        if (entityOptional.isPresent()) {
            return entityOptional.get();
        }

        throw new HttpNotFoundException("Post with id %s was not found.".formatted(id));
    }
}
