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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firestations == null) ? 0 : firestations.hashCode());
		result = prime * result + ((medicalrecords == null) ? 0 : medicalrecords.hashCode());
		result = prime * result + ((persons == null) ? 0 : persons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllData other = (AllData) obj;
		if (firestations == null) {
			if (other.firestations != null)
				return false;
		} else if (!firestations.equals(other.firestations))
			return false;
		if (medicalrecords == null) {
			if (other.medicalrecords != null)
				return false;
		} else if (!medicalrecords.equals(other.medicalrecords))
			return false;
		if (persons == null) {
			if (other.persons != null)
				return false;
		} else if (!persons.equals(other.persons))
			return false;
		return true;
	}
	
	
	
	
}
