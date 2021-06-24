package com.ocr.safety.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.ChildAlertPerson;
import com.ocr.safety.model.CommunityEmail;
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.PersonService;


@SpringJUnitConfig
@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	DataTreatment datatreatment;
	
	
	private AllData allDataTest;
	
    
    public static List<ChildAlertPerson> getCompletePersonListChild() {
    	
        return new ArrayList<>(Arrays.asList(
                new ChildAlertPerson("Tenley", "Boyd", "1509 Culver St",  9)));
        
    }

    public static List<ChildAlertPerson> getCompletePersonListAdult() {
    	
        return new ArrayList<>(Arrays.asList(
                new ChildAlertPerson("John", "Boyd", "1509 Culver St", 37),
                new ChildAlertPerson("Jacob", "Boyd", "1509 Culver St", 32)));
        
    }

    public static ChildAlert getChildAlertTest() {
    	
        return new ChildAlert("1509 Culver St", getCompletePersonListChild(), getCompletePersonListAdult());
        
    }

    public List<CompletePerson> getCompletePersonByNamesList() {
        return new ArrayList<>(Arrays.asList(
                new CompletePerson("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com",  37, List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"), 0),
                new CompletePerson("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", null, "drk@email.com", 31, List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of(), 0),
                new CompletePerson("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", null, "tenz@email.com", 9, List.of(), List.of("peanut"), 0),
                new CompletePerson("Roger", "Boyd", "29 15th St", "Culver", "97451", null, "jaboyd@email.com", 3, List.of(), List.of(), 0),
                new CompletePerson("Felicia", "Boyd", "29 15th St", "Culver", "97451", null, "jaboyd@email.com", 34, List.of("tetracyclaz:650mg"), List.of("xilliathal"), 0)
        ));
    }

    public static CommunityEmail getEmailsFromCityList() {
    	
        return new CommunityEmail(List.of("jaboyd@email.com", "drk@email.com",
        		"tenz@email.com", "jaboyd@email.com", "jaboyd@email.com"));
        
    }
    
    @BeforeEach
	public void setup() {
    	
		 allDataTest = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
				, DataForTest.FirestationList());
		
         datatreatment.setAlldata(allDataTest);
         
	}
    
    @AfterEach
    public void setupEachTest() {
    	
    }
    
    @Test
    public void savePersonTest() {
    	
    	assertEquals(DataForTest.getPersonToAddTest(), personService.savePerson(DataForTest.getPersonToAddTest()));
    	
    }
    
    @Test
    public void updatePersonTest() {
    	
    	assertEquals(DataForTest.getPersonToUpdateTest(), personService.updatePerson(DataForTest.getPersonToUpdateTest()));
    	
    }
    
    @Test
    public void deletePersonTest() {
    	
    	assertEquals(true, personService.deletePerson(DataForTest.getPersonToDeleteTest()));
    	
    }
    
    @Test
    public void giveChildAlertByAddressTest() {
    	
    	assertEquals( getChildAlertTest(), personService.giveChildAlertByAddress("1509 Culver St"));
    	
    }
    
    @Test
    public void getCommunityEmailPersonsByCityTest() {
    	
    	assertEquals(getEmailsFromCityList(), personService.getCommunityEmailPersonsByCity("Culver"));
    	
    }
    
    
    @Test
    public void getCompletePersonsByNameTest() {
    	
    	assertEquals( List.of(getCompletePersonByNamesList().get(0)), personService.getCompletePersonsByName("John", "Boyd"));
    	
    }
    

}
