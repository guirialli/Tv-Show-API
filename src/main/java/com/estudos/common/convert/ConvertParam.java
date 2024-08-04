package com.estudos.common.convert;

import org.springframework.stereotype.Service;

@Service
public class ConvertParam {
	
	public String parser(String param) {
		return param.replaceAll(" ", "+").toLowerCase();
	}
}
