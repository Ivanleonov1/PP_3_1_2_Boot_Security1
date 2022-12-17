package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.RoleEntity;
import ru.kata.spring.boot_security.demo.repositories.RoleDao;

@Service
public class RoleService {

    private final RoleDao dao;

    public RoleService(RoleDao roleDao) {
        dao = roleDao;
    }

    public RoleEntity createRole(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        RoleEntity entity = dao.findRoleEntityByAuthority(role);
        if (entity == null) {
            entity = dao.save(new RoleEntity(role));
        }
        return entity;
    }
}
