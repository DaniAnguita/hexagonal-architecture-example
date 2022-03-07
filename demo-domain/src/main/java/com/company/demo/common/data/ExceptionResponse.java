package com.company.demo.common.data;

import java.util.Map;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExceptionResponse {
	Map<String, String> errors;
}
