package com.ocr.safety.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.MedicalrecordService;

@SpringJUnitConfig
@SpringBootTest
public class MedicalRecordServiceTest {
	
	@Autowired
	DataTreatment datatreatment;
	
	@Autowired
	MedicalrecordService medicalrecordService;
	
	
	public static List<String> getMedicationsFromPerson() {
    	
        return new ArrayList<>(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        
    }

    public static List<String> getAllergiesFromPerson() {
    	
        return new ArrayList<>(Arrays.asList("nillacilan"));
        
    }
    
    private List<Person> personlist;
    
	
	@BeforeEach
	public void setup() {
		
		personlist = DataForTest.PersonList();
		
		AllData allDataTest = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
				, DataForTest.FirestationList());
		
		 datatreatment.setAlldata(allDataTest);
        
	}
	
	@Test
	public void getMedicationFromAPersonTest() {
		
		 assertEquals(getMedicationsFromPerson(), medicalrecordService.getMedications(personlist.get(0)));
		
	}
	
	@Test
	public void getAllergiesFromAPersonTest() {
		
		assertEquals(getAllergiesFromPerson(), medicalrecordService.getAllergies(personlist.get(0)));
	}
	
	@Test
	public void saveMedicalRecordTest() {
		
		assertEquals( DataForTest.getMedicalRecordToAddTest(), medicalrecordService.saveMedicalRecords(DataForTest.getMedicalRecordToAddTest()));
	}
	
	@Test
	public void updateMedicalRecordTest() {
		
		assertEquals(DataForTest.getMedicalRecordToUpdateTest(), medicalrecordService.updateMedicalRecords(DataForTest.getMedicalRecordToUpdateTest()));
	}
	
	
	@Test
	public void deleteMedicalRecordTest() {
		assertEquals(true, medicalrecordService.deleteMedicalRecords(DataForTest.getMedicalRecordToDeleteTest()));

	}

}
