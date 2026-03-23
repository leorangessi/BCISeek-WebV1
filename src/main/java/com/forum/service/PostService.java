package com.forum.service;

import com.forum.entity.Post;
import com.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findByAuthorId(Integer authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public List<Post> findByVisibilityIn(List<String> visibleRoles) {
        return postRepository.findByVisibilityIn(visibleRoles);
    }

    public List<Post> findByVisibility(String visibility) {
        return postRepository.findByVisibilityOrderByCreateTimeDesc(visibility);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }
}
