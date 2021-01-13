package br.com.desafio.fastTrack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceUnprocessableEntityException extends RuntimeException {
	public ResourceUnprocessableEntityException(String message) {
		super(message);
	}
}
