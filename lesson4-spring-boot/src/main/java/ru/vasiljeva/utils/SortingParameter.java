package ru.vasiljeva.utils;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public enum SortingParameter {
	//@formatter:off
	PRICE_UP("priceUp", "cost", Sort.Direction.ASC), 
	PRICE_DOWN("priceDown", "cost", Sort.Direction.DESC),
	TITLE_UP("titleUp", "name", Sort.Direction.ASC), 
	TITLE_DOWN("titleDown", "name", Sort.Direction.DESC),
	UNSORTED("", Sort.unsorted());
	//@formatter:on

	private final String value;
	private final Sort sort;

	private SortingParameter(String value, Sort sort) {
		this.value = value;
		this.sort = sort;
	}

	private SortingParameter(String value, String field, Direction direction) {
		this.value = value;
		this.sort = Sort.by(direction, field);
	}

	public String getValue() {
		return value;
	}

	public Sort getSort() {
		return sort;
	}

	public static SortingParameter getSortingParameter(String type) {
		Stream<SortingParameter> typeStream = Arrays.stream(SortingParameter.values());
		//@formatter:off
		SortingParameter value = typeStream
				.filter((s) -> s.getValue().equals(type))
				.findAny()
				.orElse(UNSORTED);
		//@formatter:on
		return value;
	}
}
