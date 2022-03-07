package com.company.demo.user;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Relation(itemRelation = "user", collectionRelation = "users")
@Getter
@AllArgsConstructor
public class GetUsersResponseDtoModel extends RepresentationModel<GetUsersResponseDtoModel> {
	private final PageMetadata pagination;
}
