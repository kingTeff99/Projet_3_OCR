package com.ocr.safety.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.repository.DataTreatmentImpl;
import com.ocr.safety.service.MedicalrecordService;


@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalRecordServiceTest {
	
	@Autowired
	private DataTreatment datatreatment;
	
	@Autowired
	private MedicalrecordService medicalrecordService;
	
	
	public static List<String> getMedicationsFromPerson() {
        return new ArrayList<>(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
    }

    public static List<String> getAllergiesFromPerson() {
        return new ArrayList<>(Arrays.asList("nillacilan"));
    }
    
    public static MedicalRecord getMedicalRecordToAddTest() {
        return new MedicalRecord("Pierre", "Louis", "01/01/1990", List.of("paracetamol:500mg", "efferalgan:400mg"), List.of("shrimp"));
    }

    public static MedicalRecord getMedicalRecordToUpdateTest() {
        return new MedicalRecord("John", "Boyd", "03/06/1984", List.of("efferalgan:3000mg"), List.of());
    }

    public static MedicalRecord getMedicalRecordToDeleteTest() {
        return new MedicalRecord("John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
    }
	
	
	@BeforeAll
	public void setup() {
		AllData allDataTest = new AllData(DataTest.PersonList(), DataTest.medicalRecordList()
				, DataTest.FirestationList());
        ((DataTreatmentImpl) datatreatment).setAlldata(allDataTest);
	}
	
	@Test
	public void getMedicationFromAPersonTest() {
		
		assertEquals(medicalrecordService.getMedications(DataTest.PersonList().get(0)), getMedicationsFromPerson());
	}
	
	@Test
	public void getAllergiesFromAPersonTest() {
		
		assertEquals(medicalrecordService.getAllergies(DataTest.PersonList().get(0)), getAllergiesFromPerson());
	}
	
	@Test
	public void saveMedicalRecordTest() {
		
		assertEquals(medicalrecordService.saveMedicalRecords(getMedicalRecordToAddTest()), getMedicalRecordToAddTest());
	}
	
	@Test
	public void updateMedicalRecordTest() {
		
		assertEquals(medicalrecordService.updateMedicalRecords(getMedicalRecordToUpdateTest()), getMedicalRecordToUpdateTest());
	}
	
	@Test
	public void deleteMedicalRecordTest() {
		
		assertEquals(medicalrecordService.deleteMedicalRecords(getMedicalRecordToDeleteTest()), getMedicalRecordToDeleteTest());
	}

}
