package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.jpa.UserEntity;
import com.company.demo.user.jpa.UserEntityMapper;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;

public class UserEntityMapperTests {

	@UnitTest
	void testDomainToEntityNull() {
		assertThat(UserEntityMapper.INSTANCE.domainToEntity(null)).isEqualTo(null);
	}

	@UnitTest
	void testEntityToDomainNull() {
		assertThat(UserEntityMapper.INSTANCE.entityToDomain(null)).isEqualTo(null);
	}
	
	@UnitTest
	void testEntityToDomainFieldsNull() {
		User userMapped = new UserEntity().toDomain();
		assertThat(userMapped.getId()).isNull();
	}
	
	@UnitTest
	void testDomainToEntityFieldNull() {
		UserEntity entityMapped = UserEntity.of(User.builder().build());
		assertThat(entityMapped.getId()).isNull();
	}
	
	@UnitTest
	void testDomainToEntityUserIdNotNull() {
		User user = User.builder().id(new UserId(1l)).build();
		UserEntity entity = UserEntityMapper.INSTANCE.domainToEntity(user);
		
		assertThat(user.getId().getValue()).isEqualTo(entity.getId());
	}
}
