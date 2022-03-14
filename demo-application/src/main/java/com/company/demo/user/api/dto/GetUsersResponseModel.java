package com.company.demo.user.api.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Relation(itemRelation = "user", collectionRelation = "users")
@Getter
@AllArgsConstructor
public class GetUsersResponseModel extends RepresentationModel<GetUsersResponseModel> {
	private final PageMetadata pagination;
}
