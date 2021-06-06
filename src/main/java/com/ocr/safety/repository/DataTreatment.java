package com.ocr.safety.repository;

import java.util.List;

import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;

public interface DataTreatment {
	
	List<Person> getPersonByItsAddress(String address);
	
	List<Person> getPersonByItsFirestationNumber(Integer station);
	
	Integer getFirestationNumberByPersonsAddress(Person prototype);
	
	Integer getAgeWithBirthDate(String birthDate);
	
	Integer getAgeForPerson(Person prototype);
	
	List<Person> getPersons();
	
	List<MedicalRecord> getMedicalrecords();
	
	List<FireStation> getFirestations();
	
	Person savePerson(Person prototype);
	
	Person updatePerson(Person prototype);

	boolean deletePerson(Person prototype);
	
	MedicalRecord saveMedicalRecords(MedicalRecord prototype);
	
	MedicalRecord updateMedicalRecords(MedicalRecord prototype);

	boolean deleteMedicalRecords(MedicalRecord prototype);

	FireStation saveFireStation(FireStation prototype);
	
	//FireStation updateFireStation(FireStation prototype);
	//trouver cmt faire la maj du nb de la caserne associée à une adresse
	
	boolean deleteFireStation(FireStation prototype);
	
	


}
