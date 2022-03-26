package com.company.demo.common.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExceptionResponse {
	Map<String, String> errors;
}
