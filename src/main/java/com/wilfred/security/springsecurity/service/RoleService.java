package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Role;
import com.wilfred.security.springsecurity.payload.RoleRequest;

import java.util.List;

public interface RoleService {
    Role save(RoleRequest roleRequest);

    List<Role> getList();


}
