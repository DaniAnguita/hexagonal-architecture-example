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
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.Surname;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;
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
				.name(new Name("nametest"))
				.surname(new Surname("surnametest"))
				.status(Status.ACTIVE)
				.build();
	}
	
	@UnitTest
	void testUserNotFound() {
		Mockito.when(userPersistence.findById(1l)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			userService.getUser(new UserId(1l));
		});
	}
	
	@UnitTest
	void testUserFound() {
		User user = createUser();
		Mockito.when(userPersistence.findById(1l)).thenReturn(Optional.of(user));
		
		User userResult = userService.getUser(new UserId(1l));
		
		assertThat(userResult.getEmail()).isEqualTo(user.getEmail());
		assertThat(userResult.getName()).isEqualTo(user.getName());
		assertThat(userResult.getSurname()).isEqualTo(user.getSurname());
		assertThat(userResult.getStatus()).isEqualTo(user.getStatus());
	}

}