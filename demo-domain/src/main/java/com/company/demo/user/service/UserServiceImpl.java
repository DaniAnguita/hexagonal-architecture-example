package com.company.demo.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.demo.common.data.Pagination;
import com.company.demo.user.data.Status;
import com.company.demo.user.data.User;
import com.company.demo.user.dto.AddUserRequestDto;
import com.company.demo.user.dto.AddUserResponseDto;
import com.company.demo.user.dto.GetUserRequestDto;
import com.company.demo.user.dto.GetUserResponseDto;
import com.company.demo.user.dto.GetUsersRequestDto;
import com.company.demo.user.dto.GetUsersResponseDto;
import com.company.demo.user.dto.UserListDto;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.ports.inbound.UserServicePort;
import com.company.demo.user.ports.outbound.UserPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServicePort {

	private final UserPersistencePort userPersistence;

	@Override
	public GetUserResponseDto getUser(GetUserRequestDto request) {
		User user = userPersistence.findById(request.getId()).orElseThrow(UserNotFoundException::new);
		
		return GetUserResponseDto.builder()
				.email(user.getEmail())
				.name(user.getName())
				.surname(user.getSurname())
				.status(user.getStatus())
				.build();
	}
	
	@Override
	public AddUserResponseDto addUser(AddUserRequestDto request) {
		User userDb = userPersistence.addUser(User.builder()
				.email(request.getEmail())
				.name(request.getName())
				.surname(request.getSurname())
				.status(Status.ACTIVE)
				.build());
		
		return AddUserResponseDto.builder()
				.id(userDb.getId())
				.email(userDb.getEmail())
				.name(userDb.getName())
				.surname(userDb.getSurname())
				.status(userDb.getStatus())
				.build();
	}
	
	@Override
	public GetUsersResponseDto getUsers(GetUsersRequestDto request) {
		Pagination<UserListDto> usersPagination = userPersistence.findAll(Optional.ofNullable(request.getPageNumber()))
				.map(u -> UserListDto.builder()
						.id(u.getId())
						.email(u.getEmail())
						.build());
		
		return GetUsersResponseDto.builder().usersPagination(usersPagination).build();
	}
	
}
