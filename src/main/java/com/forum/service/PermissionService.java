package com.forum.service;

import com.forum.entity.Permission;
import com.forum.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Optional<Permission> findById(Integer id) {
        return permissionRepository.findById(id);
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public List<Permission> findByRole(String role) {
        return permissionRepository.findByRole(role);
    }

    public Optional<Permission> findByRoleAndResourceType(String role, String resourceType) {
        return permissionRepository.findByRoleAndResourceType(role, resourceType);
    }

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public void deleteById(Integer id) {
        permissionRepository.deleteById(id);
    }
}
