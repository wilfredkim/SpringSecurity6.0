package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Privilege;
import com.wilfred.security.springsecurity.model.Role;
import com.wilfred.security.springsecurity.model.User;
import com.wilfred.security.springsecurity.payload.PrivilegeRequest;
import com.wilfred.security.springsecurity.payload.RoleRequest;
import com.wilfred.security.springsecurity.repository.RoleRepository;
import com.wilfred.security.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public final RoleRepository roleRepository;
    public final UserRepository userRepository;

    private final PrivilegeService privilegeService;

    @Override
    public Role save(RoleRequest roleRequest) {
        Role role = roleRepository.findByName(roleRequest.getName());
        if (role == null) {
            role = new Role(roleRequest.getName(), roleRequest.getDescription());
            List<Privilege> privilegeList = new ArrayList<>();
            if (roleRequest.getPrivilegeRequests()!=null) {
                for (PrivilegeRequest privilegeRequest : roleRequest.getPrivilegeRequests()) {
                    privilegeList.add(privilegeService.save(privilegeRequest));
                }
            }
            role.setPrivileges(privilegeList);
            if (roleRequest.getUserId() != null) {
                Optional<User> optionalUser = userRepository.findById(roleRequest.getUserId());
                if (optionalUser.isPresent()) {
                    role.setUsers(List.of(optionalUser.get()));
                }

            }

            roleRepository.save(role);
        }
        return role;
    }
}
