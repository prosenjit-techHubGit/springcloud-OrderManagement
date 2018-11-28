package io.cts.msa.om.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason="Customer recoord not in system",code=HttpStatus.EXPECTATION_FAILED)
public class CustomerCreateException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Customer id does not exist in the system";
	}


}
