package com.bilyoner.microservices.homework.service;

import java.util.List;

import com.bilyoner.microservices.homework.domain.CustomData;
import com.bilyoner.microservices.homework.exception.NoSuchCustomDataWithNumberException;

public interface CustomDataService {
	
	CustomData save(CustomData customData);
	
	CustomData findByNumber(Long number);
	
	CustomData findTopByMaximumNumber();
	
	CustomData findTopByMinumumNumber();
	
	void delete(Long number) throws NoSuchCustomDataWithNumberException;
	
	List<CustomData> findAllByOrderType(String orderType);

}
