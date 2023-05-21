package com.wilfred.security.springsecurity.controller;

import com.wilfred.security.springsecurity.model.Privilege;
import com.wilfred.security.springsecurity.payload.AuthenticationResponse;
import com.wilfred.security.springsecurity.payload.PrivilegeRequest;
import com.wilfred.security.springsecurity.payload.UserRequest;
import com.wilfred.security.springsecurity.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/privileges")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    @PostMapping
    public ResponseEntity<Privilege> savePrivileges(@RequestBody PrivilegeRequest privilegeRequest) {
        return ResponseEntity.ok(privilegeService.save(privilegeRequest));
    }

    @GetMapping
    public ResponseEntity<List<Privilege>> getList() {
        return ResponseEntity.ok(privilegeService.getList());
    }
}
