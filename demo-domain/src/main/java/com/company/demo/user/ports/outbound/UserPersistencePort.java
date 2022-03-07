package com.company.demo.user.ports.outbound;

import java.util.Optional;

import com.company.demo.common.data.Pagination;
import com.company.demo.user.data.User;

public interface UserPersistencePort {
	
	public static final int PAGE_SIZE = 15;
	
	User addUser(User user);
	Optional<User> findById(long id);
	Pagination<User> findAll(Optional<Integer> pageNumber);
}
