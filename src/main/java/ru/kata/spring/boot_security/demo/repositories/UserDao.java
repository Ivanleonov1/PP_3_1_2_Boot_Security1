package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.UserEntity;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);
}
