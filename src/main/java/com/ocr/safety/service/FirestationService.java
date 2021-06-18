package com.ocr.safety.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Fire;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;

@Service
public class FirestationService {
	
	private static final Logger logger = LogManager.getLogger(FirestationService.class);
	
	@Autowired
	private DataTreatment Datatreatment;
	
	@Autowired
	private MedicalrecordService medicalrecordService;
	
	public FireStation saveFireStation(FireStation prototype) {
		
		FireStation fireStation = Datatreatment.saveFireStation(prototype);
		
		if(fireStation != null) {
			
			logger.info(" firestation saved ");
		}
		
		return fireStation;
	}

	public boolean deleteFireStation(FireStation prototype) {
		
		boolean fireStation = Datatreatment.deleteFireStation(prototype) ;
		
		if(fireStation) {
			
			logger.info(" firestation deleted ");
		}
		
		return fireStation;
	}

	public FireStationPlus getPersonFromFireStationArea(Integer station) {
		
		 List<Person> persons = Datatreatment.getPersonByItsFirestationNumber(station);
		 
		 long adultCount = persons.stream().filter(p -> Datatreatment.getAgeForPerson(p) > 18).count();
		 
		 long childrenCount = persons.stream().filter(p -> Datatreatment.getAgeForPerson(p) <= 18).count();
		
		 return new FireStationPlus(persons, adultCount, childrenCount, station);

	}

	public List<String> getPhoneNumberByFireStationNumber(Integer firestation) {
		
		List<String> phoneList = new ArrayList<>();
		
		for (Person person : Datatreatment.getPersons() ) {
			
			if(Datatreatment.getFirestationNumberByPersonsAddress(person) == firestation) {
				
				phoneList.add(person.getPhone());
			}
		}
		return phoneList;
	}

	public Fire getPersonsByItsAddress(String address) {
		
		List<CompletePerson> peopleLiveHere = new ArrayList<>();
		
		for(Person type : Datatreatment.getPersons()) {
			
			if(type.getAddress().equals(address)) {
				
				peopleLiveHere.add(new CompletePerson(type.getFirstName(), type.getLastName(), address,
						type.getCity(), type.getZip(), type.getPhone(),
						type.getEmail(), Datatreatment.getAgeForPerson(type),
						medicalrecordService.getMedications(type), medicalrecordService.getAllergies(type), 
						Datatreatment.getFirestationNumberByPersonsAddress(type)));
				
			}
		}
		return new Fire(peopleLiveHere);
	}

	public Fire getPersonsByItsStationNumberArea(Integer stations) {
		
		List<CompletePerson> peopleList = new ArrayList<>();
		
		for(Person type : Datatreatment.getPersons()) {
			
			if(Datatreatment.getFirestationNumberByPersonsAddress(type) == stations) {
				
				peopleList.add(new CompletePerson(type.getFirstName(), type.getLastName(), type.getAddress(),
						type.getCity(), type.getZip(), type.getPhone(),
						type.getEmail(), Datatreatment.getAgeForPerson(type),
						medicalrecordService.getMedications(type), medicalrecordService.getAllergies(type), 
						Datatreatment.getFirestationNumberByPersonsAddress(type)));
				
			}
		}
		return new Fire(peopleList);
	}

	public List<Integer> getStationByAddress(String address) {
		
	        List<Integer> stationNb = new ArrayList<>();

	        for (FireStation firestation : Datatreatment.getFirestations()) {
	        	
	            if (address.compareTo(firestation.getAddress()) == 0) {
	            	
	                stationNb.add(firestation.getStation());
	            }
	        }
	        return stationNb;
	 }
		
	
}
