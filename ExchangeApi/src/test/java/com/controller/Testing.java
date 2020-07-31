package com.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Testing {

		static RestTemplate restTemplate;

		public static void main(String[] args) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization",
					"Bearer 00D190000001RRa!AQoAQNnqifayM50u0RC_3PuLWj4Rhhu0mWLQWG.PwJ0Cm7_764cVQibYYhREWFKGmFXnr0jr_eKxI8EYrtPH1lMlmtpAWvaZ");
			HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
			// TODO Auto-generated method
			String url = "https://hill-rom--hrctest.cs24.my.salesforce.com/services/data/v20.0/query/?q=SELECT BMCServiceDesk__Status_ID__c,OwnerId,BMCServiceDesk__queueName__c FROM BMCServiceDesk__Incident__c WHERE Name = '00766977'";
			restTemplate=getTemplate();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			System.out.println(response);
		}

		private static RestTemplate getTemplate() {
			restTemplate = new RestTemplate();
			return restTemplate;
		}
	}

