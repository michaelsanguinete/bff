package com.example.shopstyle.demo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopstyle.demo.dto.UserDto;
import com.example.shopstyle.demo.dto.UserFormDto;
import com.example.shopstyle.demo.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserFormDto body) {
        UserDto customer = this.service.create(body);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> searchUser(@PathVariable Long id) {
        UserDto customer = this.service.findById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserFormDto body) {
        UserDto customer = this.service.update(id, body);
        return ResponseEntity.ok(customer);
    }

}
