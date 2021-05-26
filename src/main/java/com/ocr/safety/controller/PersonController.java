package com.ocr.safety.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;

@RestController
public class PersonController {
	
	@GetMapping(value = "/allData")
	public static Map<String, List<?>> ToutesLesDonnées() {
		
		Map<String, List<?>> convertData = null;
		
		try( BufferedReader br = new BufferedReader(
				new FileReader("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"));
				) {
			
    	    Gson gson = new Gson();
    	    
    	    convertData = gson.fromJson(br, Map.class);

    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
		return convertData;
	}
	
	@GetMapping(value = "/person")
	public List<Person> listeDesPersonnes() {
		
		List<List<Person>> ensembleDePerson = null;
		
		List<Person> ens = null;
		
		try(JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"),"UTF-8"))){
	
    	    Gson gson = new Gson();
    	    
    	    Map<String, List<Person>> dataJSON = gson.fromJson(reader, Map.class);
    	    
    	    ensembleDePerson 
    	    = dataJSON.entrySet().stream().filter(s -> s.getKey().contains("persons"))
    	    .map(Map.Entry::getValue).collect(Collectors.toList());
    	    
    	    ens = ensembleDePerson.stream().flatMap(e -> e.stream())
    	    		.collect(Collectors.toList());
    	    
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
	
		

		return ens;
	}
	
	@GetMapping(value = "/firestation")
	public List<FireStation> listeDesCasernes() {
		
		Map<String, List<FireStation>> convertFireStation = null;
		
		List<FireStation> fireStations = null;
		
		try( BufferedReader br = new BufferedReader(
				new FileReader("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"));
				) {
			
    	    //create Gson instance
    	    Gson gson = new Gson();
    	    
    	    /*
    	     * convert JSON file to map
    	     */
//    	    convertPerson = ((Collection<Person>) gson.fromJson(br, Map.class)).stream().collect(Collectors.);
    	    convertFireStation = gson.fromJson(br, Map.class);
    	    
    	    //print map entries
    	    for (Entry<String, List<FireStation>> entry : convertFireStation.entrySet()) {
    	    	if(entry.getKey().contains("firestations")) {
    	    		fireStations = entry.getValue();

    	    	}
    	    }

    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
		return fireStations;
	}
	
	@GetMapping(value = "/medicalRecord")
	public List<MedicalRecord> infosMedicales() {
		
		Map<String, List<MedicalRecord>> convertMedicalRecord = null;
		
		List<MedicalRecord> medicalRecords = null;
		
		try( BufferedReader br = new BufferedReader(
				new FileReader("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"));
				) {
			
    	    //create Gson instance
    	    Gson gson = new Gson();
    	    
    	    /*
    	     * convert JSON file to map
    	     */
//    	    convertPerson = ((Collection<Person>) gson.fromJson(br, Map.class)).stream().collect(Collectors.);
    	    convertMedicalRecord = gson.fromJson(br, Map.class);
    	    
    	    //print map entries
    	    for (Entry<String, List<MedicalRecord>> entry : convertMedicalRecord.entrySet()) {
    	    	if(entry.getKey().contains("medicalrecords")) {
    	    		medicalRecords = entry.getValue();

    	    	}
    	    }

    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
		return medicalRecords;
	}
	
	
	@PostMapping(value = "/person")
	public List<Person> ajouterUnePersonne(@RequestBody Person person) {
		
		Map<String, List<Person>> convertPerson = null;
		
		List<Person> persons = null;
		
		Person pr = new Person();
		
		pr.setFirstName("Teff");
		pr.setLastName("King");
		pr.setAddress("1509 Culver St");
		pr.setCity("Culver");
		pr.setZip((long) 97451);
		pr.setPhone("123-456-7890");
		pr.setEmail("tatayoyo@email.com");
		
		try( BufferedReader br = new BufferedReader(
				new FileReader("/Users/kingteff/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/safetynet/src/main/resources/data.json"));
				) {
			
    	    //create Gson instance
    	    Gson gson = new Gson();
    	    
    	    /*
    	     * convert JSON file to map
    	     */
//    	    convertPerson = ((Collection<Person>) gson.fromJson(br, Map.class)).stream().collect(Collectors.);
    	    convertPerson = gson.fromJson(br, Map.class);
    	    
    	    //print map entries
    	    for (Entry<String, List<Person>> entry : convertPerson.entrySet()) {
    	    	
    	    	if(entry.getKey().contains("persons")) {
    	    		
        	        persons = entry.getValue();
    	    	}
    	    }

    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
		
		persons.add(pr);
		
		return persons;
		
	}
}
