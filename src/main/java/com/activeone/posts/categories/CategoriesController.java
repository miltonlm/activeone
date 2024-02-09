package com.activeone.posts.categories;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<CategoriesResponse> all() {
        return categoriesService.getAll();
    }

    @GetMapping("/{id}")
    public CategoriesResponse get(@PathVariable Integer id) {
        return categoriesService.get(id);
    }

    @PostMapping
    public CategoriesResponse create(@RequestBody @Valid CategoriesRequest request) {
        return categoriesService.create(request);
    }

    @PutMapping("/{id}")
    public CategoriesResponse update(@PathVariable Integer id, @RequestBody @Valid CategoriesRequest request) {
        return categoriesService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public CategoriesResponse delete(@PathVariable Integer id) {
        return categoriesService.delete(id);
    }
}
