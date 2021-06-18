package com.ocr.safety.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.MedicalrecordService;


@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalRecordServiceTest {
	
	@Autowired
	private DataTreatment datatreatment;
	
	@Autowired
	private MedicalrecordService medicalrecordService;
	
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
		
		personlist = DataTest.PersonList();
		
		AllData allDataTest = new AllData(DataTest.PersonList(), DataTest.MedicalRecordList()
				, DataTest.FirestationList());
		
		 datatreatment.setAlldata(allDataTest);
        
	}
	
	@Test
	public void getMedicationFromAPersonTest() {
		
		 assertEquals(getMedicationsFromPerson(), medicalrecordService.getMedications(personlist.get(0)));
		
//		assertSame(getMedicationsFromPerson(), medicalrecordService.getMedications(personlist.get(0)));
	}
	
	@Test
	public void getAllergiesFromAPersonTest() {
		
		assertEquals(medicalrecordService.getAllergies(personlist.get(0)), getAllergiesFromPerson());
	}
	
	@Test
	public void saveMedicalRecordTest() {
		
		assertEquals( DataTest.getMedicalRecordToAddTest(), medicalrecordService.saveMedicalRecords(DataTest.getMedicalRecordToAddTest()));
	}
	
	@Test
	public void updateMedicalRecordTest() {
		
		assertEquals(DataTest.getMedicalRecordToUpdateTest(), medicalrecordService.updateMedicalRecords(DataTest.getMedicalRecordToUpdateTest()));
	}
	
	@Test
	public void deleteMedicalRecordTest() {
		
		assertEquals(DataTest.getMedicalRecordToDeleteTest(), medicalrecordService.deleteMedicalRecords(DataTest.getMedicalRecordToDeleteTest()));
	}

}
