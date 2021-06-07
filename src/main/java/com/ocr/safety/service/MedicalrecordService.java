package com.ocr.safety.service;

import java.util.List;

import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;

public interface MedicalrecordService {
	
	List<String> getMedications(Person prototype);
	
	List<String> getAllergies(Person prototype);
	
	MedicalRecord saveMedicalRecords(MedicalRecord prototype);
	
	MedicalRecord updateMedicalRecords(MedicalRecord prototype);

	boolean deleteMedicalRecords(MedicalRecord prototype);

}
