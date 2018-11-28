package io.cts.msa.om.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Error connecting to Item Service", code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ItemServiceCommunicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public ItemServiceCommunicationException(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

}
