package com.ocr.safety.controller;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.ocr.safety.model.AllData;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.model.dto.FireStationDTO;

@RestController
public class PersonController {
	
	@GetMapping(value = "/")
	public static Map<String, AllData> ToutesLesDonnées() {
		
		Map<String, AllData> convertData = null;
		
		try( JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))) {
			
    	    Gson gson = new Gson();
    	    
    	    convertData = gson.fromJson(reader, Map.class);

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
				new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))){
	
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
	
	
//	@GetMapping(value = "/firestation")
//	public List<FireStation> listeDesCasernes() {
//		
//		List<List<FireStation>> ensembleDesCasernes = null;
//		
//		List<FireStation> fireStations = null;
//		
//		try(JsonReader reader = new JsonReader(new InputStreamReader(
//				new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))){
//			
//    	    Gson gson = new Gson();
//    	    
//    	    Map<String, List<FireStation>> dataJSON = gson.fromJson(reader, Map.class);
//    	    
//    	    ensembleDesCasernes
//    	    = dataJSON.entrySet().stream().filter(s -> s.getKey().contains("firestations"))
//    	    .map(Map.Entry::getValue).collect(Collectors.toList());
//    	    
//    	    fireStations = ensembleDesCasernes.stream().flatMap(e -> e.stream())
//    	    		.collect(Collectors.toList());
//
//    	} catch (Exception ex) {
//    		
//    	    ex.printStackTrace();
//    	}
//		
//		return fireStations;
//	}
	
	
	
	@GetMapping(value = "/firestation")
	public List<FireStation> listeDesCasernesFiltrees(FireStationDTO input) {
		
		List<List<FireStation>> ensembleDesCasernes = null;
		
		List<FireStation> fireStations = null;

		try(JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))){
			
    	    Gson gson = new Gson();
    	    
    	    Type fireStationType = new TypeToken<HashMap<String, List<FireStation>>>(){}.getType();
    	    
    	    HashMap<String, List<FireStation>> dataJSON = gson.fromJson(reader, fireStationType);
    	    
    	    ensembleDesCasernes = dataJSON.entrySet().stream().filter(s -> s.getKey().contains("firestations"))
    	    .map(Map.Entry::getValue).collect(Collectors.toList());
    	    
    	    
    	    fireStations = ensembleDesCasernes.stream().flatMap(e -> e.stream())
    	    		.collect(Collectors.toList());
    	    
    	    if(input.getStationId() != null) {
    	    	fireStations = fireStations.stream().filter(e -> e.getStation() == input.getStationId())
    	    	.collect(Collectors.toList());
    	    }

    	} catch(Exception ex) {
    		
    	    ex.printStackTrace();
    	}
		
		System.out.println(input);

		return fireStations;
	}
	
	
	
	
	@GetMapping(value = "/medicalRecord")
	public List<MedicalRecord> infosMedicales() {
		
		List<List<MedicalRecord>> antecedantsMedicals = null;
		
		List<MedicalRecord> medicalRecords = null;
		
		try(JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))){
			
    	    Gson gson = new Gson();
    	    
    	    Map<String, List<MedicalRecord>> dataJSON = gson.fromJson(reader, Map.class);
    	    
    	    antecedantsMedicals
    	    = dataJSON.entrySet().stream().filter(s -> s.getKey().contains("medicalrecords"))
    	    .map(Map.Entry::getValue).collect(Collectors.toList());
    	    
    	    medicalRecords = antecedantsMedicals.stream().flatMap(e -> e.stream())
    	    		.collect(Collectors.toList());

    	} catch (Exception ex) {
    		
    	    ex.printStackTrace();
    	}
		
		return medicalRecords;
		
	}
	
	
	@PostMapping(value = "/person")
	List<Person> ajouterUnePersonne(@RequestBody Person person) {
		
		List<List<Person>> ensembleDePerson = null;
		
		List<Person> ens = null;
		
		try(JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))){
	
    	    Gson gson = new Gson();
    	    
    	    Map<String, List<Person>> dataJSON = gson.fromJson(reader, Map.class);
    	    
    	    ensembleDePerson 
    	    = dataJSON.entrySet().stream().filter(s -> s.getKey().contains("persons"))
    	    .map(Map.Entry::getValue).collect(Collectors.toList());
    	    
    	    ens = ensembleDePerson.stream().flatMap(e -> e.stream())
    	    		.collect(Collectors.toList());
    	    
    	     ens.add(person);
    	     
    	    
    	} catch (Exception ex) {
    		
    	    ex.printStackTrace();
    	}
		
	     return ens;

	}
}
