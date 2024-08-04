package com.estudos.common.convert;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConvertData  implements IConvertData{
	
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T>  T parser(String json, Class<T> dest) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(json, dest);
	}

}
