package com.infopeer.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infopeer.blog.exception.ResourceNotFoundException;
import com.infopeer.blog.model.User;
import com.infopeer.blog.payload.UserDto;
import com.infopeer.blog.repositories.UserRepo;
import com.infopeer.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtotoUser(userDto);
		User saveduser = this.userRepo.save(user);
		return this.usertoDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User update = this.userRepo.save(user);
		UserDto usertoDto = this.usertoDto(update);
		return usertoDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDto = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		this.userRepo.delete(user);

	}

	private User dtotoUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		return user;

	}

	public UserDto usertoDto(User user) {
		UserDto userD = this.modelMapper.map(user, UserDto.class);

		return userD;

	}

}
