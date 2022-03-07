package com.company.demo.user.integration;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.company.demo.IntegrationTest;
import com.company.demo.user.dto.AddUserRequestDto;
import com.company.demo.user.jpa.UserJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserApiControllerAddUserIntegrationTests {
	
	private static final String USER_STATUS_ACTIVE = "ACTIVE";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserJpaRepository userRepository;
	
	@BeforeEach
	void beforeEach() {
		userRepository.deleteByEmail(createValidRequest().getEmail());
	}
	
	ResultActions addUserApi(AddUserRequestDto request) throws Exception {
		return mockMvc.perform(
				post("/api/user")
				.content(new ObjectMapper().writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
			);
	}

	public AddUserRequestDto createValidRequest() {
		return AddUserRequestDto.builder()
			.email("test@test.com")
			.name("name")
			.surname("surname")
			.build();
	}
	
	@IntegrationTest
	void testAddUserOkAndCheckResponse() throws Exception {
		AddUserRequestDto request = createValidRequest();
		
		addUserApi(request)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.errors").doesNotExist())
			.andExpect(jsonPath("$.id", is(greaterThan(0))))
			.andExpect(jsonPath("$.email", is(request.getEmail())))
			.andExpect(jsonPath("$.name", is(request.getName())))
			.andExpect(jsonPath("$.surname", is(request.getSurname())))
			.andExpect(jsonPath("$.status", is(USER_STATUS_ACTIVE)));
	}
	
	@IntegrationTest
	void testAddUserOkAndCheckUserSaved() throws Exception {
		AddUserRequestDto request = createValidRequest();
		MvcResult result = addUserApi(request).andReturn();
		int id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
		
		mockMvc.perform(get("/api/user/" + id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email", is(request.getEmail())))
			.andExpect(jsonPath("$.name", is(request.getName())))
			.andExpect(jsonPath("$.surname", is(request.getSurname())))
			.andExpect(jsonPath("$.status", is(USER_STATUS_ACTIVE)))
			.andExpect(jsonPath("$._links.self.href", is("http://localhost/api/user/" + id)));
	}
	
	@IntegrationTest
	void testUserAlreadyExists() throws Exception {
		AddUserRequestDto request = createValidRequest();
		addUserApi(request);
		
		addUserApi(request)
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.email").exists());
	}

	@IntegrationTest
	void testEmailNull() throws Exception {
		addUserApi(createValidRequest().withEmail(null))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.email").exists());
	}

	@IntegrationTest
	void testEmailEmpty() throws Exception {
		addUserApi(createValidRequest().withEmail(""))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.email").exists());
	}

	@IntegrationTest
	void testEmailMaxLengthKo() throws Exception {
		String email = RandomStringUtils.randomAlphabetic(350) + "@test.com";
		
		addUserApi(createValidRequest().withEmail(email))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.email").exists());
	}

	@IntegrationTest
	void testEmailInvalid() throws Exception {
		addUserApi(createValidRequest().withEmail("test"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.email").exists());
	}
	
	@IntegrationTest
	void testNameNull() throws Exception {
		addUserApi(createValidRequest().withName(null))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.name").exists());
	}

	@IntegrationTest
	void testNameEmpty() throws Exception {
		addUserApi(createValidRequest().withName(""))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.name").exists());
	}

	@IntegrationTest
	void testNameMinLengthKo() throws Exception {
		addUserApi(createValidRequest().withName(RandomStringUtils.randomAlphabetic(2)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.name").exists());
	}

	@IntegrationTest
	void testNameMinLengthOk() throws Exception {
		addUserApi(createValidRequest().withName(RandomStringUtils.randomAlphabetic(3)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.errors").doesNotExist());
	}

	@IntegrationTest
	void testNameMaxLengthOk() throws Exception {
		addUserApi(createValidRequest().withName(RandomStringUtils.randomAlphabetic(40)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.errors").doesNotExist());
	}

	@IntegrationTest
	void testNameMaxLengthKo() throws Exception {
		addUserApi(createValidRequest().withName(RandomStringUtils.randomAlphabetic(41)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.name").exists());
	}

	@IntegrationTest
	void testSurnameNull() throws Exception {
		addUserApi(createValidRequest().withSurname(null))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.surname").exists());
	}

	@IntegrationTest
	void testSurnameEmpty() throws Exception {
		addUserApi(createValidRequest().withSurname(""))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.surname").exists());
	}

	@IntegrationTest
	void testSurnameMinLengthKo() throws Exception {
		addUserApi(createValidRequest().withSurname(RandomStringUtils.randomAlphabetic(2)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.surname").exists());
	}

	@IntegrationTest
	void testSurnameMinLengthOk() throws Exception {
		addUserApi(createValidRequest().withSurname(RandomStringUtils.randomAlphabetic(3)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.errors").doesNotExist());
	}

	@IntegrationTest
	void testSurnameMaxLengthOk() throws Exception {
		addUserApi(createValidRequest().withSurname(RandomStringUtils.randomAlphabetic(80)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.errors").doesNotExist());
	}

	@IntegrationTest
	void testSurnameMaxLengthKo() throws Exception {
		addUserApi(createValidRequest().withSurname(RandomStringUtils.randomAlphabetic(81)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", aMapWithSize(1)))
			.andExpect(jsonPath("$.errors.surname").exists());
	}
}
