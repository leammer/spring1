package ru.vasiljeva.utils;

import org.springframework.stereotype.Component;

@Component
public final class AppConstants {
	public static final String MVC_CONTROLLER_MAPPING = "/product";
	public static final String REST_PRODUCT_CONTROLLER_MAPPING = "/app/products";
	public static final String REST_CART_CONTROLLER_MAPPING = "/app/cart";
	public static final String REST_PERSONAL_CONTROLLER_MAPPING = "/app/personal";

	public static final String BY_ID = "/{id}";
	public static final String NEW = "/new";

	public static final String MODEL_ATTRIBUTE_ALL_PRODUCTS = "products";
	public static final String MODEL_ATTRIBUTE_PRODUCT = "product";
	public static final String MODEL_ATTRIBUTE_ERROR_MESSAGE = "message";

	/** formats to get error settings */
	public static final String ERROR_CODE_FORMAT = "error.%s.code";
	public static final String ERROR_STATUS_FORMAT = "error.%s.status";
	public static final String ERROR_MESSAGE_FORMAT = "error.%s.message";
	public static final String ERROR_CAUSE_FORMAT = "error.%s.cause";

	private AppConstants() {
	}
}
