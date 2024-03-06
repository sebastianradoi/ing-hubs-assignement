package com.hubs.ing.assignment.rest.api.converter;

import org.springframework.core.convert.converter.Converter;

import com.hubs.ing.assignment.database.entities.StoreUser;
import com.hubs.ing.assignment.models.request.UserRequestDto;

public class UserConverter implements Converter<UserRequestDto, StoreUser> {
	@Override
	public StoreUser convert(UserRequestDto source) {
		var user = new StoreUser();
		user.setUsername(source.username());
		user.setPassword(source.password());
		user.setRoles(source.roles());
		return user;
	}
}
