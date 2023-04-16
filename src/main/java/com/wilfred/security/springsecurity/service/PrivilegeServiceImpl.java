package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Privilege;
import com.wilfred.security.springsecurity.payload.PrivilegeRequest;
import com.wilfred.security.springsecurity.repository.PrivilegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    @Override
    public Privilege save(PrivilegeRequest privilegeRequest) {
        Privilege privilege = privilegeRepository.findByName(privilegeRequest.getName());
        if (privilege == null) {
            privilege = new Privilege(privilegeRequest.getName());
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
}
