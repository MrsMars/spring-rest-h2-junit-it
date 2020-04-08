package com.aoher.service;

import com.aoher.model.User;

import java.util.List;

public interface UserService {

    User save(final User user);
    User findById(final Long id);
    List<User> findAll();
    User update(final long id, final User user);
    void deleteById(final long id);

}
