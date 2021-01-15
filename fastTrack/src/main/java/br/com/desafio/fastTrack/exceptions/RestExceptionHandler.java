package br.com.desafio.fastTrack.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerNotFoundException(ResourceNotFoundException rfnException) {
		ResourceNotFoundDetails rnfDetalis = ResourceNotFoundDetails.builder()

				.withTitle("Resource Not Found").withStatus(HttpStatus.NOT_FOUND.value())
				.withDetail(rfnException.getMessage()).withTimestamp(new Date().getTime())
				.withDeveloperMessage(rfnException.getClass().getName()).build();

		return new ResponseEntity<>(rnfDetalis, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceUnprocessableEntityException.class)
	public ResponseEntity<?> handlerUnprocessableEntityException(ResourceUnprocessableEntityException reuException) {
		ResourceUnprocessableEntityDetails rueDetalis = ResourceUnprocessableEntityDetails.builder()

				.withTitle("Resource Unprocessable Entity").withStatus(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.withDetail(reuException.getMessage()).withTimestamp(new Date().getTime())
				.withDeveloperMessage(reuException.getClass().getName()).build();

		return new ResponseEntity<>(rueDetalis, HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<String> errors = new ArrayList<>();
		fieldErrors.forEach(f -> {

			errors.add(f.getField() + " : " + f.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<FormularioErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {

		FormularioErrorDto f = new FormularioErrorDto("Erro, favor verificar padrão da solicitação " 
		+ "(Verificar se cidade é válida)" );

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(f);

	}

}
