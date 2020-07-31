package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.model.Request;
import com.model.RequestApi;

@RestController
public class ApiController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/home")
	@GetMapping(consumes   = { MediaType.APPLICATION_JSON_VALUE })

	public List<Request> getComparision() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		List<Request> response = null;
		String response1 = restTemplate
				.exchange("https://jsonplaceholder.typicode.com/users", HttpMethod.GET, httpEntity, String.class)
				.getBody();
		List<Request> request1 = getRequest(response1);
		String response2 = restTemplate
				.exchange("http://jsonplaceholder.typicode.com/posts", HttpMethod.GET, httpEntity, String.class)
				.getBody();
		List<RequestApi> request2 = getRequestApi(response2);

		if (request1 != null && request2 != null)
			response = dataAppendingProcess(request1, request2);
		return response;

	}

	private List<Request> getRequest(String response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			TypeFactory typeFactory = mapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(List.class, Request.class);
			return mapper.readValue(response, collectionType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private List<RequestApi> getRequestApi(String response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			TypeFactory typeFactory = mapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(List.class, RequestApi.class);
			return mapper.readValue(response, collectionType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private List<Request> dataAppendingProcess(List<Request> req1, List<RequestApi> req2) {
		List<Request> data = new ArrayList<>();
		for (Request request : req1) {
			for (RequestApi request2 : req2) {
				if (request.getId() == request2.getId()) {
					request.setBody(request2.getBody());
					request.setTitle(request2.getTitle());
					data.add(request);
				}
			}
		}
		return data;
	}
}
