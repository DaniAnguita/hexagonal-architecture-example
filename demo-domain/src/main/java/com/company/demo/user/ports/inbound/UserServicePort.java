package com.company.demo.user.ports.inbound;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.company.demo.user.dto.AddUserRequestDto;
import com.company.demo.user.dto.AddUserResponseDto;
import com.company.demo.user.dto.GetUserRequestDto;
import com.company.demo.user.dto.GetUserResponseDto;
import com.company.demo.user.dto.GetUsersRequestDto;
import com.company.demo.user.dto.GetUsersResponseDto;

@Validated
public interface UserServicePort {
	GetUserResponseDto getUser(@Valid GetUserRequestDto request);
	AddUserResponseDto addUser(@Valid AddUserRequestDto signInRequest);
	GetUsersResponseDto getUsers(@Valid GetUsersRequestDto request);
}
