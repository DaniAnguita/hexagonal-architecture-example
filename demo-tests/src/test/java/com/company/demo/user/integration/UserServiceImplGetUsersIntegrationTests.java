package com.company.demo.user.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.company.demo.IntegrationTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceImplGetUsersIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	
	void testPage0(String url) throws Exception {
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pagination.number", is(1)))
				.andExpect(jsonPath("$.pagination.size", is(15)))
				.andExpect(jsonPath("$.pagination.totalElements", is(16)))
				.andExpect(jsonPath("$.pagination.totalPages", is(2)))
				.andExpect(jsonPath("$._embedded.users", hasSize(15)))
				.andExpect(jsonPath("$._embedded.users[0].id", is(1)))
				.andExpect(jsonPath("$._embedded.users[0].email", is("user1@test.com")))
				.andExpect(jsonPath("$._embedded.users[0]._links.self.href", is("http://localhost/api/user/1")))
				.andExpect(jsonPath("$._embedded.users[1].id", is(2)))
				.andExpect(jsonPath("$._embedded.users[1].email", is("user2@test.com")))
				.andExpect(jsonPath("$._embedded.users[1]._links.self.href", is("http://localhost/api/user/2")));
	}
	
	void testPage1(String url) throws Exception {
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pagination.number", is(1)))
				.andExpect(jsonPath("$.pagination.size", is(15)))
				.andExpect(jsonPath("$.pagination.totalElements", is(16)))
				.andExpect(jsonPath("$.pagination.totalPages", is(2)))
				.andExpect(jsonPath("$._embedded.users", hasSize(15)))
				.andExpect(jsonPath("$._embedded.users[0].id", is(1)))
				.andExpect(jsonPath("$._embedded.users[0].email", is("user1@test.com")))
				.andExpect(jsonPath("$._embedded.users[0]._links.self.href", is("http://localhost/api/user/1")))
				.andExpect(jsonPath("$._embedded.users[1].id", is(2)))
				.andExpect(jsonPath("$._embedded.users[1].email", is("user2@test.com")))
				.andExpect(jsonPath("$._embedded.users[1]._links.self.href", is("http://localhost/api/user/2")));
	}
	
	void testPage2(String url) throws Exception {
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pagination.number", is(2)))
				.andExpect(jsonPath("$.pagination.size", is(15)))
				.andExpect(jsonPath("$.pagination.totalElements", is(16)))
				.andExpect(jsonPath("$.pagination.totalPages", is(2)))
				.andExpect(jsonPath("$._embedded.users", hasSize(1)))
				.andExpect(jsonPath("$._embedded.users[0].id", is(16)))
				.andExpect(jsonPath("$._embedded.users[0].email", is("user16@test.com")))
				.andExpect(jsonPath("$._embedded.users[0]._links.self.href", is("http://localhost/api/user/16")));
	}
	
	@IntegrationTest
	void testWithoutPage() throws Exception {
		testPage1("/api/user");
	}
	
	@IntegrationTest
	void testWithPage0() throws Exception {
		mockMvc.perform(get("/api/user?page=0"))
			.andExpect(status().isBadRequest());
	}
	
	@IntegrationTest
	void testWithPage1() throws Exception {
		testPage1("/api/user?page=1");
	}
	
	@IntegrationTest
	void testWithPage2() throws Exception {
		testPage2("/api/user?page=2");
	}

}
