package com.rest.domain.device.entity;

import java.util.Arrays;

public enum CardType {
	TYPE_A, TYPE_B, TYPE_C;

	public static CardType fromString(String type) {

		for (CardType enumValue : CardType.values()) {
			if (enumValue.toString().equalsIgnoreCase(type)) {
				return enumValue;
			}
		}

		if (isTypeNotSupplied(type)) {
			return null;
		} else {
			throw new IllegalArgumentException(
					type + " is not a valid card type. Supported values are: " + Arrays.asList(CardType.values()));
		}
	}

	static private boolean isTypeNotSupplied(String type) {
		return type == null;
	}
}
