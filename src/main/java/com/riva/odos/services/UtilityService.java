package com.riva.odos.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class UtilityService {

	private static String readFileFromResources(String fileName) throws IOException {
	    return IOUtils.resourceToString(fileName, StandardCharsets.UTF_8);
	}
	
}
