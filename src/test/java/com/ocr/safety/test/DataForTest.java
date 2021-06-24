package com.ocr.safety.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;

public class DataForTest {
	
	public static  List<Person> PersonList(){
		return new ArrayList<>(Arrays.asList(
			new Person("John", "Boyd", "1509 Culver St", "Culver","97451", "841-874-6512", "jaboyd@email.com" ),
			new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
			new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com" ),
			new Person("Roger", "Boyd", "892 Downing Ct", "Culver", "97451", "841-874-6512", "jaboyd@email.com" ),
			new Person("Felicia", "Boyd", "892 Downing Ct", "Culver", "97451", "841-874-6544", "jaboyd@email.com" )
				)) ;
	}
	
	public static List<FireStation> FirestationList() {
		return new ArrayList<>(Arrays.asList(
			new FireStation("1509 Culver St", 3 ),
			new FireStation("892 Downing Ct", 2 )
    			));
	}


	public static List<MedicalRecord>  MedicalRecordList() {
		return new ArrayList<>(Arrays.asList(
        new MedicalRecord("John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan")),
        new MedicalRecord("Jacob", "Boyd", "03/06/1989", List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of()),
        new MedicalRecord("Tenley","Boyd", "02/18/2012", List.of(), List.of("peanut")),
        new MedicalRecord("Roger", "Boyd", "09/06/2017", List.of(), List.of()),
		new MedicalRecord("Felicia", "Boyd","01/08/1986", List.of("tetracyclaz:650mg"), List.of("xilliathal"))
		));
	}
	
	
    public static MedicalRecord getEmptyMedicalRecord() {
    	
        return new MedicalRecord("", "", "", List.of(), List.of());
        
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
    
    public static Person getEmptyPerson() {
        return new Person("", "", "", "", "", "", "");
    }
	
    public static Person getPersonToAddTest() {
    	
        return new Person("Pierre", "Louis", "1509 Culver St", "Culver", "97451", "837-474-7983", "plouis@email.com");
        
    }

    public static Person getPersonToUpdateTest() {
    	
		return new Person("Jacob", "Boyd", "1509 Culver St", "Montreal", "97451", "841-874-6513", "changesemail@email.com" );
		
    }

    public static Person getPersonToDeleteTest() {
    	
        return new Person("John", "Boyd", "", "", "", "", "");
        
        
    }

    public static FireStation getEmptyFirestation() {
    	
        return new FireStation("", 0);
        
    }

    public static FireStation getFireStationToAdd() {
    	
        return new FireStation("112 Steppes Pl", 4);
        
    }

    public static FireStation getFireStationToDelete() {
    	
        return new FireStation("1509 Culver St", 3);
        
    }
    
}
