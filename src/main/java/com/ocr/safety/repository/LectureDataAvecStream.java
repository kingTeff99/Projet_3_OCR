package com.ocr.safety.repository;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ocr.safety.model.Person;

public class LectureDataAvecStream {
	
	public static void main(String[] args) {
		
		try(JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"),"UTF-8"))){
	
    	    Gson gson = new Gson();
    	    
    	    Map<String, List<Person>> dataJSON = gson.fromJson(reader, Map.class);
    	    
    	    List<List<Person>> ensembleDePerson 
    	    = dataJSON.entrySet().stream().filter(s -> s.getKey().contains("persons"))
    	    .map(Map.Entry::getValue).collect(Collectors.toList());
    	    
    	    ensembleDePerson.forEach(System.out::println);

//    	    Map<String, List<?>> dataJSON = gson.fromJson(reader, Map.class);
//    	    dataJSON.forEach((String, donnees) -> {
//    	    	
//    	    	System.out.println(String);
//    	    	donnees.forEach(System.out::println);
//    	    	
//    	    });
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
	
	}

}
