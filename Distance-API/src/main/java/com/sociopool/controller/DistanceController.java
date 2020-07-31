package com.sociopool.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sociopool.model.Distance;
import com.sociopool.service.DistanceService;

@RestController
@RequestMapping(value = "/home")

public class DistanceController {
	@Autowired
	private DistanceService distanceService;

	@RequestMapping(value = "/save", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PostMapping
	public ResponseEntity<String> saveDetails(@RequestBody Distance distance) {
		try {
			distanceService.saveDetails(distance);
			return new ResponseEntity<String>("Details Store Sucessfully", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Sorry we are unable to store data", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/fetchData")
	@GetMapping
	public ResponseEntity<String> getData(@RequestParam String personId,
			@RequestParam(required = false) Timestamp startTime, @RequestParam(required = false) Timestamp endTime) {
		try {
			Integer id = Integer.parseInt(personId);
			int distance = distanceService.findDetails(id, startTime, endTime);
				return new ResponseEntity<String>("He coverd distace is " + distance, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Sorry we are unable to store data", HttpStatus.BAD_REQUEST);
		}
	}
}
