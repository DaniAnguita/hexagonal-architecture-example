package com.company.demo.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.demo.common.model.Email;
import com.company.demo.common.model.Pagination;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.User;
import com.company.demo.user.param.AddUserRequest;
import com.company.demo.user.param.AddUserResponse;
import com.company.demo.user.param.GetUserRequest;
import com.company.demo.user.param.GetUserResponse;
import com.company.demo.user.param.GetUsersRequest;
import com.company.demo.user.param.GetUsersResponse;
import com.company.demo.user.param.UserList;
import com.company.demo.user.ports.in.UserServicePort;
import com.company.demo.user.ports.out.UserPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServicePort {

	private final UserPersistencePort userPersistence;

	@Override
	public GetUserResponse getUser(GetUserRequest request) {
		User user = userPersistence.findById(request.getId()).orElseThrow(UserNotFoundException::new);
		
		return GetUserResponse.builder()
				.email(user.getEmail().getValue())
				.name(user.getName())
				.surname(user.getSurname())
				.status(user.getStatus())
				.build();
	}
	
	@Override
	public AddUserResponse addUser(AddUserRequest request) {
		User userDb = userPersistence.addUser(User.builder()
				.email(new Email(request.getEmail()))
				.name(request.getName())
				.surname(request.getSurname())
				.status(Status.ACTIVE)
				.build());
		
		return AddUserResponse.builder()
				.id(userDb.getId())
				.email(userDb.getEmail().getValue())
				.name(userDb.getName())
				.surname(userDb.getSurname())
				.status(userDb.getStatus())
				.build();
	}
	
	@Override
	public GetUsersResponse getUsers(GetUsersRequest request) {
		Pagination<UserList> usersPagination = userPersistence.findAll(Optional.ofNullable(request.getPageNumber()))
				.map(u -> UserList.builder()
						.id(u.getId())
						.email(u.getEmail().getValue())
						.build());
		
		return GetUsersResponse.builder().usersPagination(usersPagination).build();
	}
	
}
