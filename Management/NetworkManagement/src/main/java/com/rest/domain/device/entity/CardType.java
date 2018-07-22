package com.rest.domain.device.entity;

import java.text.MessageFormat;
import java.util.Arrays;

public enum CardType {
	
	TYPE_A, 
	TYPE_B, 
	TYPE_C;
	
	static final String ILLEGAL_TYPE_MESSAGE = "{0} is not a valid card type. Supported types are: {1}";

	public static CardType fromString(String type) {
		for (CardType enumValue : CardType.values()) {
			if (enumValue.toString().equalsIgnoreCase(type)) {
				return enumValue;
			}
		}

		if (isTypeNotSupplied(type)) {
			return null;
		}
		
		throw new IllegalArgumentException(MessageFormat.format(ILLEGAL_TYPE_MESSAGE, type, Arrays.asList(CardType.values())));
	}

	static private boolean isTypeNotSupplied(String type) {
		return type == null;
	}

}
