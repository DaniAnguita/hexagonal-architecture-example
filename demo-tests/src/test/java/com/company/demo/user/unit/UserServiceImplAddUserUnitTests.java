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
import com.company.demo.user.data.Status;
import com.company.demo.user.data.User;
import com.company.demo.user.dto.AddUserRequestDto;
import com.company.demo.user.dto.AddUserResponseDto;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.ports.inbound.UserServicePort;
import com.company.demo.user.ports.outbound.UserPersistencePort;
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
	
	AddUserRequestDto createValidRequest() {
		return AddUserRequestDto.builder()
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
		AddUserRequestDto request = createValidRequest();
		User user = User.builder()
				.id(100l)
				.email(request.getEmail())
				.name(request.getName())
				.surname(request.getSurname())
				.status(Status.ACTIVE)
				.build();
		Mockito.when(userPersistence.addUser(Mockito.any(User.class))).thenReturn(user);
		
		AddUserResponseDto result = userService.addUser(request);
		
		assertThat(result.getId()).isEqualTo(user.getId());
		assertThat(result.getEmail()).isEqualTo(user.getEmail());
		assertThat(result.getName()).isEqualTo(user.getName());
		assertThat(result.getSurname()).isEqualTo(user.getSurname());
		assertThat(result.getStatus()).isEqualTo(user.getStatus());
	}

}
