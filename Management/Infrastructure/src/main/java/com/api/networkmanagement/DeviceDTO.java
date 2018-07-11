package com.api.networkmanagement;

import java.util.List;

public class DeviceDTO {
	private int id;	
	private int typeId;
	private String identifier;
	private String type;
	private int serialNumber;
	private int partNumber;
	private List<CardDTO> connectedCards;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
	
	public List<CardDTO> getConnectedCards() {
		return connectedCards;
	}

	public void setConnectedCards(List<CardDTO> connectedCards) {
		this.connectedCards = connectedCards;
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

