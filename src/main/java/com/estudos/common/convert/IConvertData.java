package com.estudos.common.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IConvertData {
	public <T> T parser(String json, Class<T> dest) throws JsonMappingException, JsonProcessingException;
}
