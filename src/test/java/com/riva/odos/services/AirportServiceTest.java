package com.riva.odos.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riva.odos.domain.AirportInfoDto;
import com.riva.odos.domain.AirportWaitTimeDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AirportService.class, ObjectMapper.class })
class AirportServiceTest {
	
	@Autowired
	AirportService airportService;

	private static final String DCA = "DCA";
	
	@Test
	void shouldGetUser() {
		List<AirportInfoDto> airPorts = airportService.getAirports();
		assertTrue(airPorts.size() > 0);
	}
	
	//TODO: Test the below functions
	public void testSearchAirports(){
		
		AirportInfoDto expectedAirportInfo = new AirportInfoDto();
		expectedAirportInfo.setName("Washington Reagan National");
		expectedAirportInfo.setShortcode("DCA");
		
		Mockito.when(airportService.getAirports()).thenReturn(testAirportList());
		
		List<AirportInfoDto> returnedAirportList = new ArrayList<>();
		
		returnedAirportList = airportService.searchAirports("DCA");
		
		assertEquals(expectedAirportInfo, returnedAirportList);

	}
	
	@Test
	public void testSearchAirportWaitTimes() {
		AirportService mockedAirportService = Mockito.spy(airportService);
		
		List<AirportWaitTimeDto> requestedAirportWaitTimes = new ArrayList<>();
		List<String> testShortCodes = new ArrayList<>();
		testShortCodes.add("DCA");

		AirportWaitTimeDto expectedWaitTimeDto = new AirportWaitTimeDto();
		expectedWaitTimeDto.setLongname("Washington Reagan National");
		expectedWaitTimeDto.setShortname(DCA);
		expectedWaitTimeDto.setCurrentWaitMinutes(mockWaitList());
		
		List<AirportWaitTimeDto> expectedWaitTimeList = new ArrayList<>();
		expectedWaitTimeList.add(expectedWaitTimeDto);
	
		
		List<AirportInfoDto> requestedAirportInfo = new ArrayList<>();
		AirportInfoDto airportInfo = new AirportInfoDto();
		airportInfo.setName("Washington Reagan National");
		airportInfo.setShortcode("DCA");
		requestedAirportInfo.add(airportInfo);
		
		Mockito.when(mockedAirportService.searchAirports(DCA)).thenReturn(requestedAirportInfo);
		
		requestedAirportWaitTimes = airportService.searchAirportWaitTimes(testShortCodes);
		assertEquals(expectedWaitTimeList.get(0).getLongname(), requestedAirportWaitTimes.get(0).getLongname());
	}
	
	private List<AirportInfoDto> testAirportList(){
		List<AirportInfoDto> mockAirportList = new ArrayList<>();
		AirportInfoDto airportInfo = new AirportInfoDto();
		airportInfo.setName("Washington Reagan National");
		airportInfo.setShortcode("DCA");
		mockAirportList.add(airportInfo);
		airportInfo.setName("Baltimore Washington International");
		airportInfo.setShortcode("BWI");
		mockAirportList.add(airportInfo);
		return mockAirportList;
	}
	
	private List<Long> mockWaitList(){
		List<Long> mockWaitList = new ArrayList<>();
		mockWaitList.add(208L);
		mockWaitList.add(152L);
		mockWaitList.add(106L);
		mockWaitList.add(198L);
		mockWaitList.add(124L);
		mockWaitList.add(66L);
		mockWaitList.add(257L);
		mockWaitList.add(89L);
		mockWaitList.add(75L);
		mockWaitList.add(18L);
		mockWaitList.add(167L);
		mockWaitList.add(141L);
		mockWaitList.add(63L);
		mockWaitList.add(42L);
		mockWaitList.add(62L);
		mockWaitList.add(263L);
		mockWaitList.add(153L);
		mockWaitList.add(294L);
		mockWaitList.add(16L);
		mockWaitList.add(60L);
		mockWaitList.add(130L);
		mockWaitList.add(36L);
		mockWaitList.add(95L);
		mockWaitList.add(166L);
		mockWaitList.add(6L);
		return mockWaitList;
	}
	
}
