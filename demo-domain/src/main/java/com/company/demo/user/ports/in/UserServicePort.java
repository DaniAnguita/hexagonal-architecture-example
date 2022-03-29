package com.company.demo.user.ports.in;

import com.company.demo.common.model.PageNumber;
import com.company.demo.common.model.Pagination;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;

public interface UserServicePort {
	User getUser(UserId id);
	User addUser(User user);
	Pagination<User> getUsers(PageNumber page);
}
