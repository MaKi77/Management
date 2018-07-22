package com.api.networkmanagement;

public class CardDTO {
	
	private int id;
	private int typeId;
	private String identifier;
	private String type;
	private int serialNumber;
	private int partNumber;
	
	public CardDTO() {}
	
	private CardDTO(CardDTOBuilder builder) {
		this.id = builder.id;
		this.typeId = builder.typeId;
		this.identifier = builder.identifier;
		this.type = builder.type;
		this.serialNumber = builder.serialNumber;
		this.partNumber = builder.partNumber;
	}
	
	public int getId() {
		return id;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public int getPartNumber() {
		return partNumber;
	}
	
	public static CardDTOBuilder builder() {
		return new CardDTOBuilder();
	}
	
	public static class CardDTOBuilder {
		
		private int id;
		private int typeId;
		private String identifier;
		private String type;
		private int serialNumber;
		private int partNumber;
		
		public CardDTOBuilder withId(int id) {
			this.id = id;
			return this;
		}
		
		public CardDTOBuilder withTypeId(int typeId) {
			this.typeId = typeId;
			return this;
		}
		
		
		public CardDTOBuilder withIdentifier(String identifier) {
			this.identifier = identifier;
			return this;
		}
		
		public CardDTOBuilder withType(String type) {
			this.type = type;
			return this;
		}
		
		public CardDTOBuilder withSerialNumber(int serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}
		
		public CardDTOBuilder withPartNumber(int partNumber) {
			this.partNumber = partNumber;
			return this;
		}
		
		public CardDTO build() {
			return new CardDTO(this);
		}
		
	}
	
}

