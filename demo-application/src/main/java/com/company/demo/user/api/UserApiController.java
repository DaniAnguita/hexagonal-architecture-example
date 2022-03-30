package com.company.demo.user.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
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

import com.company.demo.common.exception.InvalidPageNumberException;
import com.company.demo.common.model.ExceptionResponse;
import com.company.demo.common.model.PageNumber;
import com.company.demo.common.model.Pagination;
import com.company.demo.user.api.dto.AddUserRequestDto;
import com.company.demo.user.api.dto.UserDto;
import com.company.demo.user.api.dto.GetUserResponseDto;
import com.company.demo.user.api.dto.GetUsersResponseDto;
import com.company.demo.user.api.dto.GetUsersResponseModel;
import com.company.demo.user.api.dto.UserListDto;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Surname;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;
import com.company.demo.user.ports.in.UserServicePort;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApiController {
	
	private static final String LINK_RELATION_GET_USERS = "users";

	private final UserServicePort userService;
	private final UserMessageSource userMessageSource;

	@GetMapping("/{id}")
	public EntityModel<GetUserResponseDto> getUser(@PathVariable Long id) {
		return EntityModel.of(
				GetUserResponseDto.of(userService.getUser(new UserId(id)))
			).add(createLinkSelfRel(id));
	}
	
	private Link createLinkSelfRel(Long id) {
		return linkTo(methodOn(UserApiController.class).getUser(id)).withSelfRel();
	}

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody AddUserRequestDto request) {
    	User user = User.builder()
    			.email(new Email(request.getEmail()))
    			.name(new Name(request.getName()))
    			.surname(new Surname(request.getSurname()))
    			.build();
    	
        return new ResponseEntity<>(
        		UserDto.of(userService.addUser(user)),
        		HttpStatus.CREATED);
    }
	
	@GetMapping
	public RepresentationModel<?> getUsers(@RequestParam(required = false) Integer page) {
		Pagination<User> users = userService.getUsers(createPageNumber(page));
		return createRepresentationModelGetUsers(GetUsersResponseDto.of(users));
	}
	
	private PageNumber createPageNumber(Integer page) {
		return new PageNumber(page != null ? page : 1);
	}
	
	private RepresentationModel<?> createRepresentationModelGetUsers(GetUsersResponseDto responseDto) {
		return HalModelBuilder.halModel()
				.embed(getListUsersEntities(responseDto), LinkRelation.of(LINK_RELATION_GET_USERS))
				.entity(new GetUsersResponseModel(getPaginationGetUsers(responseDto)))
				.build();
	}
	
	private List<EntityModel<UserListDto>> getListUsersEntities(GetUsersResponseDto responseDto) {
		return responseDto.getUsersPagination()
				.getContent()
				.stream()
				.map(u -> EntityModel.of(u).add(createLinkSelfRel(u.getId())))
				.collect(Collectors.toList());
	}
	
	private PageMetadata getPaginationGetUsers(GetUsersResponseDto responseDto) {
		Pagination<UserListDto> usersPagination = responseDto.getUsersPagination();
		
		return new PageMetadata(usersPagination.getSize(), 
				usersPagination.getPageNumber() + 1, 
				usersPagination.getTotalElements(), 
				usersPagination.getTotalPages());
	}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(InvalidPageNumberException.class)
    public ResponseEntity<Void> handleInvalidPageNumberException(InvalidPageNumberException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
    	Map<String, String> errors = Collections.singletonMap("email", userMessageSource.getMessageUserAlreadyExists());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(errors));
    }
	
}
