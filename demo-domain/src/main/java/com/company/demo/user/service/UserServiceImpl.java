package com.company.demo.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.demo.common.model.Pagination;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.Surname;
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
		return GetUserResponse.of(user);
	}
	
	@Override
	public AddUserResponse addUser(AddUserRequest request) {
		User userDb = userPersistence.addUser(User.builder()
				.email(new Email(request.getEmail()))
				.name(new Name(request.getName()))
				.surname(new Surname(request.getSurname()))
				.status(Status.ACTIVE)
				.build());
		
		return AddUserResponse.of(userDb);
	}
	
	@Override
	public GetUsersResponse getUsers(GetUsersRequest request) {
		Pagination<UserList> usersPagination = userPersistence.findAll(Optional.ofNullable(request.getPageNumber()))
				.map(u -> UserList.builder()
						.id(u.getId().getValue())
						.email(u.getEmail().getValue())
						.build());
		
		return new GetUsersResponse(usersPagination);
	}
	
}
