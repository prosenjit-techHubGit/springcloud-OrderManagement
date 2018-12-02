package io.cts.msa.om.order.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RefreshScope
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidItemException extends RuntimeException {

	@Value("${item.exception.message}")
	private String message;

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return message;
	}

	public InvalidItemException() {
	}

}
