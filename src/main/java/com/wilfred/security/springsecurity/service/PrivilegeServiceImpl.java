package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Privilege;
import com.wilfred.security.springsecurity.payload.PrivilegeRequest;
import com.wilfred.security.springsecurity.repository.PrivilegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    @Override
    public Privilege save(PrivilegeRequest privilegeRequest) {
        Privilege privilege = privilegeRepository.findByName(privilegeRequest.getName());

        if (privilege == null) {
            privilege = new Privilege(privilegeRequest.getName());
            privilege.setDateCreated(LocalDateTime.now());
            privilege.setDateUpdate(LocalDateTime.now());
        } else {
            privilege.setDateUpdate(LocalDateTime.now());

        }
        privilegeRepository.save(privilege);
        return privilege;
    }

    @Override
    public List<Privilege> getList() {
        return privilegeRepository.findAll();
    }
}
