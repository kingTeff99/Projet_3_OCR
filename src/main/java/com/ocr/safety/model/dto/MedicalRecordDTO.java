package com.ocr.safety.model.dto;

import java.util.List;

import com.ocr.safety.model.MedicalRecord;

public class MedicalRecordDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String birthDate;
	
	private List<String> medications;
	
	private List<String> allergies;
	
	public MedicalRecordDTO() {
		
	}

	public MedicalRecordDTO(MedicalRecord medicalrecord) {
		
		this.setFirstName(medicalrecord.getFirstName()); 
		
		this.setLastName(medicalrecord.getLastName()); 
		
		this.setBirthDate(medicalrecord.getBirthDate()); 
		
		this.setMedications(medicalrecord.getMedications()); 
		
		this.setAllergies(medicalrecord.getAllergies());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

}
