package com.riva.odos.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riva.odos.domain.AirportInfoDto;
import com.riva.odos.domain.AirportWaitTimeDto;
import com.riva.odos.domain.DelayTimeDto;

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
			return mockDelayTimes(objectMapper.readValue(json, new TypeReference<List<AirportInfoDto>>(){}));
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<AirportInfoDto> searchAirports(String searchString){
		List<AirportInfoDto> allAirports = getAirports();
		List<AirportInfoDto> searchResults = new ArrayList<>();
		for(AirportInfoDto airport: allAirports) {
			if(airport.getShortcode().toUpperCase().contains(searchString.toUpperCase()) || airport.getName().toUpperCase().contains(searchString.toUpperCase()) 
					|| airport.getCity().toUpperCase().contains(searchString.toUpperCase()) || airport.getState().toUpperCase().contains(searchString.toUpperCase())) {
				searchResults.add(airport);
			}
		}
		return mockDelayTimes(searchResults);
	}
	
	public List<AirportWaitTimeDto> searchAirportWaitTimes(List<String> airportShortCodes) {
		
		List<AirportWaitTimeDto> requestedAirportWaitTimes = new ArrayList<>();
		
		for(String shortCode: airportShortCodes) {
			for(AirportInfoDto airport: searchAirports(shortCode)) {
				requestedAirportWaitTimes.add(buildMockAirportWaitTimeDto(airport));
			}
		}
		return requestedAirportWaitTimes;
	}
	
	private List<AirportInfoDto> mockDelayTimes(List<AirportInfoDto> airports) {
		airports.forEach((airport) -> airport.setDelayTimes(mockDelayTime()));
		return airports;
	}
	
	private DelayTimeDto mockDelayTime() {
		Integer minEstimatedWaitTime = (int) (Math.random() * 60);
		Integer maxEstimatedWaitTime = (int) (minEstimatedWaitTime + (Math.random() * 120));
		Integer maxEstimatedPrecheckTime = (int) (Math.random() * 60);

		DelayTimeDto delayTime = new DelayTimeDto();
		delayTime.setMinEstimatedWaitTime(minEstimatedWaitTime);
		delayTime.setMaxEstimatedWaitTime(maxEstimatedWaitTime);
		delayTime.setMaxEstimatedPrecheckTime(maxEstimatedPrecheckTime);

		return delayTime;
	}

	protected AirportWaitTimeDto buildMockAirportWaitTimeDto(AirportInfoDto airport) {
		AirportWaitTimeDto airportWaitTime = new AirportWaitTimeDto();
		airportWaitTime.setLongname(airport.getName());
		airportWaitTime.setShortname(airport.getShortcode());
		airportWaitTime.setCurrentWaitMinutes(createMockListOfWaitTimes());
		return airportWaitTime;
	}
	
	protected List<Long> createMockListOfWaitTimes() {
		List<Long> waitTimeList = new ArrayList<>();
		Long minWait = 1L;
	    Long maxWait = 300L;
	    for(int i = 0; i < 25; i++) {
	    	waitTimeList.add(minWait + (long) (Math.random() * (maxWait - minWait)));
	    }
	    return waitTimeList;
	}
}
