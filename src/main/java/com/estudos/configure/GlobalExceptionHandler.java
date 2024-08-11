package com.estudos.configure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estudos.common.dto.ResponseError;
import com.estudos.common.http.exceptions.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseError> handleNotFoundException(BadRequestException ex){
		var err = new ResponseError(ex.getStatus(), ex.getMessage());
		return ResponseEntity.status(ex.getStatus()).body(err);

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseError> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		var err = new ResponseError(400, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ResponseError> handleNumberFormatException(NumberFormatException ex){
		var err = new ResponseError(400, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
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
