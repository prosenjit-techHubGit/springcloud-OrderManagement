package io.cts.msa.om.customer.exception;

public class CustomerMessageSendException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomerMessageSendException(String message) {
		this.message = message;
		
	}

	public String getMessage() {
		return "Could not send message to Rabbit Exchange:: Reason -  " + message;
	}

}
