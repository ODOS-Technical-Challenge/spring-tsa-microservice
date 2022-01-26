package com.riva.odos.services;

import org.springframework.stereotype.Service;

import com.riva.odos.domain.UserInfoDto;

@Service
public class UserService {
	public UserInfoDto getUser() {
		return new UserInfoDto("Bruce Banter", "Hero", "Avengies", "Male", "10/10/1980", "200000", "test@test.com");
	}
}
