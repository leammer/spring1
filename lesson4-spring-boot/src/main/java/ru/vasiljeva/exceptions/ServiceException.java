package ru.vasiljeva.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ServiceException extends RuntimeException {
	private ExceptionType code;
	private Object[] args;

	public ServiceException(ExceptionType code, Object... args) {
		super(code.toString());
		this.code = code;
		this.args = args;
	}
}
