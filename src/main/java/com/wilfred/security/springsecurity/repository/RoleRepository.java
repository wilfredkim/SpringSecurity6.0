package com.wilfred.security.springsecurity.repository;

import com.wilfred.security.springsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
