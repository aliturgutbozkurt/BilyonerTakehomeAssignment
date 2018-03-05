package com.bilyoner.microservices.homework.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bilyoner.microservices.contracts.CustomDataServiceContract;
import com.bilyoner.microservices.homework.domain.CustomData;
import com.bilyoner.microservices.homework.service.CustomDataService;

@RestController
public class CustomDataResource implements CustomDataServiceContract {

	private final CustomDataService customDataService;

	public CustomDataResource(CustomDataService customDataService) {
		this.customDataService = customDataService;
	}

	@RequestMapping("/number-service")
	public String testNumberService() {
		return "test";
	}

	@Override
	public ResponseEntity<?> createCustomData(@RequestBody CustomData customData) {

		return ResponseEntity.status(HttpStatus.CREATED).body(customDataService.save(customData));
	}

	@Override
	public ResponseEntity<CustomData> findCustomDataByNumber(@PathVariable Long number) {
		
		return ResponseEntity.status(HttpStatus.OK).body(customDataService.findByNumber(number));
		
	}

	@Override
	public ResponseEntity<CustomData> findCustomDataByMaximumNumber(Long number) {
		
		return ResponseEntity.status(HttpStatus.OK).body(customDataService.findTopByMaximumNumber());
	}

	@Override
	public ResponseEntity<CustomData> findCustomDataByMinumumNumber(Long number) {
		
		return ResponseEntity.status(HttpStatus.OK).body(customDataService.findTopByMinumumNumber());
	}

	@Override
	public ResponseEntity<?> deleteCustomDataByNumber(@PathVariable Long number) {

		customDataService.delete(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	public ResponseEntity<List<CustomData>> findAllCustomDatas(@RequestParam(required=false,value="orderType",defaultValue="ascending") String orderType) {
		
		return ResponseEntity.status(HttpStatus.OK).body(customDataService.findAllByOrderType(orderType));
	}
	

	
	
}
