package com.hubs.ing.assignment.services;

import java.util.Optional;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.hubs.ing.assignment.database.entities.StoreUser;
import com.hubs.ing.assignment.database.repository.StoreUserRepository;
import com.hubs.ing.assignment.exceptions.BadRequestException;
import com.hubs.ing.assignment.exceptions.InternalServerErrorException;
import com.hubs.ing.assignment.models.request.UserRequestDto;
import com.hubs.ing.assignment.models.response.Response;
import com.hubs.ing.assignment.validation.IsUserRequestValid;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {

	private final StoreUserRepository storeUserRepository;
	private final ConversionService conversionService;
	private final ResponseService responseService;

	@IsUserRequestValid
	public Response addUser(UserRequestDto request) {
		var storeUser = Optional.ofNullable(conversionService.convert(request, StoreUser.class))
				.orElseThrow(() -> new InternalServerErrorException("Failed to convert UserRequestDto to StoreUser"));

		storeUserRepository.save(storeUser);
		return responseService.createSuccessResponse(storeUser.getId(), "User added successfully");
	}

	public Response deleteUser(Long userId) {
		var user = storeUserRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found"));
		storeUserRepository.delete(user);
		return responseService.createSuccessResponse(userId, "User deleted successfully");
	}
}
