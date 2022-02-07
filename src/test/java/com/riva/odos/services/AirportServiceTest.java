package com.riva.odos.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riva.odos.domain.AirportInfoDto;
import com.riva.odos.domain.AirportWaitTimeDto;
import com.riva.odos.domain.PredictedWaitTimeDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AirportService.class, ObjectMapper.class })
class AirportServiceTest {
	
	@Spy
	AirportService airportService;
	
	@Autowired
	AirportService unSpyAirportService;

	private static final String DCA = "DCA";
	private static final String REAGAN = "Washington Reagan National";
	private static final String UNKNOWN = "unknown";
	
	@Test
	void shouldGetAirports() {
		List<AirportInfoDto> airPorts = unSpyAirportService.getAirports();
		
		assertTrue(airPorts.size() > 0);
	}
	
	@Test
	public void testSearchAirports(){
		List<AirportInfoDto> returnedAirportList = new ArrayList<>();
		AirportInfoDto expectedAirportInfo = new AirportInfoDto();
		expectedAirportInfo.setName(REAGAN);
		expectedAirportInfo.setShortcode(DCA);
		
		List<AirportInfoDto> testAirports = testAirportList();
		
		doReturn(testAirports).when(airportService).getAirports();
		
		returnedAirportList = airportService.searchAirports(DCA);
		
		assertEquals(expectedAirportInfo.getName(), returnedAirportList.get(0).getName());
		assertEquals(expectedAirportInfo.getShortcode(), returnedAirportList.get(0).getShortcode());
		
		returnedAirportList = airportService.searchAirports("VA");
		
		assertEquals(expectedAirportInfo.getName(), returnedAirportList.get(0).getName());
		assertEquals(expectedAirportInfo.getShortcode(), returnedAirportList.get(0).getShortcode());
		
		returnedAirportList = airportService.searchAirports("DC");
		
		assertEquals(expectedAirportInfo.getName(), returnedAirportList.get(0).getName());
		assertEquals(expectedAirportInfo.getShortcode(), returnedAirportList.get(0).getShortcode());
		
		returnedAirportList = airportService.searchAirports("National");
		
		assertEquals(expectedAirportInfo.getName(), returnedAirportList.get(0).getName());
		assertEquals(expectedAirportInfo.getShortcode(), returnedAirportList.get(0).getShortcode());
	}
	
	@Test
	public void testSearchAirportsNotFound(){
		List<AirportInfoDto> expectedAirportInfoList = new ArrayList<>();
		List<AirportInfoDto> returnedAirportList = new ArrayList<>();
		
		when(airportService.getAirports()).thenReturn(testAirportList());
		
		returnedAirportList = airportService.searchAirports(UNKNOWN);
		
		assertEquals(expectedAirportInfoList.size(), returnedAirportList.size());
	}
	
	@Test
	public void testSearchAirportWaitTimes() {
		AirportWaitTimeDto requestedAirportWaitTimes = new AirportWaitTimeDto();
		AirportWaitTimeDto expectedWaitTimeDto = new AirportWaitTimeDto();
		List<AirportInfoDto> requestedAirportInfo = new ArrayList<>();
		AirportInfoDto airportInfo = new AirportInfoDto();
		List<AirportInfoDto> mockSearchResults = new ArrayList<>();
		
		mockSearchResults.add(getSingleTestAirport());
		
		airportInfo.setName(REAGAN);
		airportInfo.setShortcode(DCA);
		requestedAirportInfo.add(airportInfo);
		
		expectedWaitTimeDto.setLongname(REAGAN);
		expectedWaitTimeDto.setShortname(DCA);
		expectedWaitTimeDto.setCurrentWaitMinutes(mockWaitList());
		
		doReturn(getSingleTestAirport()).when(airportService).getSingleAirport(DCA);
//		when(airportService.searchAirports(DCA)).thenReturn(requestedAirportInfo);
		
		requestedAirportWaitTimes = airportService.searchAirportHistoricWaitTimes(DCA);
		
		assertEquals(expectedWaitTimeDto.getShortname(), requestedAirportWaitTimes.getShortname());
	}
	
	@Test
	public void testGetPredictedwaitTime() {
		Date futureDate = new Date();
		PredictedWaitTimeDto returnedPredictedWaitTimeDto = new PredictedWaitTimeDto();
		PredictedWaitTimeDto expectedPredictedWaitTimeDto = new PredictedWaitTimeDto();
		expectedPredictedWaitTimeDto.setLongname(REAGAN);
		expectedPredictedWaitTimeDto.setShortname(DCA);
		expectedPredictedWaitTimeDto.setPredictedWaitMinutes(96L);

		doReturn(getSingleTestAirport()).when(airportService).getSingleAirport(DCA);
		returnedPredictedWaitTimeDto = airportService.getPredictedwaitTime(DCA, futureDate);
		
		assertEquals(expectedPredictedWaitTimeDto.getShortname(), returnedPredictedWaitTimeDto.getShortname());
		assertEquals(expectedPredictedWaitTimeDto.getLongname(), returnedPredictedWaitTimeDto.getLongname());
		assertNotNull(returnedPredictedWaitTimeDto.getPredictedWaitMinutes());
	}
	
	@Test
	public void testGetPredictedwaitTimeNoAirport() {
		Date futureDate = new Date();
		PredictedWaitTimeDto returnedPredictedWaitTimeDto = new PredictedWaitTimeDto();
		PredictedWaitTimeDto expectedPredictedWaitTimeDto = new PredictedWaitTimeDto();

		returnedPredictedWaitTimeDto = airportService.getPredictedwaitTime("unknown", futureDate);
		
		assertEquals(expectedPredictedWaitTimeDto.getShortname(), returnedPredictedWaitTimeDto.getShortname());
		assertEquals(expectedPredictedWaitTimeDto.getLongname(), returnedPredictedWaitTimeDto.getLongname());
	}
	
	@Test
	public void testGetSingleAirportNoList() {
		
		when(airportService.getAirports()).thenReturn(new ArrayList<>());

		AirportInfoDto returnedAirportInfo = airportService.getSingleAirport("unkown");
		assertNull(returnedAirportInfo);
	}
	
	@Test
	public void testSearchHistoricWaitTimesAirportNotFound() {
		AirportService mockedAirportService = spy(airportService);
		AirportWaitTimeDto expectedWaitTimes = new AirportWaitTimeDto();
		AirportWaitTimeDto returnedWaitTimes = new AirportWaitTimeDto();
		
		when(mockedAirportService.getSingleAirport(UNKNOWN)).thenReturn(null);
		
		returnedWaitTimes = airportService.searchAirportHistoricWaitTimes(UNKNOWN);
	        
		assertEquals(expectedWaitTimes.getCurrentWaitMinutes(), returnedWaitTimes.getCurrentWaitMinutes());
	}
	
	private List<AirportInfoDto> testAirportList(){
		List<AirportInfoDto> mockAirportList = new ArrayList<>();
		AirportInfoDto airportInfo1 = new AirportInfoDto();
		airportInfo1.setName(REAGAN);
		airportInfo1.setShortcode(DCA);
		airportInfo1.setCity("Washington, DC");
		airportInfo1.setState("VA");
		mockAirportList.add(airportInfo1);
		
		AirportInfoDto airportInfo2 = new AirportInfoDto();
		airportInfo2.setName("Baltimore Washington International");
		airportInfo2.setShortcode("BWI");
		airportInfo2.setCity("Baltimore");
		airportInfo2.setState("MD");
		mockAirportList.add(airportInfo2);
		
		return mockAirportList;
	}
	
	private AirportInfoDto getSingleTestAirport() {
		AirportInfoDto airportInfo = new AirportInfoDto();
		airportInfo.setName(REAGAN);
		airportInfo.setShortcode(DCA);
		airportInfo.setCity("Washington, DC");
		airportInfo.setState("VA");
		
		return airportInfo;
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
