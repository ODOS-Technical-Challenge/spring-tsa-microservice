package com.riva.odos.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riva.odos.domain.AirportInfoDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AirportService.class, ObjectMapper.class })
class AirportServiceTest {
	@Autowired
	AirportService userService;

	@Test
	void shouldGetUser() {
		List<AirportInfoDto> airPorts = userService.getAirports();

		assertTrue(airPorts.size() > 0);
	}

}
