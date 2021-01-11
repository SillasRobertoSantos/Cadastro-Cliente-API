package br.com.desafio.fastTrack.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerNotFoundException(ResourceNotFoundException rfnException) {
		ResourceNotFoundDetails rnfDetalis = ResourceNotFoundDetails.builder()

				.withTitle("Resource Not Found")
				.withStatus(HttpStatus.NOT_FOUND.value())
				.withDetail(rfnException.getMessage())
				.withTimestamp(new Date().getTime())
				.withDeveloperMessage(rfnException.getClass().getName()).build();

		return new ResponseEntity<>(rnfDetalis, HttpStatus.NOT_FOUND);

	}

}
