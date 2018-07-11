package com.rest.domain.device.entity;

import java.util.Arrays;

public enum DeviceType {
	ROUTER, 
	MODEM, 
	SWITCHER, 
	REPEATER, 
	BRIDGE;

	public static DeviceType fromString(String type) {
		for(DeviceType enumValue : DeviceType.values()) {
			if(enumValue.toString().equalsIgnoreCase(type)) {
				return enumValue;
			}
		}
		throw new IllegalArgumentException(
				type + " is not valid device type. Supported values are: " + Arrays.asList(DeviceType.values()));
	}
};