package com.riva.odos.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riva.odos.domain.AirportInfoDto;
import com.riva.odos.domain.AirportWaitTimeDto;
import com.riva.odos.domain.PredictedWaitTimeDto;
import com.riva.odos.services.AirportService;

@RestController
@RequestMapping(value="/api/v1", produces= {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AirportSearchController {

	@Autowired
	AirportService airportService;
	
    @GetMapping(value="/airports")
    public List<AirportInfoDto> search() {
        return airportService.getAirports();
    }
    
    @GetMapping(value="/airportSearch")
    public List<AirportInfoDto> airportSearch(@RequestParam(value="searchValue") String searchValue) {
        return airportService.searchAirports(searchValue);
    }
    
    @GetMapping(value="/airportHistoricWaitTimes")
    public AirportWaitTimeDto airportWaitSearch(@RequestParam(value="airportShortCode") String airportShortCode) {
    	return airportService.searchAirportHistoricWaitTimes(airportShortCode);
    }
    
    @GetMapping(value="/predictedWaitTime")
    public PredictedWaitTimeDto predictedWaitTime(@RequestParam(value="airportShortCode") String airportShortCode, 
    		@RequestParam(value="date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date futureDate){
    			return airportService.getPredictedwaitTime(airportShortCode, futureDate);
    		}
}
