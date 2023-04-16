package com.wilfred.security.springsecurity.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequest {
    private String name;
    private String description;
    private Long userId;
    private List<PrivilegeRequest> privilegeRequests;
}
