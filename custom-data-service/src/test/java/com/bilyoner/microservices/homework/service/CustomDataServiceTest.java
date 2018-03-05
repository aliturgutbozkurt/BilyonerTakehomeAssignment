package com.bilyoner.microservices.homework.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bilyoner.microservices.homework.NumberServiceApplication;
import com.bilyoner.microservices.homework.domain.CustomData;
import com.bilyoner.microservices.homework.exception.NoSuchCustomDataWithNumberException;
import com.bilyoner.microservices.homework.repository.CustomDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NumberServiceApplication.class)
public class CustomDataServiceTest {

	@Autowired
	CustomDataService customDataService;

	@Autowired
	CustomDataRepository customDataRepository;

	private CustomData customData1 = new CustomData("abc1", new Long("123"));
	private CustomData customData2 = new CustomData("abc2", new Long("234"));
	private CustomData customData3 = new CustomData("abc3", new Long("345"));;
	private CustomData customData4 = new CustomData("abc4", new Long("456"));;
	private CustomData customData5 = new CustomData("abc5", new Long("678"));

	@Before
	public void setup() {
		customDataRepository.save(customData1);
		customDataRepository.save(customData2);
		customDataRepository.save(customData3);
		customDataRepository.save(customData4);
		customDataRepository.save(customData5);
	}

	@Test
	public void createCustomData() {

		CustomData customDataSaved = customDataService.save(new CustomData("testToSave", new Long("888")));

		assertEquals(customDataSaved.getId(), "testToSave");

		customDataRepository.delete(customDataSaved);
	}

	@Test
	public void findByNumber() {

		CustomData customDatafoundByNumber = customDataService.findByNumber(new Long("234"));

		assertEquals(customDatafoundByNumber.getId(), "abc2");
	}

	@Test
	public void findTopByMaximumNumber() {
		CustomData customDataWithMaxNumber = customDataService.findTopByMaximumNumber();
		assertEquals(customData5.getNumber(), customDataWithMaxNumber.getNumber());
	}

	@Test
	public void findTopByMinumumNumber() {
		CustomData customDataWithMinNumber = customDataService.findTopByMinumumNumber();
		assertEquals(customData1.getNumber(), customDataWithMinNumber.getNumber());
	}

	@Test(expected = NoSuchCustomDataWithNumberException.class)
	public void deleteException() {
		customDataService.delete(new Long(999));
	}

	@Test
	public void findAllByOrderTypeNumberDesc() {
		List<CustomData> customDataNumberDescending = customDataService.findAllByOrderType("descending");
		assertEquals(customData5.getNumber(), customDataNumberDescending.get(0).getNumber());
		assertEquals(customData4.getNumber(), customDataNumberDescending.get(1).getNumber());
		assertEquals(customData3.getNumber(), customDataNumberDescending.get(2).getNumber());
		assertEquals(customData2.getNumber(), customDataNumberDescending.get(3).getNumber());
		assertEquals(customData1.getNumber(), customDataNumberDescending.get(4).getNumber());

	}

	@Test
	public void findAllByOrderTypeNumberAsc() {
		List<CustomData> customDataNumberAscending = customDataService.findAllByOrderType("ascending");
		assertEquals(customData1.getNumber(), customDataNumberAscending.get(0).getNumber());
		assertEquals(customData2.getNumber(), customDataNumberAscending.get(1).getNumber());
		assertEquals(customData3.getNumber(), customDataNumberAscending.get(2).getNumber());
		assertEquals(customData4.getNumber(), customDataNumberAscending.get(3).getNumber());
		assertEquals(customData5.getNumber(), customDataNumberAscending.get(4).getNumber());
	}

	@Test
	public void findAllByOrderWithNoParameter() {
		List<CustomData> customDataNumberAscending = customDataService.findAllByOrderType(null);
		assertEquals(customData1.getNumber(), customDataNumberAscending.get(0).getNumber());
		assertEquals(customData2.getNumber(), customDataNumberAscending.get(1).getNumber());
		assertEquals(customData3.getNumber(), customDataNumberAscending.get(2).getNumber());
		assertEquals(customData4.getNumber(), customDataNumberAscending.get(3).getNumber());
		assertEquals(customData5.getNumber(), customDataNumberAscending.get(4).getNumber());
	}

	@After
	public void cleanup() {
		customDataRepository.delete(customData1);
		customDataRepository.delete(customData2);
		customDataRepository.delete(customData3);
		customDataRepository.delete(customData4);
		customDataRepository.delete(customData5);
	}

}
