package com.company.demo.common.data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Pagination<T> {
	int pageNumber;
	int totalPages;
	long totalElements;
	int size;
	List<T> content;
	
	public <U> Pagination<U> map(Function<? super T, ? extends U> converter) {
		return Pagination.<U>builder()
				.pageNumber(pageNumber)
				.totalPages(totalPages)
				.totalElements(totalElements)
				.size(size)
				.content(content.stream().map(converter::apply).collect(Collectors.toList()))
				.build();
		
	}
}

