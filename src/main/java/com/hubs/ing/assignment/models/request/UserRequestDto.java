package com.hubs.ing.assignment.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for {@link com.hubs.ing.assignment.database.entities.StoreUser}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequestDto(String username, String password, String roles) {

}
