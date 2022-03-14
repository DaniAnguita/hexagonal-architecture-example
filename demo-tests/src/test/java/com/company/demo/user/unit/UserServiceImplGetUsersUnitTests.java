package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.company.demo.UnitTest;
import com.company.demo.common.model.Pagination;
import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.Surname;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;
import com.company.demo.user.param.GetUsersRequest;
import com.company.demo.user.param.GetUsersResponse;
import com.company.demo.user.param.UserList;
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
		Mockito.when(userPersistence.findAll(Optional.ofNullable(null))).thenReturn(createPaginationWithUsers());
		
		GetUsersResponse result = userService.getUsers(GetUsersRequest.builder().pageNumber(null).build());
		
		assertThat(result.getUsersPagination().getPageNumber()).isEqualTo(1);
		assertThat(result.getUsersPagination().getTotalElements()).isEqualTo(2);
		assertThat(result.getUsersPagination().getTotalPages()).isEqualTo(1);
		assertThat(result.getUsersPagination().getSize()).isEqualTo(15);
		assertThat(result.getUsersPagination().getContent().size()).isEqualTo(2);
	}
	
	@UnitTest
	void testUser1ExistsUsers() {
		Mockito.when(userPersistence.findAll(Optional.ofNullable(null))).thenReturn(createPaginationWithUsers());
		
		GetUsersResponse result = userService.getUsers(GetUsersRequest.builder().pageNumber(null).build());
		
		User userRequest1 = createUserRequest1();
		UserList userResponse1 = result.getUsersPagination().getContent().get(0);
		assertThat(userResponse1.getEmail()).isEqualTo(userRequest1.getEmail().getValue());
		assertThat(userResponse1.getId()).isEqualTo(userRequest1.getId().getValue());
	}
	
	@UnitTest
	void testUser2ExistsUsers() {
		Mockito.when(userPersistence.findAll(Optional.ofNullable(null))).thenReturn(createPaginationWithUsers());
		
		GetUsersResponse result = userService.getUsers(GetUsersRequest.builder().pageNumber(null).build());
		
		User userRequest2 = createUserRequest2();
		UserList userResponse2 = result.getUsersPagination().getContent().get(1);
		assertThat(userResponse2.getEmail()).isEqualTo(userRequest2.getEmail().getValue());
		assertThat(userResponse2.getId()).isEqualTo(userRequest2.getId().getValue());
	}
	
	@UnitTest
	void testNotExistsUsers() {
		Mockito.when(userPersistence.findAll(Optional.ofNullable(null))).thenReturn(createPaginationWithoutUsers());
		
		GetUsersResponse result = userService.getUsers(GetUsersRequest.builder().pageNumber(null).build());
		
		assertThat(result.getUsersPagination().getPageNumber()).isEqualTo(1);
		assertThat(result.getUsersPagination().getTotalElements()).isEqualTo(0);
		assertThat(result.getUsersPagination().getTotalPages()).isEqualTo(1);
		assertThat(result.getUsersPagination().getSize()).isEqualTo(15);
		assertThat(result.getUsersPagination().getContent().size()).isEqualTo(0);
	}
}
