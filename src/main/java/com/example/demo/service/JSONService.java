package com.example.demo.service;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.JSONModelEntity;
import com.example.demo.model.JSONModel;
import com.example.demo.repository.JSONRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JSONService {
	
	private String jsonModel = "{\"menu\": {\"id\": \"file\", \"value\": \"file\", \"popup\": {\"menuitem\": [{\"value\": \"New\", \"onclick\": \"CreateDoc()\"}, {\"value\": \"open\", \"onclick\": \"OpenDoc()\"}, {\"value\": \"Save\", \"onclick\": \"SaveDoc()\"}]}}}";
	
	@Autowired
	private JSONRepository jsonRepository;
	
	public JSONModel manipulateJSON(String input) {
		String modifiedjsonModel = jsonModel;
		String[] pairs = input.split(",");
		for (String pair : pairs) {
			String[] keyValue = pair.split(":::");
			String key = keyValue[0].replace("\"", "");
			String value = keyValue[1].replace("\"", "");
			
			modifiedjsonModel = modifiedjsonModel.replace(key, value);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JSONModel modifiedJson = null;
		
	try {
		modifiedJson = mapper.readValue(modifiedjsonModel, JSONModel.class);
		} catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	
	saveToDB(modifiedjsonModel);
	
	return modifiedJson;
	
	}
	
	private void saveToDB(String modifiedJsonModel) {
		JSONModelEntity entity = new JSONModelEntity();
		entity.setJsonModel(modifiedJsonModel);
		jsonRepository.save(entity);
		
	}
}
