package com.company.demo.user.service;

import org.springframework.stereotype.Service;

import com.company.demo.common.model.PageNumber;
import com.company.demo.common.model.Pagination;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;
import com.company.demo.user.ports.in.UserServicePort;
import com.company.demo.user.ports.out.UserPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServicePort {

	private final UserPersistencePort userPersistence;

	@Override
	public User getUser(UserId id) {
		return userPersistence.findById(id.getValue()).orElseThrow(UserNotFoundException::new);
	}
	
	@Override
	public User addUser(User user) {
		user.validateUserCanBeAdded();
		
		return userPersistence.addUser(User.builder()
				.email(user.getEmail())
				.name(user.getName())
				.surname(user.getSurname())
				.status(Status.ACTIVE)
				.build());
	}
	
	@Override
	public Pagination<User> getUsers(PageNumber page) {
		return userPersistence.findAll(page);
	}
	
}
