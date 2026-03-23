package com.forum.service;

import com.forum.entity.Comment;
import com.forum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> findByAuthorId(Integer authorId) {
        return commentRepository.findByAuthorId(authorId);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }
}
