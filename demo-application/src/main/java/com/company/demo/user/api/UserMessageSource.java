package com.company.demo.user.api;

import org.springframework.stereotype.Component;

import com.company.demo.common.CustomMessageSource;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserMessageSource {

	private final CustomMessageSource messageSource;
	
	public String getMessageUserAlreadyExists() {
		return messageSource.getMessage("user.already.exists");
	}
}
