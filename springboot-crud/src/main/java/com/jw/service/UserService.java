package com.jw.service;

import com.jw.bean.User;

public interface UserService {

    Iterable<User> findAll();

    void save(User user);

    void update(User user);

    void deleteById(Long id);

    User findById(Long id);

}
