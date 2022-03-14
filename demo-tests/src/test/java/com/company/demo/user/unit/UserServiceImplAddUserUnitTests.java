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
import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.Surname;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;
import com.company.demo.user.param.AddUserRequest;
import com.company.demo.user.param.AddUserResponse;
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
	
	AddUserRequest createValidRequest() {
		return AddUserRequest.builder()
				.email("test@test.com")
				.name("name")
				.surname("surname")
				.build();
	}
	
	@UnitTest
	void testEmailAlreadyExists() {
		Mockito.when(userPersistence.addUser(Mockito.any(User.class))).thenThrow(new UserAlreadyExistsException());
		
		Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
			userService.addUser(createValidRequest());
		});
	}
	
	@UnitTest
	void testAddOk() {
		AddUserRequest request = createValidRequest();
		User user = User.builder()
				.id(new UserId(100l))
				.email(new Email(request.getEmail()))
				.name(new Name(request.getName()))
				.surname(new Surname(request.getSurname()))
				.status(Status.ACTIVE)
				.build();
		Mockito.when(userPersistence.addUser(Mockito.any(User.class))).thenReturn(user);
		
		AddUserResponse result = userService.addUser(request);
		
		assertThat(result.getId()).isEqualTo(user.getId().getValue());
		assertThat(result.getEmail()).isEqualTo(user.getEmail().getValue());
		assertThat(result.getName()).isEqualTo(user.getName().getValue());
		assertThat(result.getSurname()).isEqualTo(user.getSurname().getValue());
		assertThat(result.getStatus()).isEqualTo(user.getStatus());
	}

}
