package com.forum.service;

import com.forum.entity.Permission;
import com.forum.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Cacheable(value = "permissions", key = "#id")
    public Optional<Permission> findById(Integer id) {
        return permissionRepository.findById(id);
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Cacheable(value = "permissions", key = "#role")
    public List<Permission> findByRole(String role) {
        return permissionRepository.findByRole(role);
    }

    @Cacheable(value = "permissions", key = "#role + '_' + #resourceType")
    public Optional<Permission> findByRoleAndResourceType(String role, String resourceType) {
        return permissionRepository.findByRoleAndResourceType(role, resourceType);
    }

    @CachePut(value = "permissions", key = "#result.id")
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @CacheEvict(value = "permissions", key = "#id")
    public void deleteById(Integer id) {
        permissionRepository.deleteById(id);
    }
}
