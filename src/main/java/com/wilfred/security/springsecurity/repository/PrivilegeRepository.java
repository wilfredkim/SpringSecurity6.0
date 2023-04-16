package com.wilfred.security.springsecurity.repository;

import com.wilfred.security.springsecurity.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
