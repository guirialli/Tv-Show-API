package com.estudos.common.dto;


public record ResponseError(int status, String message) {
	public ResponseError(int status, String message) {
		this.status = status;
		this.message = message;
	}

}
