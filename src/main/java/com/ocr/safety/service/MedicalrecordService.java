package com.ocr.safety.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;

@Service
public class MedicalrecordService {
	
	private static final Logger logger = LogManager.getLogger(MedicalrecordService.class);
	
	@Autowired
	private DataTreatment dataTreatment;

	public MedicalRecord saveMedicalRecords(MedicalRecord prototype) {
		
		MedicalRecord medicalRecord = dataTreatment.saveMedicalRecords(prototype);
		
		if(medicalRecord != null) {
			
			logger.info(" new medical records saved ");
		}
		
		return medicalRecord;
	}

	public MedicalRecord updateMedicalRecords(MedicalRecord prototype) {
		
		MedicalRecord medicalRecord = dataTreatment.updateMedicalRecords(prototype);
		
		if(medicalRecord != null) {
			
			logger.info(" medical records updated ");
		}
		
		return medicalRecord;
	}

	public boolean deleteMedicalRecords(MedicalRecord prototype) {
		
		boolean medicalRecord = dataTreatment.deleteMedicalRecords(prototype);
		
		if(medicalRecord) {
			
			logger.info(" medical records deleted ");
		}
		
		return medicalRecord;
	}

	public List<String> getMedications(Person prototype) {
		
		for(MedicalRecord type : dataTreatment.getMedicalrecords()) {
			
			if(prototype.getFirstName().equals(type.getFirstName()) &&
					prototype.getLastName().equals(type.getLastName())) {
				
				return type.getMedications();
			}
		}
		
		return null;
	}

	public List<String> getAllergies(Person prototype) {
		
		for(MedicalRecord type : dataTreatment.getMedicalrecords()) {
			
			if(prototype.getFirstName().equals(type.getFirstName()) &&
					prototype.getLastName().equals(type.getLastName())) {
				
				return type.getAllergies();
			}
		}
		return null;
	}
	
}
