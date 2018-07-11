package com.rest.domain.device.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections4.map.HashedMap;

@Entity
@XmlRootElement
public class DeviceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int typeId;
	private String identifier;
	private DeviceType type;
	private int serialNumber;
	private int partNumber;

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

	public void assignIdentifiers(int typeId) {
		assignIdentifierForDevice(typeId);
		assignIdentifiersForConnectedCards();
	}
	
	private void assignIdentifierForDevice(int typeId) {
		setTypeId(typeId);
		setIdentifier(getType() + "_" + typeId);
	}
	
	private void assignIdentifiersForConnectedCards() {
		HashedMap<CardType, Integer> nextCardNumber = new HashedMap<>();
		Arrays.asList(CardType.values()).forEach(type -> nextCardNumber.put(type, 1));
	
		for (CardEntity cardEntity : connectedCards) {
			CardType cardType = cardEntity.getType();
			int cardTypeId = nextCardNumber.get(cardEntity.getType());
			cardEntity.assignIdentifierForCard(getIdentifier(), cardTypeId);
			nextCardNumber.put(cardType, cardTypeId + 1);
		}
	}
	
	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
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

	public List<CardEntity> getConnectedCards() {
		return connectedCards;
	}

	public void setConnectedCards(List<CardEntity> connectedCards) {
		this.connectedCards.clear();
		this.connectedCards.addAll(connectedCards);
	}

	public void updateDevice(DeviceEntity deviceEntity) {
		if(!isValid(deviceEntity)) {
			throw new IllegalArgumentException("Wrong identifier, type or typeId for this device");
		}
				
		serialNumber = deviceEntity.getSerialNumber();
		partNumber = deviceEntity.getPartNumber();
		updateCards(deviceEntity.getConnectedCards());
	}
	
	private boolean isValid(DeviceEntity deviceEntity) {
		return hasTheSameType(deviceEntity) 
				&& hasTheSameTypeId(deviceEntity) 
				&& hasTheSameIdentifer(deviceEntity);
	}

	private boolean hasTheSameIdentifer(DeviceEntity deviceEntity) {
		return deviceEntity.getIdentifier().equals(this.getIdentifier());
	}

	private boolean hasTheSameTypeId(DeviceEntity deviceEntity) {
		return deviceEntity.getTypeId() == this.getTypeId();
	}

	private boolean hasTheSameType(DeviceEntity deviceEntity) {
		return deviceEntity.getType() == this.getType();
	}

	private void updateCards(List<CardEntity> updatedCards) {
		connectedCards.retainAll(updatedCards);

		for (CardEntity updated : updatedCards) {
			boolean isNew = true;
			for (CardEntity connected : connectedCards) {
				if (updated.equals(connected)) {
					connected.updateCard(updated);
					isNew = false;
					break;
				}
			}
			if (isNew) {
				int newCardTypeId = findMinAvailableTypeIdForCard(updated);
				updated.assignIdentifierForCard(getIdentifier(), newCardTypeId);
				connectedCards.add(updated);
			}
		}
	}
	
	int findMinAvailableTypeIdForCard(CardEntity cardEntity) {
		Set<Integer> alreadyUsedTypeIds = connectedCards.stream()
				.filter(card -> card.getType() == cardEntity.getType())
				.mapToInt(card -> card.getTypeId())
				.boxed()
				.collect(Collectors.toSet());

		int newTypeId = 1;
		while (alreadyUsedTypeIds.contains(newTypeId)) {
			newTypeId++;
		}
		return newTypeId;
	}

	@Override
	public String toString() {
		return "[" + id + "] - " + identifier;
	}

}
