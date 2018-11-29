package io.cts.msa.om.order.exception;

public class OrderSaveToDBException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public OrderSaveToDBException(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

}
