package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.company.demo.UnitTest;
import com.company.demo.common.model.PageNumber;
import com.company.demo.common.model.Pagination;
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
public class UserServiceImplGetUsersUnitTests {
	
	@Autowired
	UserServicePort userService;

	@MockBean
	UserPersistencePort userPersistence;
	
	private User createUserRequest1() {
		return User.builder()
				.id(new UserId(1l))
				.email(new Email("test1@test.com"))
				.name(new Name("name1"))
				.surname(new Surname("surname1"))
				.status(Status.ACTIVE)
				.build();
	}
	
	private User createUserRequest2() {
		return User.builder()
				.id(new UserId(2l))
				.email(new Email("test2@test.com"))
				.name(new Name("name2"))
				.surname(new Surname("surname2"))
				.status(Status.DEACTIVATED)
				.build();
	}
	
	private Pagination<User> createPaginationWithUsers() {
		return Pagination.<User>builder()
				.pageNumber(1)
				.totalElements(2)
				.totalPages(1)
				.size(15)
				.content(List.of(createUserRequest1(), createUserRequest2()))
				.build();
	}
	
	private Pagination<User> createPaginationWithoutUsers() {
		return Pagination.<User>builder()
				.pageNumber(1)
				.totalElements(0)
				.totalPages(1)
				.size(15)
				.content(Collections.emptyList())
				.build();
	}
	
	@UnitTest
	void testPaginationExistsUsers() {
		PageNumber pageNumber = new PageNumber(1);
		Mockito.when(userPersistence.findAll(pageNumber)).thenReturn(createPaginationWithUsers());
		
		Pagination<User> result = userService.getUsers(pageNumber);
		
		assertThat(result.getPageNumber()).isEqualTo(1);
		assertThat(result.getTotalElements()).isEqualTo(2);
		assertThat(result.getTotalPages()).isEqualTo(1);
		assertThat(result.getSize()).isEqualTo(15);
		assertThat(result.getContent().size()).isEqualTo(2);
	}
	
	@UnitTest
	void testUser1ExistsUsers() {
		PageNumber pageNumber = new PageNumber(1);
		Mockito.when(userPersistence.findAll(pageNumber)).thenReturn(createPaginationWithUsers());
		
		Pagination<User> result = userService.getUsers(pageNumber);
		
		User userRequest1 = createUserRequest1();
		User userResponse1 = result.getContent().get(0);
		assertThat(userResponse1).isEqualTo(userRequest1);
	}
	
	@UnitTest
	void testUser2ExistsUsers() {
		PageNumber pageNumber = new PageNumber(1);
		Mockito.when(userPersistence.findAll(pageNumber)).thenReturn(createPaginationWithUsers());
		
		Pagination<User> result = userService.getUsers(pageNumber);
		
		User userRequest2 = createUserRequest2();
		User userResponse2 = result.getContent().get(1);
		assertThat(userResponse2).isEqualTo(userRequest2);
	}
	
	@UnitTest
	void testNotExistsUsers() {
		PageNumber pageNumber = new PageNumber(1);
		Mockito.when(userPersistence.findAll(pageNumber)).thenReturn(createPaginationWithoutUsers());
		
		Pagination<User> result = userService.getUsers(pageNumber);
		
		assertThat(result.getPageNumber()).isEqualTo(1);
		assertThat(result.getTotalElements()).isEqualTo(0);
		assertThat(result.getTotalPages()).isEqualTo(1);
		assertThat(result.getSize()).isEqualTo(15);
		assertThat(result.getContent().size()).isEqualTo(0);
	}
}
