package com.rest.domain.device.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String identifier;
	private CardType type;
	private int typeId;
	private int serialNumber;
	private int partNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public CardType getType() {
		return type;
	}
	public void setType(CardType type) {
		this.type = type;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
	
	public void assignIdentifierForCard(String deviceIdentifier, int typeId) {
		setIdentifier(deviceIdentifier + ":" + type + ":" + typeId);
		setTypeId(typeId);
	}
	
	public void updateCard(CardEntity cardEntity) {	
		if(!isValid(cardEntity)) {
			throw new IllegalArgumentException("Wrong indentifier, type or typeId for this device");
		}
		
		serialNumber = cardEntity.getSerialNumber();
		partNumber = cardEntity.getPartNumber();
	}
	
	private boolean isValid(CardEntity cardEntity) {
		return hasTheSameType(cardEntity)
				&& hasTheSameTypeId(cardEntity)
				&& hasTheSameIdentfier(cardEntity);
	}
	
	boolean hasTheSameType(CardEntity cardEntity) {
		return cardEntity.getType() == this.getType();
	}
	
	boolean hasTheSameTypeId(CardEntity cardEntity) {
		return cardEntity.getTypeId() == this.getTypeId();
	}
	
	boolean hasTheSameIdentfier(CardEntity cardEntity) {
		return cardEntity.getIdentifier().equals(this.getIdentifier());
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) { 
			return false;
		} if (other == this) { 
	    	return true;
		} if (!(other instanceof CardEntity)) {
	    	return false;
		}
	    CardEntity otherCardEntity = (CardEntity)other;
	    if(otherCardEntity.getIdentifier() == null) {
	    	return false;
	    } if(otherCardEntity.getIdentifier().equals(getIdentifier())) {
	    	return true;
	    }
	    return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(identifier);
	}
	
}