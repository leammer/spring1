package ru.vasiljeva.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDto {
	private String message;
	private String cause;
}
