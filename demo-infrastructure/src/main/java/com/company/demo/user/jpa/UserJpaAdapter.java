package com.company.demo.user.jpa;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.company.demo.common.data.Pagination;
import com.company.demo.user.data.User;
import com.company.demo.user.exception.UserAlreadyExistsException;
import com.company.demo.user.ports.outbound.UserPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {
	
	private final UserJpaRepository userRepository;

	@Override
	public User addUser(User user) {
		try {
			UserEntity entity = userRepository.save(UserEntityMapper.INSTANCE.userToEntity(user));
			return UserEntityMapper.INSTANCE.entityToUser(entity);
		} catch (DataIntegrityViolationException e) {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public Optional<User> findById(long id) {
		Optional<UserEntity> entity = userRepository.findById(id);
		
		Optional<User> user = Optional.empty();
		if (entity.isPresent()) {
			user = Optional.of(UserEntityMapper.INSTANCE.entityToUser(entity.get()));
		}
		
		return user;
	}
	
	@Override
	public Pagination<User> findAll(Optional<Integer> pageNumber) {
		int page = pageNumber.isPresent() ? pageNumber.get() - 1 : 0;
		
		Page<User> pageUser = userRepository
				.findAll(PageRequest.of(page, PAGE_SIZE))
				.map(e -> UserEntityMapper.INSTANCE.entityToUser(e));
		
		return Pagination.<User>builder()
				.pageNumber(pageUser.getNumber())
				.totalElements(pageUser.getTotalElements())
				.totalPages(pageUser.getTotalPages())
				.size(pageUser.getSize())
				.content(pageUser.getContent())
				.build();
	}

}
