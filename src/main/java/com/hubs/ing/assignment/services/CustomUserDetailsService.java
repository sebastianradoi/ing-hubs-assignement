package com.hubs.ing.assignment.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hubs.ing.assignment.database.entities.StoreUser;
import com.hubs.ing.assignment.database.repository.StoreUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final StoreUserRepository storeUserRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		StoreUser storeUser = storeUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return User.builder()
				.username(storeUser.getUsername())
				.password(passwordEncoder.encode(storeUser.getPassword()))
				.roles(storeUser.getRoles().split(","))
				.build();
	}
}
