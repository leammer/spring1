package ru.vasiljeva.dto;

public enum SortValue {
	PRICE_UP("priceUp"), 
	PRICE_DOWN("priceDown");

	private final String value;

	private SortValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
