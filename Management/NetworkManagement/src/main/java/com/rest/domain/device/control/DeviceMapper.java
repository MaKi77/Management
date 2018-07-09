package com.rest.domain.device.control;

import java.util.stream.Collectors;

import javax.inject.Inject;

import com.api.networkmanagement.DeviceDTO;
import com.rest.domain.device.entity.DeviceEntity;
import com.rest.domain.device.entity.DeviceType;

public class DeviceMapper {
	
	@Inject
	private CardMapper cardMapper;

	public DeviceEntity mapToEntity(DeviceDTO deviceDTO) {
		DeviceEntity deviceEntity = new DeviceEntity();
		deviceEntity.setId(deviceDTO.getId());
		deviceEntity.setIdentifier(deviceDTO.getIdentifier());
		deviceEntity.setType(DeviceType.fromString(deviceDTO.getType()));
		deviceEntity.setConnectedCards(deviceDTO.getConnectedCards()
				.stream()
				.map(cardMapper::mapToEntity)
				.collect(Collectors.toList()));
		return deviceEntity;
	}
	
	public DeviceDTO mapToDTO(DeviceEntity deviceEntity) {
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setId(deviceEntity.getId());
		deviceDTO.setIdentifier(deviceEntity.getIdentifier());
		deviceDTO.setType(deviceEntity.getType().toString());
		deviceDTO.setConnectedCards(deviceEntity.getConnectedCards()
				.stream()
				.map(cardMapper::mapToDTO)
				.collect(Collectors.toList()));
		return deviceDTO;
	}
	
}