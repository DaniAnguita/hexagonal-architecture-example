package com.company.demo.user.jpa;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.company.demo.common.model.PageNumber;
import com.company.demo.common.model.PagePersistence;
import com.company.demo.common.model.Pagination;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.model.User;
import com.company.demo.user.ports.out.UserPersistencePort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {
	
	private final UserJpaRepository userRepository;

	@Override
	public User addUser(User user) {
		try {
			return userRepository.save(UserEntity.of(user)).toDomain();
		} catch (DataIntegrityViolationException e) {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public Optional<User> findById(long id) {
		Optional<UserEntity> entity = userRepository.findById(id);
		return entity.isPresent() ? Optional.of(entity.get().toDomain()) : Optional.empty();
	}
	
	@Override
	public Pagination<User> findAll(PageNumber pageNumber) {
		Page<User> pageUser = userRepository
				.findAll(PageRequest.of(pageNumber.getValue() - 1, PAGE_SIZE))
				.map(e -> e.toDomain());
		
		return PagePersistence.<User>of(pageUser).toDomain();
	}

}
