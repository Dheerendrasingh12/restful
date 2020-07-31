package com.industrybuying.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

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

import com.industrybuying.model.Truecaller;
import com.industrybuying.service.TrueCallerService;

@RestController
public class TrueController {
	@Autowired
	private TrueCallerService service;

	@RequestMapping("/save")
	@PostMapping
	public ResponseEntity<String> storeData(@Valid @RequestBody Truecaller caller) {
		ResponseEntity entity;
		int count = service.saveUser(caller);
		if (count > 0) {
			entity = new ResponseEntity<String>("User Created Sucessfully", HttpStatus.OK);
		} else {

			entity = new ResponseEntity<String>("Sorry   User RegistratinFailed", HttpStatus.BAD_REQUEST);

		}
		return entity;
	}

	@RequestMapping(value = "/getAll")
	@GetMapping

	public ResponseEntity getData() {
		List listData = service.getAll();
		ResponseEntity entity;

		if (listData != null) {
			entity = new ResponseEntity(listData, HttpStatus.OK);
		} else {

			entity = new ResponseEntity("Sorry Data Not Found", HttpStatus.BAD_REQUEST);

		}
		return entity;
	}

	@RequestMapping(value = "/fetchByName")
	@GetMapping

	public Truecaller getByName(@RequestParam(required = true) String name) {
		Truecaller truecaller = service.getByName(name);
		return truecaller;
	}

	@RequestMapping(value = "/fetchByNumber")
	@GetMapping

	public Truecaller getByNumber(@RequestParam(required = true) long mobno) {
		Truecaller truecaller = service.getByNumber(mobno);
		return truecaller;
	}
}
