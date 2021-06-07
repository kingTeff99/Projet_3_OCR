package com.ocr.safety.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;

@Service
public class FirestationServiceImpl implements FirestationService {
	
	@Autowired
	private DataTreatment Datatreatment;
	
	@Override
	public FireStation saveFireStation(FireStation prototype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFireStation(FireStation prototype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FireStationPlus getPersonFromFireStationArea(Integer station) {
		
		 List<Person> persons = Datatreatment.getPersonByItsFirestationNumber(station);
		 
		 long adultCount = persons.stream().filter(p -> Datatreatment.getAgeForPerson(p) > 18).count();
		 
		 long childrenCount = persons.stream().filter(p -> Datatreatment.getAgeForPerson(p) <= 18).count();
		
		 return new FireStationPlus(persons, adultCount, childrenCount, station);

	}

}
