package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.RoleEntity;

@Repository
public interface RoleDao extends CrudRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByAuthority(String authority);
}
