package ru.vasiljeva.exceptions;

public enum ExceptionType {
	//@formatter:off
	BAD_REQUEST("ERROR-400"),
	FORBIDDEN("ERROR-403"),
	NOT_FOUND("ERROR-404"),
	METHOD_NOT_ALLOWED("ERRPR-405"),
	ALREADY_EXISTS("ERROR_409"),
	DEFAULT("ERROR-500");
	//@formatter:on

	private String code;

	private ExceptionType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
