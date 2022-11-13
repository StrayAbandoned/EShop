package ru.lapshina.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapshina.entity.Role;
import ru.lapshina.repository.RoleRepository;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}