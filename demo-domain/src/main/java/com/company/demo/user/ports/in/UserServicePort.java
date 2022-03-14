package com.company.demo.user.ports.in;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.company.demo.user.param.AddUserRequest;
import com.company.demo.user.param.AddUserResponse;
import com.company.demo.user.param.GetUserRequest;
import com.company.demo.user.param.GetUserResponse;
import com.company.demo.user.param.GetUsersRequest;
import com.company.demo.user.param.GetUsersResponse;

@Validated
public interface UserServicePort {
	GetUserResponse getUser(@Valid GetUserRequest request);
	AddUserResponse addUser(@Valid AddUserRequest signInRequest);
	GetUsersResponse getUsers(@Valid GetUsersRequest request);
}
