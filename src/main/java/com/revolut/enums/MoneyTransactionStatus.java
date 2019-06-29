package com.revolut.enums;

public enum MoneyTransactionStatus {

success("success"),pending("pending");
	
	private final String value;

	private MoneyTransactionStatus(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	
	public static MoneyTransactionStatus getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (MoneyTransactionStatus v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}

}
