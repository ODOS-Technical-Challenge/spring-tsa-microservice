package com.riva.odos.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	public static String readFileFromResources(String fileName) throws IOException {
	    return IOUtils.resourceToString(fileName, StandardCharsets.UTF_8);
	}
	
}
