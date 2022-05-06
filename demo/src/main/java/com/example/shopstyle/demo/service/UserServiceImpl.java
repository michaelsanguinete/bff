package com.example.shopstyle.demo.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopstyle.demo.dto.UserDto;
import com.example.shopstyle.demo.dto.UserFormDto;
import com.example.shopstyle.demo.entity.User;
import com.example.shopstyle.demo.exception.EmailNotUniqueException;
import com.example.shopstyle.demo.exception.ResourceNotFoundException;
import com.example.shopstyle.demo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto create(UserFormDto body) {
		User user = modelMapper.map(body, User.class);
		String email = user.getEmail();

		if (isUniqueEmail(email)) {
			User savedUser = this.repository.save(user);
			return modelMapper.map(savedUser, UserDto.class);
		}

		throw new EmailNotUniqueException(email);
	}

	@Override
	public UserDto findById(Long id) {
		Optional<User> user = this.repository.findById(id);

		if (user.isPresent()) {
			return modelMapper.map(user.get(), UserDto.class);
		}

		throw new ResourceNotFoundException("user ID " + id);
	}

	@Override
	public UserDto update(Long id, UserFormDto body) {
		Optional<User> user = this.repository.findById(id);

		if (user.isPresent()) {
			User updatedUser = modelMapper.map(body, User.class);
			updatedUser.setId(user.get().getId());
			this.repository.save(updatedUser);

			return modelMapper.map(updatedUser, UserDto.class);
		}

		throw new ResourceNotFoundException("user ID " + id);
	}

	public Boolean isUniqueEmail(String email) {
		Optional<User> user = this.repository.findByEmail(email);

		return user.isEmpty();
	}
}
