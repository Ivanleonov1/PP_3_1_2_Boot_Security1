package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserService implements UserDetailsService {

    private final UserDao dao;

    public UserService(UserDao userDao) {
        dao = userDao;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findUserEntityByUsername(username);
    }

    @Transactional
    public void createOrUpdate(UserEntity user) {
        UserEntity old = dao.findUserEntityByUsername(user.getUsername());
        if (old != null) user.setId(old.getId());
        dao.save(user);
    }

    /***
     * Получить всех пользователей
     * @return список
     */
    public Collection<UserEntity> getList() {
        Collection<UserEntity> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    /***
     * Получить пользователя по ID
     * @param id ID пользователя
     * @return пользователь либо null
     */
    public UserEntity get(Long id) {
        return dao.findById(id).orElse(null);
    }

    public void delete(Long id) {
        dao.deleteById(id);
    }

}
