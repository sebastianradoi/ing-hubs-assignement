package com.hubs.ing.assignment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hubs.ing.assignment.models.response.Response;

@Service
public class ResponseService {
	public Response createSuccessResponse(Long id, String message) {
		return new Response(id, Boolean.TRUE, List.of(message));
	}

}
