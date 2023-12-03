package com.kanban.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kanban.backend.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
