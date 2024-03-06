package com.hubs.ing.assignment.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FetchProductResponseDto(Long id, String name, String description, Double price) {
}
