package com.forum.controller;

import com.forum.entity.Post;
import com.forum.entity.User;
import com.forum.service.PostService;
import com.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Post>> getPostsByAuthor(@PathVariable Integer authorId) {
        return ResponseEntity.ok(postService.findByAuthorId(authorId));
    }

    @GetMapping("/visibility/{visibility}")
    public ResponseEntity<List<Post>> getPostsByVisibility(@PathVariable String visibility) {
        return ResponseEntity.ok(postService.findByVisibility(visibility));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody com.forum.dto.PostCreateRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setVisibility(request.getVisibility());
        
        // 如果提供了作者ID，从数据库加载完整的User对象
        if (request.getAuthorId() != null) {
            User author = userService.findById(request.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("作者不存在"));
            post.setAuthor(author);
        }
        Post savedPost = postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody com.forum.dto.PostUpdateRequest request) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setVisibility(request.getVisibility());
        return ResponseEntity.ok(postService.save(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        if (!postService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
