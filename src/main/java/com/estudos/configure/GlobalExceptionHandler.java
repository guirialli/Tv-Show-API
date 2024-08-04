package com.estudos.configure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estudos.common.dto.ResponseError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseError> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		var err = new ResponseError(400, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ResponseError> handleJsonMappingException(JsonMappingException ex) {
		var err = new ResponseError(500, "Internal Error");
		System.err.println(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<ResponseError> handleJsonProcessingException(JsonProcessingException ex) {
		var err = new ResponseError(500, "Internal Error");
		System.err.println(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

}
