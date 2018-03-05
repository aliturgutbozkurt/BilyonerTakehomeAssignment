package com.bilyoner.microservices.homework.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bilyoner.microservices.homework.domain.CustomData;

@Repository
public interface CustomDataRepository extends MongoRepository<CustomData, String> {

	CustomData findByNumber(Long number);

	CustomData findTopByOrderByNumberDesc();

	CustomData findTopByOrderByNumberAsc();
	
	List<CustomData> findAllByOrderByNumberDesc();
	
	List<CustomData> findAllByOrderByNumberAsc();
}
