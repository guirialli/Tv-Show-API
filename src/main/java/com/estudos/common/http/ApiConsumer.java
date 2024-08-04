package com.estudos.common.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class ApiConsumer {
	public String getData(String url) {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(url)).setHeader("Content-Type", "application/json").build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException e) {
			throw new RuntimeException("Error to get in " + url + "!");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
