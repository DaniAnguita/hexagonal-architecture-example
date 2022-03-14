package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.company.demo.UnitTest;
import com.company.demo.common.model.Email;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.User;
import com.company.demo.user.param.GetUserRequest;
import com.company.demo.user.param.GetUserResponse;
import com.company.demo.user.ports.in.UserServicePort;
import com.company.demo.user.ports.out.UserPersistencePort;
import com.company.demo.user.service.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
		UserServiceImpl.class
	})
public class UserServiceImplGetUserUnitTests {
	
	@Autowired
	UserServicePort userService;

	@MockBean
	UserPersistencePort userPersistence;
	
	User createUser() {
		return User.builder()
				.email(new Email("test@test.com"))
				.name("nametest")
				.surname("surnametest")
				.status(Status.ACTIVE)
				.build();
	}
	
	@UnitTest
	void testUserNotFound() {
		Mockito.when(userPersistence.findById(1l)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			userService.getUser(new GetUserRequest(1l));
		});
	}
	
	@UnitTest
	void testUserFound() {
		User user = createUser();
		Mockito.when(userPersistence.findById(1l)).thenReturn(Optional.of(user));
		
		GetUserResponse result = userService.getUser(new GetUserRequest(1l));
		
		assertThat(result.getEmail()).isEqualTo(user.getEmail().getValue());
		assertThat(result.getName()).isEqualTo(user.getName());
		assertThat(result.getSurname()).isEqualTo(user.getSurname());
		assertThat(result.getStatus()).isEqualTo(user.getStatus());
	}

}