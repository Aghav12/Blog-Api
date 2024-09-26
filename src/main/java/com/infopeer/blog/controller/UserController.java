package com.infopeer.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infopeer.blog.constant.ApiConstants;
import com.infopeer.blog.constant.PathConstants;
import com.infopeer.blog.payload.ApiResponse;
import com.infopeer.blog.payload.UserDto;
import com.infopeer.blog.service.UserService;

/**
 * @author Aghav Narayan
 * 
 */
@RestController
@RequestMapping(PathConstants.USER_PATH)

public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @apiNote To Create a new User in database
	 * @param userDto
	 * @return
	 */
	@PostMapping(PathConstants.CREATE_CATEGORY)
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto creatuserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(creatuserDto, HttpStatus.CREATED);

	}

	/**
	 * @apiNote To Update User By Id in database
	 * @param userDto
	 * @param uId
	 * @return
	 */
	@PutMapping(PathConstants.UPDATE_USER)
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uId) {
		UserDto updateUser = this.userService.updateUser(userDto, uId);

		return ResponseEntity.ok(updateUser);

	}

	/**
	 * @apiNote To Delete User By Id in database
	 * @param uId
	 * @return
	 */
	@DeleteMapping(PathConstants.DELETE_USER_BY_ID)
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId) {
		this.deleteUser(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstants.USER_DELETED, true), HttpStatus.OK);

	}

	/**
	 * @apiNote To Get All User
	 * @return
	 */
	@GetMapping(PathConstants.GET_ALL_USER)
	public ResponseEntity<List<UserDto>> getAllUser() {

		return ResponseEntity.ok(this.userService.getAllUser());

	}

	/**
	 * @apiNote To Get User by userId from database
	 * @param userId
	 * @return
	 */
	@GetMapping(PathConstants.GET_USER_BY_ID)
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));

	}

}
