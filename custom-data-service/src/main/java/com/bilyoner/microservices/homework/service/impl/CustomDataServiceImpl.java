package com.bilyoner.microservices.homework.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilyoner.microservices.homework.domain.CustomData;
import com.bilyoner.microservices.homework.exception.NoSuchCustomDataWithNumberException;
import com.bilyoner.microservices.homework.repository.CustomDataRepository;
import com.bilyoner.microservices.homework.service.CustomDataService;

@Service
public class CustomDataServiceImpl implements CustomDataService {

	private final CustomDataRepository customDataRepository;

	
	public CustomDataServiceImpl(CustomDataRepository customDataRepository) {
		this.customDataRepository = customDataRepository;
	}

	@Override
	public CustomData findByNumber(Long number) {
		return customDataRepository.findByNumber(number);
	}

	@Override
	public CustomData save(CustomData customData) {
		return customDataRepository.save(customData);
	}

	@Override
	public CustomData findTopByMaximumNumber() {
		return customDataRepository.findTopByOrderByNumberDesc();
	}

	@Override
	public CustomData findTopByMinumumNumber() {
		return customDataRepository.findTopByOrderByNumberAsc();
	}

	@Override
	public void delete(Long number) throws NoSuchCustomDataWithNumberException {

		CustomData customData = customDataRepository.findByNumber(number);
		if (customData != null) {
			customDataRepository.delete(customData.getId());
		} else {
			throw new NoSuchCustomDataWithNumberException();
		}
	}

	@Override
	public List<CustomData> findAllByOrderType(String orderType) {
		if (orderType!=null && orderType.equals("descending")) {
			return customDataRepository.findAllByOrderByNumberDesc();
		} else {
			return customDataRepository.findAllByOrderByNumberAsc();
		}

	}

}
