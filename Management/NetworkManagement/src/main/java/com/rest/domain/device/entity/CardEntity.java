package com.rest.domain.device.entity;

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
	
	public void updateCard(CardEntity cardEntity) {
		this.identifier = cardEntity.getIdentifier();
		this.type = cardEntity.getType();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) 
			return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof CardEntity)) 
	    	return false;
	    CardEntity otherCardEntity = (CardEntity)other;
	    if(otherCardEntity.getId() == this.getId())
	    	return true;
	    return false;
	}
	
}