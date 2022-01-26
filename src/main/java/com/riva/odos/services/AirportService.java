package com.riva.odos.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.riva.odos.domain.AirportInfoDto;

@Service
public class AirportService {
	@Autowired
	ObjectMapper objectMapper;

	public List<AirportInfoDto> getAirports() {
		return parseJson(retrieveJson());
	}

	private String retrieveJson() {
		try (FileReader fileReader = new FileReader("src/main/resources/AirportList.json");
				BufferedReader reader = new BufferedReader(fileReader)) {
			String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			return contents;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	
	private List<AirportInfoDto> parseJson(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<AirportInfoDto>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	@Autowired
	UtilityService utilityService;
	
	public AirportInfoDto getAirports(String criteria) {
		
		return searchForAirports(criteria).get(0);
		
//		return new AirportInfoDto("Bruce Banter", "Hero", "Avengies", "Male", "10/10/1980", "200000", "test@test.com");
	}
	
	
	private List<AirportInfoDto> searchForAirports(String searchCriteria) {
		ObjectMapper mapper = new ObjectMapper();
	      ObjectNode node = null;
		try {
			node = mapper.readValue(utilityService.readFileFromResources("/data/AirportList.json"), ObjectNode.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      if(node.has("name")) {
	         System.out.println("NAME: " + node.get("name"));
	      }
	      return new ArrayList<>();
	}
	
	
}
