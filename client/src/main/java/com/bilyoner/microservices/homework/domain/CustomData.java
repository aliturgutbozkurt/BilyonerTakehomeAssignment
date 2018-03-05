package com.bilyoner.microservices.homework.domain;

public class CustomData extends AbstractAuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
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