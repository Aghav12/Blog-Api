package com.infopeer.blog.service;

import java.util.List;
import com.infopeer.blog.payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Integer userId);

	UserDto getUserById(Integer userId);

	void deleteUser(Integer userId);

	List<UserDto> getAllUser();

}
