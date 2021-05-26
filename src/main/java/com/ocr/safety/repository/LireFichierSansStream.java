package com.ocr.safety.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import com.ocr.safety.model.Person;

public class LireFichierSansStream {
	
	public static void main(String[] args) {
		
	    	try {
	    	    //create Gson instance
	    	    Gson gson = new Gson();
	    	    
	    	    // create a reader
	  	      	BufferedReader br = new BufferedReader(new FileReader("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"));
	
	    	    // convert JSON file to map
	    	    Map<String, List<?>> convertData = gson.fromJson(br, Map.class);
	
	    	    // print map entries
	    	    for (Map.Entry<String, List<?>> entry : convertData.entrySet()) {
	    	    	
	    	    	if(entry.getKey().contains("persons")) {
	    	    		
		    	        System.out.println(entry.getValue());

	    	    	}
//	    	        System.out.println(entry.getKey().contains("persons") + " : " + entry.getValue());
	    	    }
	
	    	    br.close();
	
	    	} catch (Exception ex) {
	    	    ex.printStackTrace();
	    	}
	}    
}


