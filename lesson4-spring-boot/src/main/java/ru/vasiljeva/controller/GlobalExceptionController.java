package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.ERROR_CAUSE_FORMAT;
import static ru.vasiljeva.utils.AppConstants.ERROR_CODE_FORMAT;
import static ru.vasiljeva.utils.AppConstants.ERROR_MESSAGE_FORMAT;
import static ru.vasiljeva.utils.AppConstants.ERROR_STATUS_FORMAT;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ru.vasiljeva.dto.ErrorDto;
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;

@RestControllerAdvice
public class GlobalExceptionController {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

	@Autowired
	private ResourceBundleMessageSource resourceBundle;

	@Autowired
	private ObjectMapper mapper;

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorDto> handleServiceException(ServiceException e) {
		log.error("Caught an ServiceException", e);

		ErrorDto error = createErrorDto(e.getCode(), e.getArgs());
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<ErrorDto> handleHttpServerErrorException(HttpServerErrorException ex) {
		log.error("Caught an Exception", ex);

		String errorMsg = "";
		try {
			ObjectNode node = mapper.readValue(ex.getResponseBodyAsByteArray(), ObjectNode.class);
			if (node.has("cause")) {
				errorMsg += node.get("cause").asText() + ": ";
			}
			if (node.has("error_description")) {
				errorMsg += node.get("error_description").asText();
			}

			if (errorMsg.isEmpty()) {
				errorMsg = "Unknown format of MQTT error: " + ex.getResponseBodyAsByteArray();
			}
		} catch (IOException e) {
			log.error("MQTT error parser exception", e);
			errorMsg = "Exception when trying to parse MQTT error: " + e.getMessage();
		}

		ErrorDto error = createErrorDto(ExceptionType.BAD_REQUEST, errorMsg);
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex) {
		ErrorDto error = createErrorDto(ExceptionType.FORBIDDEN, ex.getMessage());
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handleException(Exception ex) {
		log.error("Caught an Exception", ex);

		String errorMsg = ex.getMessage();
		ErrorDto error = createErrorDto(ExceptionType.DEFAULT, errorMsg);
		return new ResponseEntity<>(error, error.getStatus());
	}

	private ErrorDto createErrorDto(ExceptionType type, Object... args) {
		ErrorDto error = new ErrorDto();

		Locale locale = LocaleContextHolder.getLocale();
		String typeCode = type.getCode();

		error.setCode(resourceBundle.getMessage(String.format(ERROR_CODE_FORMAT, typeCode), null, locale));
		String statusStr = resourceBundle.getMessage(String.format(ERROR_STATUS_FORMAT, typeCode), null, locale);
		error.setStatus(HttpStatus.valueOf(statusStr));
		error.setMessage(resourceBundle.getMessage(String.format(ERROR_MESSAGE_FORMAT, typeCode), args, locale));
		error.setCause(resourceBundle.getMessage(String.format(ERROR_CAUSE_FORMAT, typeCode), args, locale));

		return error;
	}
}
