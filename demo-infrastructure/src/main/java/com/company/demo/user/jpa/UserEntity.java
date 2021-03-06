package com.company.demo.user.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.company.demo.user.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
    private String email;
	
    private String name;
    private String surname;
    private String status;
    
    public static UserEntity of(User user) {
    	return UserEntityMapper.INSTANCE.domainToEntity(user);
    }
    
    public User toDomain() {
    	return UserEntityMapper.INSTANCE.entityToDomain(this);
    }
}
