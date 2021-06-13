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
import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.ChildAlertPerson;
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.repository.DataTreatmentImpl;
import com.ocr.safety.service.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	DataTreatment datatreatment;
	
    public static Person getPersonToAddTest() {
        return new Person("Pierre", "Louis", "1509 Culver St", "Culver", "97451", "837-474-7983", "plouis@email.com");
    }

    public static Person getPersonToUpdateTest() {
		return new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "changesemail@email.com" );
    }

    public static Person getPersonToDeleteTest() {
        return new Person("Jacob", "Boyd", "", "", "", "", "");
        
    }
    
    public static List<ChildAlertPerson> getCompletePersonListChild() {
        return new ArrayList<>(Arrays.asList(
                new ChildAlertPerson("Tenley", "Boyd", null,  8)

        ));
    }

    public static List<ChildAlertPerson> getCompletePersonListAdult() {
        return new ArrayList<>(Arrays.asList(
                new ChildAlertPerson("John", "Boyd", null,  37),
                new ChildAlertPerson("Jacob", "Boyd", null,32)
        ));
    }

    public static ChildAlert getChildAlertTest() {
        return new ChildAlert("1509 Culver St", getCompletePersonListChild(), getCompletePersonListAdult());
    }

    public static List<CompletePerson> getCompletePersonByNamesList() {
        return new ArrayList<>(Arrays.asList(
                new CompletePerson("John", "Boyd", "1509 Culver St", "Culver", "97451", null, "jaboyd@email.com",  36, List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"), 0),
                new CompletePerson("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", null, "drk@email.com", 31, List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of(), 0),
                new CompletePerson("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", null, "tenz@email.com", 8, List.of(), List.of("peanut"), 0),
                new CompletePerson("Roger", "Boyd", "29 15th St", "Culver", "97451", null, "jaboyd@email.com", 3, List.of(), List.of(), 0),
                new CompletePerson("Felicia", "Boyd", "29 15th St", "Culver", "97451", null, "jaboyd@email.com", 34, List.of("tetracyclaz:650mg"), List.of("xilliathal"), 0)
        ));
    }

    public static List<String> getEmailsFromCityList() {
        return new ArrayList<>(Arrays.asList("jaboyd@email.com", "drk@email.com", "tenz@email.com", "jaboyd@email.com", "jaboyd@email.com"));
    }
    
    @BeforeAll
	public void setup() {
		AllData allDataTest = new AllData(DataTest.PersonList(), DataTest.medicalRecordList()
				, DataTest.FirestationList());
        ((DataTreatmentImpl) datatreatment).setAlldata(allDataTest);
	}
    
    @Test
    public void savePersonTest() {
    	
    	assertEquals(personService.savePerson(getPersonToAddTest()), getPersonToAddTest());
    	
    }
    
    @Test
    public void updatePersonTest() {
    	
    	assertEquals(personService.updatePerson(getPersonToUpdateTest()), getPersonToUpdateTest());
    	
    }
    
    @Test
    public void deletePersonTest() {
    	
    	assertEquals(personService.deletePerson(getPersonToDeleteTest()), getPersonToDeleteTest());
    	
    }
    
    @Test
    public void giveChildAlertByAddressTest() {
    	
    	assertEquals(personService.giveChildAlertByAddress("1509 Culver St"), getChildAlertTest() );
    	
    }
    
    @Test
    public void getCommunityEmailPersonsByCityTest() {
    	
    	assertEquals(personService.getCommunityEmailPersonsByCity("Culver"), getEmailsFromCityList());
    	
    }
    
    
    @Test
    public void getCompletePersonsByNameTest() {
    	
    	assertEquals(personService.getCompletePersonsByName("John", "Boyd"), getCompletePersonByNamesList());
    	
    }
    

}
