package com.ocr.safety.test;

import static org.junit.Assert.assertEquals;

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
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Fire;
import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.FirestationService;

@SpringJUnitConfig
@SpringBootTest
public class FireStationServiceTest {
	
	@Autowired
	FirestationService firestationService;
	
	@Autowired
	DataTreatment datatreatment;
	
	
	private AllData allDataTest; 
	
	public static FireStationPlus getPersonFromFireStationAreaTest() {
        return new FireStationPlus(
   			List.of(
                new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
                new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com")),
                2,1,3);
    }

    public static FireStationPlus getFirestationsAreaControllerTest() {
        return new FireStationPlus(
        		List.of(
                    new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", null),
                    new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", null),
                    new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", null)),
                	2,1,3);
    }

    public static List<String> getPhoneAlertListTest() {
        return new ArrayList<>(Arrays.asList("841-874-6512", "841-874-6544"));
    }

    public static List<CompletePerson> getCompletePersonListFireStationNumberThreeTest() {
        return new ArrayList<>(Arrays.asList(
           new CompletePerson("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com",37, List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"), 3),
           new CompletePerson("Jacob", "Boyd",  "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com", 32, List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of(), 3),
           new CompletePerson("Tenley", "Boyd",  "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com", 9, List.of(), List.of("peanut"), 3)
        ));
    }

    public static List<CompletePerson> getCompletePersonListFireStationNumberTwoTest() {
        return new ArrayList<>(Arrays.asList(
                new CompletePerson("Jonanathan", "Marrack", "29 15th St", "Culver", "97451", "841-874-6513", "drk@email.com", 32, List.of(), List.of(), 2),
                new CompletePerson("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878", "soph@email.com",33
                		,List.of("aznol:60mg", "hydrapermazol:900mg", "pharmacol:5000mg", "terazine:500mg"), List.of("peanut", "shellfish", "aznol"),2)
        ));
    }

    public static Fire getFireMedicalRecordTest() {
        return new Fire( getCompletePersonListFireStationNumberThreeTest());
    }
    
	
	@BeforeEach
	public void setup() {
		 allDataTest = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
				, DataForTest.FirestationList());
        datatreatment.setAlldata(allDataTest);
	}
	
	@AfterEach
	public void endUp() {
		
	}
	
	@Test
	public void getFirestationAreaTest() {
		 
       assertEquals(getPersonFromFireStationAreaTest(), firestationService.getPersonFromFireStationArea(3));
       
    }

	@Test
	public void getPhoneAlertFromFirestationsTest() {
		 
		 assertEquals(getPhoneAlertListTest(), firestationService.getPhoneNumberByFireStationNumber(2));
		 
    }

	@Test
	public void getStationByAddressTest() {
		 
		 assertEquals(List.of(2), firestationService.getStationByAddress("892 Downing Ct"));
		 
	}

	@Test
	public void getPersonsByAddressTest() {
		 
		 assertEquals(getFireMedicalRecordTest(), firestationService.getPersonsByItsAddress("1509 Culver St"));
		 
	}

	@Test
	public void saveFirestationTest() {
		 
		 assertEquals( DataForTest.getFireStationToAdd(), firestationService.saveFireStation(DataForTest.getFireStationToAdd()));
		 
	}

	@Test
	public void deleteFirestationTest() {
		 
		 assertEquals(true, firestationService.deleteFireStation(DataForTest.getFireStationToDelete()));
		 
	}
	 
}


