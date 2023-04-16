package com.wilfred.security.springsecurity.controller;

import com.wilfred.security.springsecurity.model.Privilege;
import com.wilfred.security.springsecurity.model.Role;
import com.wilfred.security.springsecurity.payload.PrivilegeRequest;
import com.wilfred.security.springsecurity.payload.RoleRequest;
import com.wilfred.security.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> savePrivileges(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.save(roleRequest));
    }
}
