package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Test {
	static RestTemplate restTemplate;

	public static void main(String[] args) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_XML);
	//	headers.add("Accept", "text/xml");
		BufferedReader reader;
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> allLines = Files.readAllLines(Paths.get("E:\\Test.txt"));
			for (String line : allLines) {
				// System.out.println(line);
				buffer.append(line);
			}

			String responses = buffer.toString();
			System.out.println(responses);
			
			HttpEntity<String> httpEntity = new HttpEntity<>(responses,
					getHttpHeader(headers, true));

			// TODO Auto-generated method
			String url = "https://hill-rom--hrctest.cs24.my.salesforce.com/services/Soap/u/35.0";
			restTemplate = getTemplate();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected HttpHeaders getHttpHeader(HttpHeaders headers) {
		return getHttpHeader(headers, false);
	}

	protected static HttpHeaders getHttpHeader(HttpHeaders headers, boolean xmlRequest) {
		headers.add("SOAPAction", "SOAPAction");
		if (headers != null) {
	
			return headers;
		}

		headers = new HttpHeaders();
		if (xmlRequest) {
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
		} else {
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		}
		

		return headers;
	}

	private static RestTemplate getTemplate() {
		return new RestTemplate();
	}
}