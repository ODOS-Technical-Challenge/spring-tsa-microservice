package com.riva.odos.services;

import org.springframework.stereotype.Service;

import com.riva.odos.domain.AirportInfoDto;

@Service
public class AirportService {
	public AirportInfoDto getUser() {
		return new AirportInfoDto("Bruce Banter", "Hero", "Avengies", "Male", "10/10/1980", "200000", "test@test.com");
	}
}
