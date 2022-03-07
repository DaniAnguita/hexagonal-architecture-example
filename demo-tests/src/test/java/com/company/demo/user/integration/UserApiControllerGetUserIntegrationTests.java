package com.company.demo.user.integration;

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
public class UserApiControllerGetUserIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@IntegrationTest
	void testUserLoggedActive() throws Exception {
		mockMvc.perform(get("/api/user/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email", is("user1@test.com")))
			.andExpect(jsonPath("$.name", is("Name 1")))
			.andExpect(jsonPath("$.surname", is("Surname 1")))
			.andExpect(jsonPath("$.status", is("ACTIVE")))
			.andExpect(jsonPath("$._links.self.href", is("http://localhost/api/user/1")));
	}
	
	@IntegrationTest
	void testUserLoggedDeactivated() throws Exception {
		mockMvc.perform(get("/api/user/2"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email", is("user2@test.com")))
			.andExpect(jsonPath("$.name", is("Name 2")))
			.andExpect(jsonPath("$.surname", is("Surname 2")))
			.andExpect(jsonPath("$.status", is("DEACTIVATED")))
			.andExpect(jsonPath("$._links.self.href", is("http://localhost/api/user/2")));
	}
	
	@IntegrationTest
	void testUserNotLogged() throws Exception {
		mockMvc.perform(get("/api/user/234364234"))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$").doesNotExist());
	}

	@IntegrationTest
	void testIdText() throws Exception {
		mockMvc.perform(get("/api/user/aaa"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$").doesNotExist());
	}

}
