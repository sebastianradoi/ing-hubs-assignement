package com.hubs.ing.assignment.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@RequiredArgsConstructor
@Getter
@Setter
public class StoreUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_user_id_generator")
	@SequenceGenerator(name = "store_user_id_generator", sequenceName = "store_user_id_seq", initialValue = 1000, allocationSize = 1)
	private Long id;
	private String username;
	private String password;
	private String roles;

}
