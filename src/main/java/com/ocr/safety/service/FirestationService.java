package com.ocr.safety.service;


import java.util.ArrayList;
import java.util.List;

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
	
	@Autowired
	private DataTreatment Datatreatment;
	
	@Autowired
	private MedicalrecordService medicalrecordService;
	
	public FireStation saveFireStation(FireStation prototype) {
		
		FireStation fireStation = Datatreatment.saveFireStation(prototype);
		
		return fireStation;
	}

	public boolean deleteFireStation(FireStation prototype) {
		
		boolean fireStation = Datatreatment.deleteFireStation(prototype);

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
		
	        List<Integer> stationNumber = new ArrayList<>();

	        for (FireStation firestation : Datatreatment.getFirestations()) {
	        	
	            if (address.compareTo(firestation.getAddress()) == 0) {
	            	
	                stationNumber.add(firestation.getStation());
	            }
	        }
	        return stationNumber;
	 }
		
	
}
