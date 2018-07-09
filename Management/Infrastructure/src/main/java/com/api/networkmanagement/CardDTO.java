package com.api.networkmanagement;

public class CardDTO {
	private int id;
	private int typId;
	private String identifier;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTypId() {
		return typId;
	}
	
	public void setTypId(int typId) {
		this.typId = typId;
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
}

