package com.ipen.ems.security.auth;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void createUser(User user, Set<UserRole> userRoles) throws IOException {
		log.debug("Creating new user {}", user.getEmail());

		if (userRepository.findByEmail(user.getEmail()) != null) {
			log.error("Duplicate Email: {}", user.getEmail());
		} else {
			user = userRepository.save(user);
			for (UserRole userRole : userRoles)
				userRole.setUser(user);
			userRoleRepository.saveAll(userRoles);
		}

		return;
	}

}