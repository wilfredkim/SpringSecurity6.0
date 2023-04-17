package com.wilfred.security.springsecurity.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequest {
    private String name;
    private String description;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private List<PrivilegeRequest> privilegeRequests;
}
