package com.example.shopstyle.demo.service;

import com.example.shopstyle.demo.dto.UserDto;
import com.example.shopstyle.demo.dto.UserFormDto;

public interface UserService {
	
	UserDto create(UserFormDto UserFormDto);

    UserDto findById(Long id);

    UserDto update(Long id, UserFormDto UserFormDto);

}
