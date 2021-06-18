package com.ocr.safety.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Fire;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.FirestationService;

@RunWith(SpringJUnit4ClassRunner.class)
public class FireStationServiceTest {
	
	@Autowired
	FirestationService firestationService;
	
	@Autowired
	DataTreatment datatreatment;
	
	public static FireStationPlus getPersonFromFireStationAreaTest() {
        return new FireStationPlus(
   			List.of(
                new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
                new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"),
                new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com" ),
    			new Person("Felicia", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com" )),
                3,2,3);
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
        return new ArrayList<>(Arrays.asList("841-874-6512", "841-874-6513"));
    }

    public static List<CompletePerson> getCompletePersonListFireStationNumberThreeTest() {
        return new ArrayList<>(Arrays.asList(
           new CompletePerson("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com",37, List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"), 3),
           new CompletePerson("Jacob", "Boyd",  "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com", 32, List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of(), 3),
           new CompletePerson("Tenley", "Boyd",  "1509 Culver St", "Culver", "97451", "841-874-6513", "tenz@email.com", 9, List.of(), List.of("peanut"), 3)
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
    
	
	@Before
	public void setup() {
		AllData allDataTest = new AllData(DataTest.PersonList(), DataTest.MedicalRecordList()
				, DataTest.FirestationList());
        datatreatment.setAlldata(allDataTest);
	}
	
	 @Test
	 public void getFirestationAreaTest() {
		 
       Assert.assertEquals(firestationService.getPersonFromFireStationArea(3), getPersonFromFireStationAreaTest());
     }

	 @Test
	 public void getPhoneAlertFromFirestationsTest() {
		 
		 Assert.assertEquals(firestationService.getPhoneNumberByFireStationNumber(2), getPhoneAlertListTest());
     }

	 @Test
	 public void getStationByAddressTest() {
		 
		 Assert.assertEquals(firestationService.getStationByAddress("29 15th St"),2);
	 }

	 @Test
	 public void getPersonsByAddressTest() {
		 
		 Assert.assertEquals(firestationService.getPersonsByItsAddress("1509 Culver St"), getFireMedicalRecordTest());
	 }

	 @Test
	 public void saveFirestationTest() {
		 
		 Assert.assertEquals( DataTest.getFireStationToAdd(), firestationService.saveFireStation(DataTest.getFireStationToAdd()));
	 }

	 @Test
	 public void deleteFirestationTest() {
		 
		 Assert.assertEquals(true, firestationService.deleteFireStation(DataTest.getFireStationToDelete()));
	 }
	 
}
