package com.minbrat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minbrat.Service.RegistrationService;
import com.minbrat.model.Registration;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService regService;

	@PostMapping("/store")
	public ResponseEntity<Object> getProvderData(@RequestBody Registration registration) {
		int count = regService.saveData(registration);
		ResponseEntity<Object> responseEntity = null;
		if (count > 0)
			responseEntity = new ResponseEntity<Object>("Details Store Sucessfully", HttpStatus.OK);
		else {
			responseEntity = new ResponseEntity<Object>("Sorry we are unable to store data", HttpStatus.BAD_REQUEST);

		}
		return responseEntity;

	}

	@RequestMapping("/getData")
	public ResponseEntity<Object> getData(@RequestParam(required = false) String name,
			@RequestParam(required = false) String pinCode, @RequestParam(required = false) Date startDate,
			@RequestParam(required = false) Date endDate) {
		List<Registration> data = regService.getData(name, pinCode, startDate, endDate);
		ResponseEntity<Object> responseEntity = null;

		if (data != null)
			responseEntity = new ResponseEntity<Object>(data, HttpStatus.OK);
		else {
			responseEntity = new ResponseEntity<Object>("Sorry we are unable to store data", HttpStatus.BAD_REQUEST);

		}
		return responseEntity;

	}

	@RequestMapping("/getPermutation")
	public ResponseEntity<Object> getPermutationData(@RequestParam(required = false) String name) {
		List<Registration> data = regService.getPermutationData(name);

		ResponseEntity<Object> responseEntity = null;

		if (data != null)
			responseEntity = new ResponseEntity<Object>(data, HttpStatus.OK);
		else {
			responseEntity = new ResponseEntity<Object>("Sorry we are unable to store data", HttpStatus.BAD_REQUEST);

		}
		return responseEntity;
	}

	@RequestMapping("/get")
	public ResponseEntity<Object> getAll() {

		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>("Hi Hello", HttpStatus.OK);
		return responseEntity;

	}
}
