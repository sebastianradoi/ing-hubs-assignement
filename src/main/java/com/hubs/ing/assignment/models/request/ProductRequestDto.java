package com.hubs.ing.assignment.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for {@link com.hubs.ing.assignment.database.entities.Product}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductRequestDto(String name, String description, Double price) {
}
