package com.aoher.controller;

import com.aoher.model.User;
import com.aoher.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = {"users"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("enregistrer un nouvel utilisateur ")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/id")
    @ApiOperation("Obtenir l'utilisateur par ID")
    public User findById(@PathVariable final Long id) {
        return userService.findById(id);
    }

    @GetMapping
    @ApiOperation("obtenir la liste des utilisateurs ")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    @ApiOperation("Màj des infos d'un utilisateur identifié par ID")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("supprimer un utilisateur identifié par ID")
    public void deleteById(@PathVariable final Long id) {
        userService.deleteById(id);
    }

}
