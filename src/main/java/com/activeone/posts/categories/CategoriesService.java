package com.activeone.posts.categories;

import com.activeone.posts.exceptions.HttpNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<CategoriesResponse> getAll() {
        return categoriesRepository.findAll()
                .stream()
                .map(categoriesEntity -> modelMapper.map(categoriesEntity, CategoriesResponse.class))
                .collect(Collectors.toList());
    }

    public CategoriesResponse get(Integer id) {
        CategoriesEntity existingEntity = getEntity(id);

        return modelMapper.map(existingEntity, CategoriesResponse.class);
    }

    public CategoriesResponse create(CategoriesRequest request) {
        CategoriesEntity newEntity = modelMapper.map(request, CategoriesEntity.class);

        newEntity.setFecha_creacion(LocalDateTime.now());
        newEntity.setFecha_actualizacion(LocalDateTime.now());

        CategoriesEntity savedPost = this.categoriesRepository.save(newEntity);

        return modelMapper.map(savedPost, CategoriesResponse.class);
    }

    public CategoriesResponse update(Integer id, CategoriesRequest request) {
        CategoriesEntity existingEntity = getEntity(id);

        modelMapper.map(request, existingEntity);
        existingEntity.setFecha_actualizacion(LocalDateTime.now());

        CategoriesEntity savedCategory = this.categoriesRepository.save(existingEntity);

        return modelMapper.map(savedCategory, CategoriesResponse.class);
    }

    public CategoriesResponse delete(Integer id) {
        CategoriesEntity categoriesEntity = getEntity(id);

        categoriesRepository.deleteById(id);

        return modelMapper.map(categoriesEntity, CategoriesResponse.class);
    }

    private CategoriesEntity getEntity(Integer id) {
        Optional<CategoriesEntity> postEntityOptional = categoriesRepository.findById(id);

        if (postEntityOptional.isPresent()) {
            return postEntityOptional.get();
        }

        throw new HttpNotFoundException("Category with id %s was not found.".formatted(id));
    }
}
