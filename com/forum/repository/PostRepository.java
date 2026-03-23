package com.forum.repository;

import com.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
    List<Post> findByAuthorId(Integer authorId);
    
    @Query("SELECT p FROM Post p WHERE p.visibility IN :visibleRoles ORDER BY p.createTime DESC")
    List<Post> findByVisibilityIn(@Param("visibleRoles") List<String> visibleRoles);
    
    List<Post> findByVisibilityOrderByCreateTimeDesc(String visibility);
}