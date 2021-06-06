package com.ocr.safety.model;

import java.util.List;

public class AllData {
	
	private List<Person> persons;
	
	private List<MedicalRecord> medicalrecords;
	
	private List<FireStation> firestations;

	public AllData(List<Person> persons, List<MedicalRecord> medicalrecords, List<FireStation> firestations) {
		this.persons = persons;
		this.medicalrecords = medicalrecords;
		this.firestations = firestations;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	@Override
	public String toString() {
		return "AllData [persons=" + persons + ", medicalrecords=" + medicalrecords + ", firestations=" + firestations
				+ "]";
	}
	
	
}
