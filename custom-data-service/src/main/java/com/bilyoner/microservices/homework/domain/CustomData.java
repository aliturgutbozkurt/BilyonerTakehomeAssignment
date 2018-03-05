package com.bilyoner.microservices.homework.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customDatas")
public class CustomData extends AbstractAuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private Long number;

    public CustomData() {
		super();
	}
    
	public CustomData(String id,Long number) {
		super();
		this.id=id;
		this.number = number;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}