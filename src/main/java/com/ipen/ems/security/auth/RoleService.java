package com.ipen.ems.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findOrCreateRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }
}
