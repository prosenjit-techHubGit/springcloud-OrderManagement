package io.cts.msa.om.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Item does not exist in the system";
	}

}
