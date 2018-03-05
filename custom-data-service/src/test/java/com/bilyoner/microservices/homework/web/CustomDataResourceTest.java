package com.bilyoner.microservices.homework.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bilyoner.microservices.homework.NumberServiceApplication;
import com.bilyoner.microservices.homework.domain.CustomData;
import com.bilyoner.microservices.homework.repository.CustomDataRepository;
import com.bilyoner.microservices.homework.service.CustomDataService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NumberServiceApplication.class)
public class CustomDataResourceTest {

	private static final String DEFAULT_ID = "AAA";

	private static final String DEFAULT_NUMBER = "1111";
	private static final String UPDATED_NUMBER = "2222";

	@Autowired
	CustomDataService customDataService;

	@Autowired
	CustomDataRepository customDataRepository;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
	private ExceptionHandlerAdvice exceptionHandlerAdvice;

	private MockMvc restcustomDataMockMvc;

	private CustomData customData;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		CustomDataResource customDataResource = new CustomDataResource(customDataService);
		this.restcustomDataMockMvc = MockMvcBuilders.standaloneSetup(customDataResource)
				.setControllerAdvice(exceptionHandlerAdvice).setMessageConverters(jacksonMessageConverter).build();
	}

	public static CustomData createEntity() {
		CustomData customData = new CustomData(DEFAULT_ID, new Long(DEFAULT_NUMBER));
		return customData;
	}

	@Before
	public void initTest() {
		customData = createEntity();
	}

	@Test
	public void createCustomData() throws Exception {
		int databaseSizeBeforeCreate = customDataRepository.findAll().size();

		// Create the CustomData
		restcustomDataMockMvc.perform(post("/custom-datas/create").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(customData))).andExpect(status().isCreated());

		// Validate the CustomData in the MongoDB
		List<CustomData> customDataList = customDataRepository.findAll();
		assertThat(customDataList).hasSize(databaseSizeBeforeCreate + 1);
		CustomData testCustomData = customDataList.get(customDataList.size() - 1);
		assertThat(testCustomData.getId()).isEqualTo(DEFAULT_ID);
		assertThat(testCustomData.getNumber()).isEqualTo(new Long(DEFAULT_NUMBER));
		
		customDataRepository.delete(customData);

	}

}
