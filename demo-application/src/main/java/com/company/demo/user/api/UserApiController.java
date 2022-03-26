package com.company.demo.user.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.common.model.ExceptionResponse;
import com.company.demo.user.api.dto.AddUserRequestDto;
import com.company.demo.user.api.dto.AddUserResponseDto;
import com.company.demo.user.api.dto.GetUserResponseDto;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.exception.UserNotFoundException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApiController {
	
	private final UserApiAdapter userAdapter;
	private final UserMessageSource userMessageSource;

	@GetMapping("/{id}")
	public EntityModel<GetUserResponseDto> getUser(@PathVariable Long id) {
		return userAdapter.getUser(id);
	}

    @PostMapping
    public ResponseEntity<AddUserResponseDto> addUser(@RequestBody AddUserRequestDto request) {
    	return userAdapter.addUser(request);
    }
	
	@GetMapping
	public RepresentationModel<?> getUsers(@RequestParam(required = false) Integer page) {
		return userAdapter.getUsers(page);
	}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
    			.build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
    	Map<String, String> errors = Collections.singletonMap("email", userMessageSource.getMessageUserAlreadyExists());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(errors));
    }
	
}
