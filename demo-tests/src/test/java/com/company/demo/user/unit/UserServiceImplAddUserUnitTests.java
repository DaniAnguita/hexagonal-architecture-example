package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.company.demo.UnitTest;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.exception.UserValidationCanBeAddedException;
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
public class UserServiceImplAddUserUnitTests {
	
	@Autowired
	UserServicePort userService;

	@MockBean
	UserPersistencePort userPersistence;
	
	User createValidUser() {
		return User.builder()
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.build();
	}
	
	@UnitTest
	void testEmailAlreadyExists() {
		Mockito.when(userPersistence.addUser(Mockito.any(User.class))).thenThrow(new UserAlreadyExistsException());
		
		Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
			userService.addUser(createValidUser());
		});
	}
	
	@UnitTest
	void testAddOk() {
		User user = User.builder()
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.build();
		Mockito.when(userPersistence.addUser(Mockito.any(User.class))).thenReturn(user);
		
		User userResult = userService.addUser(user);
		
		assertThat(userResult.getId()).isEqualTo(user.getId());
		assertThat(userResult.getEmail()).isEqualTo(user.getEmail());
		assertThat(userResult.getName()).isEqualTo(user.getName());
		assertThat(userResult.getSurname()).isEqualTo(user.getSurname());
		assertThat(userResult.getStatus()).isEqualTo(user.getStatus());
	}
	
	@UnitTest
	void testAddKoIdNotNull() {
		User user = User.builder()
				.id(new UserId(1l))
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.build();
		
		Assertions.assertThrows(UserValidationCanBeAddedException.class, () -> {
			userService.addUser(user);
		});
	}

	
	@UnitTest
	void testAddKoStatusActive() {
		User user = User.builder()
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.status(Status.ACTIVE)
				.build();
		
		Assertions.assertThrows(UserValidationCanBeAddedException.class, () -> {
			userService.addUser(user);
		});
	}

	
	@UnitTest
	void testAddKoStatusDeactivated() {
		User user = User.builder()
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.status(Status.DEACTIVATED)
				.build();
		
		Assertions.assertThrows(UserValidationCanBeAddedException.class, () -> {
			userService.addUser(user);
		});
	}
}
