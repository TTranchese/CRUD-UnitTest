package com.example.CRUDUnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Method;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldPostANewAccount() throws Exception {
		String jsonString = "{\"id\":1,\"name\":\"John\"}";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
						.content(jsonString)
						.contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
				.andDo(print());
	}
	
	@Test
	public void shouldReturnAnAccountById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{id}", 1L))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andDo(print());
	}
	
	@Test
	public void shouldUpdateStudentIdOne() throws Exception {
		String updatedStudentJson = "{\"name\":\"Tommaso\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.put("/accounts/{id}", 1L)
						.content(updatedStudentJson)
						.contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tommaso"))
				.andDo(print());
	}
	
	@Test
	public void testDeleteAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/{id}", 1L))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	
}
