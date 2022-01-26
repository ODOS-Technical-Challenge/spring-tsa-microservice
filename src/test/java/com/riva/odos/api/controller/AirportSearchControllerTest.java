//package com.riva.odos.api.controller;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.riva.odos.domain.AirportInfoDto;
//import com.riva.odos.services.AirportService;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(AirportSearchController.class)
//@ContextConfiguration(classes = AirportSearchController.class)
//public class AirportSearchControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private AirportService userService;
//
////	@BeforeEach
////	public void setUp() {
////		AirportInfoDto user = new AirportInfoDto("Bruce Banter", "Hero", "Avengies", "Male", "10/10/1980", "200000",
////				"test@test.com");
////
////		Mockito.when(userService.getUser()).thenReturn(user);
////	};
//
////	@Test
////	public void testController() throws Exception {
////		mockMvc.perform(get("/api/v1/search?searchValue=user").contentType(MediaType.APPLICATION_JSON)
////				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
////	}
////
////	@Test
////	public void shouldReturnDefaultMessage() throws Exception {
////		this.mockMvc.perform(get("/api/v1/search?searchValue=user")).andDo(print()).andExpect(status().isOk())
////				.andExpect(content().string(containsString(
////						"{\"name\":\"Bruce Banter\",\"occupation\":\"Hero\",\"company\":\"Avengies\",\"gender\":\"Male\",\"birthDate\":\"10/10/1980\",\"salary\":\"200000\",\"email\":\"test@test.com\"}")));
////	}
//
//}
