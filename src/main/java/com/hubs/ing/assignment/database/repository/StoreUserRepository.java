package com.hubs.ing.assignment.database.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hubs.ing.assignment.database.entities.StoreUser;

@Repository
public interface StoreUserRepository extends CrudRepository<StoreUser, Long> {
	Optional<StoreUser> findByUsername(String username);

}
