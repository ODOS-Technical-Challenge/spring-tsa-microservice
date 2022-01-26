package com.riva.odos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riva.odos.domain.AirportInfoDto;
import com.riva.odos.services.AirportService;

@RestController
@RequestMapping(value="/api/v1", produces= {MediaType.APPLICATION_JSON_VALUE})
public class AirportSearchController {

	@Autowired
	AirportService userService;
	
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public AirportInfoDto search(@RequestParam(value="searchValue") String searchValue) {
        return userService.getUser();
    }
    
}
