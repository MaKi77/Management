package com.rest.domain.device.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class DeviceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private int typeId;
	private String identifier;
	private DeviceType type;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "device_id")
	private List<CardEntity> connectedCards;
	
	public DeviceEntity() {
		connectedCards = new ArrayList<>();
	}

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
	
	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}
	
	public List<CardEntity> getConnectedCards() {
		return connectedCards;
	}

	public void setConnectedCards(List<CardEntity> connectedCards) {
		this.connectedCards.clear();
		this.connectedCards.addAll(connectedCards);
	}

	public void updateDevice(DeviceEntity deviceEntity) {
		this.identifier = deviceEntity.getIdentifier();
		this.type = deviceEntity.getType();
		updateCards(deviceEntity.getConnectedCards());	
	}
	
	private void updateCards(List<CardEntity> updatedCards) {
		connectedCards.retainAll(updatedCards);
		boolean isNew = true;
		for(CardEntity updated : updatedCards) {
			isNew = true;
			for(CardEntity connected : connectedCards) {
				if(updated.equals(connected)) {
					connected.updateCard(updated);
					isNew = false;
					break;
				}
			}
			if(isNew) {
				connectedCards.add(updated);
			}
		}
	}
	
	@Override
	public String toString() {
		return "[" + id + "] - " + identifier;
	}

}
