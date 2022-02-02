package com.riva.odos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riva.odos.domain.AirportInfoDto;
import com.riva.odos.domain.AirportWaitTimeDto;
import com.riva.odos.services.AirportService;

@RestController
@RequestMapping(value="/api/v1", produces= {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AirportSearchController {

	@Autowired
	AirportService airportService;
	
    @RequestMapping(value="/airports", method=RequestMethod.GET)
    public List<AirportInfoDto> search() {
        return airportService.getAirports();
    }
    
    @RequestMapping(value="/airportSearch", method=RequestMethod.GET)
    public List<AirportInfoDto> airportSearch(@RequestParam(value="searchValue") String searchValue) {
        return airportService.searchAirports(searchValue);
    }
    
    @RequestMapping(value="/airportWaitTime", method=RequestMethod.GET)
    public List<AirportWaitTimeDto> airportWaitSearch(@RequestParam(value="airportShortCodes") List<String> airportShortCodes) {
    	return airportService.searchAirportWaitTimes(airportShortCodes);
    }
}
