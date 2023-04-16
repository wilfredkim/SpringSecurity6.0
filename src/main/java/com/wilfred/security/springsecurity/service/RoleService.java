package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Role;
import com.wilfred.security.springsecurity.payload.RoleRequest;

public interface RoleService {
    Role save(RoleRequest roleRequest);
}
