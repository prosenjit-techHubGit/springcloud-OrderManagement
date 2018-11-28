package io.cts.msa.om.customer.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.cts.msa.om.customer.service.messaging.CustomerMessageSender;

@ControllerAdvice
@RestController
public class ServiceResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerCreateException.class)
	public final ResponseEntity<ErrorDetails> handleCustomerCreateException(CustomerCreateException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(CustomerMessageSendException.class)
	public final ResponseEntity<ErrorDetails> handleCustomerMessageSendException(CustomerMessageSendException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
				ex.getBindingResult().getFieldErrors().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
