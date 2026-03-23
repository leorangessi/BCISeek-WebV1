package com.forum.controller;

import com.forum.entity.Permission;
import com.forum.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "*")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Integer id) {
        return permissionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Permission>> getPermissionsByRole(@PathVariable String role) {
        return ResponseEntity.ok(permissionService.findByRole(role));
    }

    @GetMapping("/role/{role}/resource/{resourceType}")
    public ResponseEntity<Permission> getPermissionByRoleAndResource(
            @PathVariable String role,
            @PathVariable String resourceType) {
        return permissionService.findByRoleAndResourceType(role, resourceType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission savedPermission = permissionService.save(permission);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPermission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Integer id, @RequestBody Permission permission) {
        if (!permissionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        permission.setId(id);
        return ResponseEntity.ok(permissionService.save(permission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Integer id) {
        if (!permissionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        permissionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
