package com.forum.controller;

import com.forum.entity.Comment;
import com.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Comment>> getCommentsByAuthor(@PathVariable Integer authorId) {
        return ResponseEntity.ok(commentService.findByAuthorId(authorId));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        if (!commentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comment.setId(id);
        return ResponseEntity.ok(commentService.save(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        if (!commentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
