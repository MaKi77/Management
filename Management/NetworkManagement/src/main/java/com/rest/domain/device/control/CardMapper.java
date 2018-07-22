package com.rest.domain.device.control;

import java.util.List;
import java.util.stream.Collectors;

import com.api.networkmanagement.CardDTO;
import com.rest.domain.device.entity.CardEntity;
import com.rest.domain.device.entity.CardType;

public class CardMapper {
	
	public CardEntity mapToEntity(CardDTO cardDTO) {
		return CardEntity.builder()
				.withId(cardDTO.getId())
				.withIdentifier(cardDTO.getIdentifier())
				.withType(CardType.fromString(cardDTO.getType()))
				.withTypeId(cardDTO.getTypeId())
				.withSerialNumber(cardDTO.getSerialNumber())
				.withPartNumber(cardDTO.getPartNumber())
				.build();
	}
	
	public List<CardEntity> mapToEntity(List<CardDTO> cardDTOList) {
		return cardDTOList.stream()
				.map(this::mapToEntity)
				.collect(Collectors.toList());
	}
	
	public CardDTO mapToDTO(CardEntity cardEntity) {
		return CardDTO.builder()
				.withId(cardEntity.getId())
				.withIdentifier(cardEntity.getIdentifier())
				.withType(cardEntity.getType().toString())
				.withTypeId(cardEntity.getTypeId())
				.withSerialNumber(cardEntity.getSerialNumber())
				.withPartNumber(cardEntity.getPartNumber())
				.build();
	}
	
	public List<CardDTO> mapToDTO(List<CardEntity> cardEntityList) {
		return cardEntityList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
	
}
