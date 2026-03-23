package com.forum.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "permission", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"role", "resource_type"}, name = "uk_role_resource")
})
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Column(name = "resource_type", nullable = false, length = 20)
    private String resourceType;

    @Column(name = "operations", nullable = false, length = 50)
    private String operations;

    @Column(name = "visible_roles", nullable = false, length = 50)
    private String visibleRoles;

    @Column(name = "description", length = 200)
    private String description;

    public Permission() {
    }

    public Permission(Integer id, String role, String resourceType, String operations, String visibleRoles, String description) {
        this.id = id;
        this.role = role;
        this.resourceType = resourceType;
        this.operations = operations;
        this.visibleRoles = visibleRoles;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getVisibleRoles() {
        return visibleRoles;
    }

    public void setVisibleRoles(String visibleRoles) {
        this.visibleRoles = visibleRoles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) && Objects.equals(role, that.role) && Objects.equals(resourceType, that.resourceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, resourceType);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", operations='" + operations + '\'' +
                ", visibleRoles='" + visibleRoles + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}