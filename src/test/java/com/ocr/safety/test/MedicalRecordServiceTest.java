package com.ocr.safety.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
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
	
	MedicalrecordService medicalrecordService;
	
	private List<String> medicationsFromPersonList = getMedicationsFromPerson();

    private List<String> allergiesFromPersonList = getAllergiesFromPerson();
	
	public List<String> getMedicationsFromPerson() {
    	
        return new ArrayList<>(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        
    }

    public List<String> getAllergiesFromPerson() {
    	
        return new ArrayList<>(Arrays.asList("nillacilan"));
        
    }
    
    private List<Person> personlist;
    
	
	@Before
	public void setup() {
		
		personlist = DataForTest.PersonList();
		
		AllData allDataTest = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
				, DataForTest.FirestationList());
		
		 datatreatment.setAlldata(allDataTest);
        
	}
	
	@Test
	public void getMedicationFromAPersonTest() {
		
		 assertEquals(getMedicationsFromPerson(), medicalrecordService.getMedications(personlist.get(0)));
		
//		assertSame(getMedicationsFromPerson(), medicalrecordService.getMedications(personlist.get(0)));
	}
	
	@Test
	public void getAllergiesFromAPersonTest() {
		
		MedicalrecordService baba = new MedicalrecordService();

		
		assertEquals(baba.getAllergies(personlist.get(0)), getAllergiesFromPerson());
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
		assertEquals(DataForTest.getMedicalRecordToDeleteTest(), medicalrecordService.deleteMedicalRecords(DataForTest.getMedicalRecordToDeleteTest()));

	}

}
