package com.hubs.ing.assignment.models.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response(Long id, Boolean success, List<String> message) {
}
