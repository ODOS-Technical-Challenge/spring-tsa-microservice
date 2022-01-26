package com.riva.odos.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riva.odos.domain.AirportInfoDto;

@Service
public class AirportService {
	@Autowired
	ObjectMapper objectMapper;

	public List<AirportInfoDto> getAirports() {
		return parseJson(retrieveJson());
	}

	private String retrieveJson() {
		try (InputStream in = getClass().getResourceAsStream("/AirportList.json");
			    BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (Exception e) {
			return null;
		}
	};
	
	private List<AirportInfoDto> parseJson(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<AirportInfoDto>>(){});
		} catch (Exception e) {
			return null;
		}
	}
}
