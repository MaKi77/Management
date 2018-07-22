package com.api.networkmanagement;

import java.util.ArrayList;
import java.util.List;

public class DeviceDTO {
	private int id;	
	private int typeId;
	private String identifier;
	private String type;
	private int serialNumber;
	private int partNumber;
	private List<CardDTO> connectedCards;
	
	public DeviceDTO() {}
	
	private DeviceDTO(DeviceDTOBuilder builder) {
		this.id = builder.id;
		this.typeId = builder.typeId;
		this.identifier = builder.identifier;
		this.type = builder.type;
		this.serialNumber = builder.serialNumber;
		this.partNumber = builder.partNumber;
		this.connectedCards = builder.connectedCards;
	}

	public int getId() {
		return id;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public int getTypeId() {
		return typeId;
	}
	
	public String getType() {
		return type;
	}
	
	public List<CardDTO> getConnectedCards() {
		return connectedCards;
	}

	public int getSerialNumber() {
		return serialNumber;
	}
	
	public int getPartNumber() {
		return partNumber;
	}
	
	public static DeviceDTOBuilder builder() {
		return new DeviceDTOBuilder();
	}
	
	public static class DeviceDTOBuilder {
		
		private int id;	
		private int typeId;
		private String identifier;
		private String type;
		private int serialNumber;
		private int partNumber;
		private List<CardDTO> connectedCards;
		
		public DeviceDTOBuilder withId(int id) {
			this.id = id;
			return this;
		}
		
		public  DeviceDTOBuilder withTypeId(int typeId) {
			this.typeId = typeId;
			return this;
		}
		
		public DeviceDTOBuilder withIdentifier(String identifier) {
			this.identifier = identifier;
			return this;
		}
		
		public DeviceDTOBuilder withType(String type) {
			this.type = type;
			return this;
		}
		
		public DeviceDTOBuilder withSerialNumber(int serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}
		
		public DeviceDTOBuilder withPartNumber(int partNumber) {
			this.partNumber = partNumber;
			return this;
		}
		
		public DeviceDTOBuilder withCards(List<CardDTO> connectedCards) {
			this.connectedCards = new ArrayList<>(connectedCards);
			return this;
		}
		
		public DeviceDTO build() {
			return new DeviceDTO(this);
		}
		
	}
	
}

