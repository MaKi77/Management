package com.rest.domain.device.control;

import com.api.networkmanagement.CardDTO;
import com.rest.domain.device.entity.CardEntity;
import com.rest.domain.device.entity.CardType;

public class CardMapper {
	public CardEntity mapToEntity(CardDTO cardDTO) {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setId(cardDTO.getId());
		cardEntity.setIdentifier(cardDTO.getIdentifier());
		cardEntity.setType(CardType.fromString(cardDTO.getType()));
		return cardEntity;
	}
	
	public CardDTO mapToDTO(CardEntity cardEntity) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setId(cardEntity.getId());
		cardDTO.setIdentifier(cardEntity.getIdentifier());
		cardDTO.setType(cardEntity.getType().toString());
		return cardDTO;
	}
}
