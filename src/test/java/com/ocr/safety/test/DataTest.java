package com.ocr.safety.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;

public class DataTest {
	
	public static List<Person> PersonList(){
		return new ArrayList<>(Arrays.asList(
			new Person("John", "Boyd", "1509 Culver St", "Culver","97451", "841-874-6512", "jaboyd@email.com" ),
			new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
			new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com" ),
			new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com" ),
			new Person("Felicia", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com" ),
			new Person("Jonanathan", "Marrack", "29 15th St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
			new Person("Peter","Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com" ),
			new Person("Lily", "Cooper", "489 Manchester St", "Culver", "97451", "841-874-9845", "lily@email.com" )
				)) ;
	}
	
	public static List<FireStation> FirestationList() {
		return new ArrayList<>(Arrays.asList(
			new FireStation("1509 Culver St", 3 ),
			new FireStation("29 15th St", 2 ),
    		new FireStation("644 Gershwin Cir", 1 ),
    		new FireStation("489 Manchester St", 4 )
    			));
	}


	public static List<MedicalRecord>  medicalRecordList() {
		return new ArrayList<>(Arrays.asList(
        new MedicalRecord("John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan")),
        new MedicalRecord("Jacob", "Boyd", "03/06/1989", List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of() ),
        new MedicalRecord("Tenley","Boyd", "02/18/2012", List.of(), List.of("peanut")),
        new MedicalRecord("Roger", "Boyd", "09/06/2017", List.of(), List.of()),
		new MedicalRecord("Felicia", "Boyd","01/08/1986", List.of("tetracyclaz:650mg"), List.of("xilliathal")),
		new MedicalRecord("Jonanathan", "Marrack", "01/03/1989", List.of(), List.of()),
		new MedicalRecord("Tessa", "Carman", "02/18/2012", List.of(), List.of()),
		new MedicalRecord("Peter", "Duncan", "09/06/2000", List.of(), List.of("shellfish")),
		new MedicalRecord("Foster", "Shepard","01/08/1980", List.of(), List.of()),
		new MedicalRecord("Lily", "Cooper", "03/06/1994", List.of(), List.of() )
		));
	}
	
	
    public static FireStation getEmptyFirestation() {
        return new FireStation("", 0);
    }

    public static FireStation getFireStationToAdd() {
        return new FireStation("947 E. Rose Dr", 1);
    }

    public static FireStation getFireStationToDelete() {
        return new FireStation("1509 Culver St", 3);
    }

    public static MedicalRecord getEmptyMedicalRecord() {
        return new MedicalRecord("", "", "", List.of(), List.of());
    }

    
}
