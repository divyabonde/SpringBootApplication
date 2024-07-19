package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JSONModel;
import com.example.demo.service.JSONService;

@RestController
@RequestMapping("/api/json")
public class JSONController {
	@Autowired
	private JSONService jsonService;
	
	@PostMapping("/manipulate")
	public ResponseEntity<JSONModel> manipulateJSON(@RequestBody String input) {
		JSONModel modifiedJson = jsonService.manipulateJSON(input);
		return ResponseEntity.ok(modifiedJson);
	}
}
