package com.riva.odos.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.riva.odos.domain.UserInfoDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserService.class)
class UserServiceTest {
	@Autowired
	UserService userService;

	@Test
	void shouldGetUser() {
		UserInfoDto expectedUser = new UserInfoDto("Bruce Banter", "Hero", "Avengies", "Male", "10/10/1980", "200000",
				"test@test.com");
		UserInfoDto actualUser = userService.getUser();

		assertEquals(expectedUser, actualUser);
	}

}
