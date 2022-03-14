package com.company.demo.common.model;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PagePersistence<T> {
	int pageNumber;
	int totalPages;
	long totalElements;
	int size;
	List<T> content;

    public static <T> PagePersistence<T> of(Page<T> page) {
		return PagePersistence.<T>builder()
				.pageNumber(page.getNumber())
				.totalElements(page.getTotalElements())
				.totalPages(page.getTotalPages())
				.size(page.getSize())
				.content(page.getContent())
				.build();
    }
    
    public Pagination<T> toDomain() {
		return Pagination.<T>builder()
				.pageNumber(pageNumber)
				.totalElements(totalElements)
				.totalPages(totalPages)
				.size(size)
				.content(content)
				.build();
    }
    
}
