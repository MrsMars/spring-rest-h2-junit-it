package com.aoher.service.impl;

import com.aoher.model.User;
import com.aoher.repository.UserRepository;
import com.aoher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return Optional.of(userRepository.save(user))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                    .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        return Optional.of(userRepository.findAll())
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User update(long id, User user) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            return this.save(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
