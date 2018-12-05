package io.cts.msa.om.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(reason="Could not query customer record from DB",code=HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomerRecordQueryException extends RuntimeException {

	String message;

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return message;
	}

}
