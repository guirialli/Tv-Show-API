package com.estudos.common.http.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final int status;

	public BadRequestException(String msg) {
		super(msg);
		this.status = 400;
	}
	
	public BadRequestException(String msg, int status) {
		super(msg);
		this.status = status;
	}

}
