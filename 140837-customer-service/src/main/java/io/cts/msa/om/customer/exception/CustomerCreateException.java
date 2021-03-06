package io.cts.msa.om.customer.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(reason="Could not create Customer record in DB",code=HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomerCreateException extends RuntimeException{
	
	//@Value("${customer.exception.message}")
	String message;
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return message;
	}


}
