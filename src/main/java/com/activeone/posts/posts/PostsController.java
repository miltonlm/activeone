package com.activeone.posts.posts;

import com.activeone.posts.commentaries.CommentariesRequest;
import com.activeone.posts.commentaries.CommentariesResponse;
import com.activeone.posts.commentaries.CommentariesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    private final PostService postService;
    private final CommentariesService commentariesService;

    @Autowired
    public PostsController(PostService postService, CommentariesService commentariesService) {
        this.postService = postService;
        this.commentariesService = commentariesService;
    }

    @GetMapping
    public List<PostsResponse> all() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public PostsResponse get(@PathVariable Integer id) {
        return postService.getById(id);
    }

    @PostMapping
    public PostsResponse create(@RequestBody @Valid PostsRequest request) {
        return postService.create(request);
    }

    @PutMapping("/{id}")
    public PostsResponse update(@PathVariable Integer id, @RequestBody @Valid PostsRequest request) {
        return postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public PostsResponse delete(@PathVariable Integer id) {
        return postService.delete(id);
    }

    /**
     * Commentaries endpoints.
     * */

    @GetMapping("/{postId}/commentaries")
    public List<CommentariesResponse> all(@PathVariable Integer postId) {
        return commentariesService.getAll(postId);
    }

    @GetMapping("/{postId}/commentaries/{id}")
    public CommentariesResponse get(@PathVariable Integer postId,
                                    @PathVariable Integer id) {
        return commentariesService.getById(postId, id);
    }

    @PostMapping("/{postId}/commentaries")
    public CommentariesResponse create(@PathVariable Integer postId,
                                       @RequestBody @Valid CommentariesRequest request) {
        return commentariesService.create(postId, request);
    }

    @PutMapping("/{postId}/commentaries/{id}")
    public CommentariesResponse update(@PathVariable Integer postId,
                                       @PathVariable Integer id,
                                       @RequestBody @Valid CommentariesRequest request) {
        return commentariesService.update(postId, id, request);
    }

    @DeleteMapping("/{postId}/commentaries/{id}")
    public CommentariesResponse delete(@PathVariable Integer postId,
                                       @PathVariable Integer id) {
        return commentariesService.delete(postId, id);
    }
}
