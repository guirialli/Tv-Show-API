package com.estudos.common.http.exceptions;

public class NotFoundException extends BadRequestException{
	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg) {
		super(msg, 404);
	}
}
