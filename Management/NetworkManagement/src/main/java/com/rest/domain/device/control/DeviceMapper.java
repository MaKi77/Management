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
		return DeviceEntity.builder()
				.withId(deviceDTO.getId())
				.withIdentifier(deviceDTO.getIdentifier())
				.withType(DeviceType.fromString(deviceDTO.getType()))
				.withTypeId(deviceDTO.getTypeId())
				.withSerialNumber(deviceDTO.getSerialNumber())
				.withPartNumber(deviceDTO.getPartNumber())
				.withCards(cardMapper.mapToEntity(deviceDTO.getConnectedCards()))
				.build();
	}
	
	public DeviceDTO mapToDTO(DeviceEntity deviceEntity) {
		return DeviceDTO.builder()
				.withId(deviceEntity.getId())
				.withIdentifier(deviceEntity.getIdentifier())
				.withType(deviceEntity.getType().toString())
				.withTypeId(deviceEntity.getTypeId())
				.withSerialNumber(deviceEntity.getSerialNumber())
				.withPartNumber(deviceEntity.getPartNumber())
				.withCards(cardMapper.mapToDTO(deviceEntity.getConnectedCards()))
				.build();
	}
	
}