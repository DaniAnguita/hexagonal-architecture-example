package com.company.demo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
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

import com.company.demo.common.data.ExceptionResponse;
import com.company.demo.common.data.Pagination;
import com.company.demo.user.dto.AddUserRequestDto;
import com.company.demo.user.dto.AddUserResponseDto;
import com.company.demo.user.dto.GetUserRequestDto;
import com.company.demo.user.dto.GetUserResponseDto;
import com.company.demo.user.dto.GetUsersRequestDto;
import com.company.demo.user.dto.GetUsersResponseDto;
import com.company.demo.user.dto.UserListDto;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.exception.UserNotFoundException;
import com.company.demo.user.ports.inbound.UserServicePort;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApiController {
	
	private static final String LINK_RELATION_GET_USERS = "users";
	
	private final UserServicePort userService;
    private final MessageSource messageSource;

	@GetMapping("/{id}")
	public EntityModel<GetUserResponseDto> getUser(@PathVariable Long id) {
		return EntityModel.of(
				userService.getUser(GetUserRequestDto.builder().id(id).build())
			).add(createLinkSelfRel(id));
	}
	
	private Link createLinkSelfRel(Long id) {
		return linkTo(methodOn(UserApiController.class).getUser(id)).withSelfRel();
	}

    @PostMapping
    public ResponseEntity<AddUserResponseDto> addUser(@RequestBody AddUserRequestDto request) {
        return new ResponseEntity<>(
        		userService.addUser(request),
        		HttpStatus.CREATED);
    }
	
	@GetMapping
	public RepresentationModel<?> getUsers(@RequestParam(required = false) Integer page) {
		GetUsersResponseDto responseDto = userService.getUsers(GetUsersRequestDto.builder().pageNumber(page).build());
		return createRepresentationModelGetUsers(responseDto);
	}
	
	private RepresentationModel<?> createRepresentationModelGetUsers(GetUsersResponseDto responseDto) {
		return HalModelBuilder.halModel()
				.embed(getListUsersEntities(responseDto), LinkRelation.of(LINK_RELATION_GET_USERS))
				.entity(new GetUsersResponseDtoModel(getPaginationGetUsers(responseDto)))
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
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
    			.build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception, Locale locale) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
            			.errors(Collections.singletonMap("email", messageSource.getMessage("user.already.exists", null, locale)))
            			.build());
    }
	
}
