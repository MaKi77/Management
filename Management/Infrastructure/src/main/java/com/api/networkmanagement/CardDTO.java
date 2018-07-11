package com.api.networkmanagement;

public class CardDTO {
	private int id;
	private int typeId;
	private String identifier;
	private String type;
	private int serialNumber;
	private int partNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public int getPartNumber() {
		return partNumber;
	}
	
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	
	
}

