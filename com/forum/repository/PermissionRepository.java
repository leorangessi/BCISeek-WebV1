package com.forum.repository;

import com.forum.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    
    List<Permission> findByRole(String role);
    
    Optional<Permission> findByRoleAndResourceType(String role, String resourceType);
}