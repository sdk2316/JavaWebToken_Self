package com.durgesh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
 
}