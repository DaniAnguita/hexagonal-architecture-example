package com.company.demo.user.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.company.demo.common.model.Pagination;
import com.company.demo.user.api.dto.AddUserRequestDto;
import com.company.demo.user.api.dto.AddUserResponseDto;
import com.company.demo.user.api.dto.GetUserResponseDto;
import com.company.demo.user.api.dto.GetUsersResponseDto;
import com.company.demo.user.api.dto.GetUsersResponseModel;
import com.company.demo.user.api.dto.UserListDto;
import com.company.demo.user.param.GetUserRequest;
import com.company.demo.user.param.GetUsersRequest;
import com.company.demo.user.param.GetUsersResponse;
import com.company.demo.user.ports.in.UserServicePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserApiAdapter {
	
	private static final String LINK_RELATION_GET_USERS = "users";

	private final UserServicePort userService;

	public EntityModel<GetUserResponseDto> getUser(Long id) {
		return EntityModel.of(
				GetUserResponseDto.of(userService.getUser(new GetUserRequest(id)))
			).add(createLinkSelfRel(id));
	}
	
	private Link createLinkSelfRel(Long id) {
		return linkTo(methodOn(UserApiController.class).getUser(id)).withSelfRel();
	}

	public ResponseEntity<AddUserResponseDto> addUser(AddUserRequestDto request) {
        return new ResponseEntity<>(
        		AddUserResponseDto.of(userService.addUser(request.toDomain())),
        		HttpStatus.CREATED);
	}

	public RepresentationModel<?> getUsers(Integer page) {
		GetUsersResponse response = userService.getUsers(GetUsersRequest.builder().pageNumber(page).build());
		return createRepresentationModelGetUsers(GetUsersResponseDto.of(response));
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

}
