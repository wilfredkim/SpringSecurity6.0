package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Privilege;
import com.wilfred.security.springsecurity.payload.PrivilegeRequest;

public interface PrivilegeService {
    Privilege save(PrivilegeRequest privilegeRequest);
}
