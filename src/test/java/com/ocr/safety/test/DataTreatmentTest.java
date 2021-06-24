package com.ocr.safety.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;

@SpringJUnitConfig
@SpringBootTest
public class DataTreatmentTest {
	
	@Autowired
	private DataTreatment datatreatment;
	
	private List<Person> personListByStationNumberAndAddressTest = new ArrayList<>(Arrays.asList(
			new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
			new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
			new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com")
			));
	
	
	private Person emptyPerson = DataForTest.getEmptyPerson();

	private Person personToAddTest = DataForTest.getPersonToAddTest();

    private Person personToUpdateTest = DataForTest.getPersonToUpdateTest();

    private Person personToDeleteTest = DataForTest.getPersonToDeleteTest();

    private FireStation emptyFirestation = DataForTest.getEmptyFirestation();

    private FireStation firestationToAdd = DataForTest.getFireStationToAdd();

    private FireStation firestationToDelete = DataForTest.getFireStationToDelete();

    private MedicalRecord emptyMedicalRecord = DataForTest.getEmptyMedicalRecord();

    private MedicalRecord medicalRecordsToAddTest = DataForTest.getMedicalRecordToAddTest();

    private MedicalRecord medicalRecordsToUpdateTest = DataForTest.getMedicalRecordToUpdateTest();

    private MedicalRecord medicalRecordsToDeleteTest = DataForTest.getMedicalRecordToDeleteTest();

	public List<Person> personList;
	
	public List<FireStation> firestationsList;
	
	public List<MedicalRecord> medicalRecordsList;
	
	public AllData allDataTest;

	@BeforeEach
    public void setup() {
	    	
		personList = DataForTest.PersonList();
	        
	    firestationsList = DataForTest.FirestationList();
	        
        medicalRecordsList = DataForTest.MedicalRecordList();
	        
        allDataTest = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
					, DataForTest.FirestationList());
	    datatreatment.setAlldata(allDataTest);

    }

	@Test		    
	public void getNbStationByAddressFromValidPersonTest() {
	
		assertThat( datatreatment.getFirestationNumberByPersonsAddress(personList.get(0))).isEqualTo(3);
		
	}


	@Test
    public void getNbStationByAddressFromNullPersonTest() {
		
		assertThat(datatreatment.getFirestationNumberByPersonsAddress(null)).isEqualTo(0);
    }

	@Test
    public void getAgeFromValidPersonTest() {
		
	    	assertThat(datatreatment.getAgeForPerson(personList.get(0))).isEqualTo(36);
	}

    @Test
    public void getAgeFromNoValidPersonTest() {
    	
	    	assertThat(datatreatment.getAgeForPerson(emptyPerson)).isEqualTo(0);
	}

    @Test
    public void getAgeFromNullPersonTest() {
    	
	    	assertThat(datatreatment.getAgeForPerson(null)).isEqualTo(0);
    }

    @Test
    public void getPersonsByValidFirestationNumberTest() {
	       
	        assertEquals(personListByStationNumberAndAddressTest, datatreatment.getPersonByItsFirestationNumber(firestationsList.get(0).getStation()));
    }

    @Test
    public void getPersonsByNoValidFirestationNumberTest() {
	    	
	   	assertEquals(Collections.emptyList(), datatreatment.getPersonByItsFirestationNumber(-1));
	    	
    }

    @Test
    public void getPersonsByValidAddress() {
	    	
	 	assertEquals(personListByStationNumberAndAddressTest, datatreatment.getPersonByItsAddress("1509 Culver St"));
	    	
    }

	@Test
	public void getPersonsByNoValidAddress() {
	    	
    	assertEquals(Collections.emptyList(), datatreatment.getPersonByItsAddress("11"));
	    	
	}

    @Test
    public void getPersonsByNullAddress() {
	    	
    	assertEquals(Collections.emptyList(), datatreatment.getPersonByItsAddress(null));
	    	
	}

	@Test
    public void getAgeFromValidBirthdate() {
	    	
		assertThat(datatreatment.getAgeWithBirthDate(medicalRecordsList.get(0).getBirthdate())).isEqualTo(36);

	}
	    
	@Test
    public void getAgeFromNoValidBirthdate() {
	    	
		assertThat(datatreatment.getAgeWithBirthDate("")).isEqualTo(0);
		
	}

    @Test
    public void getAgeFromNullBirthdate() {
	    	
    	assertThat( datatreatment.getAgeWithBirthDate(null)).isEqualTo(0);

    }

    @Test
    public void getFirestationsTest() {
	    	
    	assertEquals(firestationsList, datatreatment.getFirestations());
    	
	}

    @Test
    public void getPersonsTest() {
	    	
    	assertEquals(personList, datatreatment.getPersons());
	    	
    }

    @Test
    public void getMedicalRecordsTest() {
	    	
    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    	
    }

    @Test
    public void savePersonNoEmptyListTest() {
	    	
	        List<Person> personListTest = new ArrayList<>(personList);
	        personListTest.add(personToAddTest);
	        
	        assertEquals(personToAddTest, datatreatment.savePerson(personToAddTest));
	        
	        assertEquals(personListTest, datatreatment.getPersons());
	    }

	    @Test
	    public void saveUniquePersonEmptyListTest() {
	    	
	    	allDataTest.setPersons(new ArrayList<>());
	    	
	        assertEquals(personToAddTest, datatreatment.savePerson(personToAddTest));
	        
	        assertEquals(List.of(personToAddTest), datatreatment.getPersons());
	    }

	    @Test
	    public void saveExistingPersonListTest() {
	    	
	    	assertEquals(null, datatreatment.savePerson(personList.get(0)));
	    	
	    	assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void saveNullPersonListTest() {
	    	
	    	assertEquals(null, datatreatment.savePerson(null));
	    	
	    	assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void savePersonNullListTest() {
	    	
	    	allDataTest.setPersons(null);
	    	
	        assertEquals(personToAddTest, datatreatment.savePerson(personToAddTest));
	        
	        assertEquals(List.of(personToAddTest), datatreatment.getPersons());
	    }

	    @Test
	    public void updateExistingPersonNoEmptyListTest() {
	    	
	        List<Person> personListTest = new ArrayList<>(datatreatment.getPersons());
	        
	        assertEquals(personToUpdateTest, datatreatment.updatePerson(personToUpdateTest));
	        
	        personListTest.get(0).setCity("Paris");
	        
	        assertEquals(personListTest, datatreatment.getPersons());
	    }


	    @Test
	    public void updatePersonEmptyListTest() {
	    	
	    	allDataTest.setPersons(new ArrayList<>());
	    	
	        assertEquals(null, datatreatment.updatePerson(personToUpdateTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

	    @Test
	    public void updateNoExistingPersonListTest() {
	    	assertEquals(null, datatreatment.updatePerson(emptyPerson));
	    	assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void updateNullPersonListTest() {
	    	
	    	assertEquals(null, datatreatment.updatePerson(null));
	    	
	    }

	    @Test
	    public void updatePersonNullListTest() {
	    	
	    	allDataTest.setPersons(null);
	    	
	        assertEquals(null, datatreatment.updatePerson(personToUpdateTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

	    @Test
	    public void deletePersonNoEmptyListTest() {
	    	
	        List<Person> personListTest = new ArrayList<>(allDataTest.getPersons());
	        
	        personListTest.remove(0);
	        
	        assertEquals(true, datatreatment.deletePerson(personToDeleteTest));
	        
	        assertEquals(personListTest, datatreatment.getPersons());
	    }

	    @Test
	    public void deletePersonEmptyListTest() {
	    	
	    	allDataTest.setPersons(new ArrayList<>());
	    	
	        assertEquals(false, datatreatment.deletePerson(personToDeleteTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

	    @Test
	    public void deleteNoExistingPersonListTest() {
	    	assertEquals(false, datatreatment.deletePerson(emptyPerson));
	    	assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void deleteNullPersonListTest() {
	    	
	    	assertEquals(false, datatreatment.deletePerson(null));
	    	
	        assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void deletePersonNullListTest() {
	    	
	    	allDataTest.setPersons(null);
	    	
	        assertEquals(false, datatreatment.deletePerson(personToAddTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

	    @Test
	    public void saveMedicalRecordNoEmptyListTest() {
	    	
	        List<MedicalRecord> medicalRecordsListTest = new ArrayList<>(medicalRecordsList);
	        
	        assertEquals(medicalRecordsToAddTest, datatreatment.saveMedicalRecords(medicalRecordsToAddTest));
	        
	        medicalRecordsListTest.add(medicalRecordsToAddTest);
	        
	        assertEquals(medicalRecordsListTest, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void saveUniqueMedicalRecordEmptyListTest() {
	    	
	    	allDataTest.setMedicalrecords(new ArrayList<>());
	    	
	        assertEquals(medicalRecordsToAddTest, datatreatment.saveMedicalRecords(medicalRecordsToAddTest));
	        
	        assertEquals(List.of(medicalRecordsToAddTest), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void saveExistingMedicalRecordListTest() {
	    	
	    	assertEquals(null, datatreatment.saveMedicalRecords(medicalRecordsList.get(0)));
	    	
	    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void saveNullMedicalRecordListTest() {
	    	
	    	assertEquals(null, datatreatment.saveMedicalRecords(null));
	    	
	    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void saveMedicalRecordNullListTest() {
	    	allDataTest.setMedicalrecords(null);
	    	
	        assertEquals(medicalRecordsToAddTest, datatreatment.saveMedicalRecords(medicalRecordsToAddTest));
	        
	        assertEquals(List.of(medicalRecordsToAddTest), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void updateExistingMedicalRecordNoEmptyListTest() {
	    	
	        List<MedicalRecord> medicalRecordsListTest = new ArrayList<>(DataForTest.MedicalRecordList());
	        
	        assertEquals(medicalRecordsToUpdateTest, datatreatment.updateMedicalRecords(medicalRecordsToUpdateTest));
	        
	        medicalRecordsListTest.get(0).setMedications(List.of("doliprane:1000mg"));
	        medicalRecordsListTest.get(0).setAllergies(List.of());
	        
	        assertEquals(medicalRecordsListTest, datatreatment.getMedicalrecords());
	        
	    }

	    @Test
	    public void updateMedicalRecordEmptyListTest() {
	    	
	    	allDataTest.setMedicalrecords(new ArrayList<>());
	    	
	        assertEquals(null, datatreatment.updateMedicalRecords(medicalRecordsToUpdateTest));
	        
	        assertEquals(List.of(), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void updateNoExistingMedicalRecordListTest() {
	    	
	    	assertEquals(null, datatreatment.updateMedicalRecords(emptyMedicalRecord));
	    	
	    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void updateNullMedicalRecordListTest() {
	    	
	    	assertEquals(null, datatreatment.updateMedicalRecords(null));
	    	
	    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void updateMedicalRecordNullListTest() {
	    	allDataTest.setMedicalrecords(null);
	    	
	        assertEquals(null, datatreatment.updateMedicalRecords(medicalRecordsToUpdateTest));
	    	
	        assertEquals(List.of(), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void deleteMedicalRecordNoEmptyListTest() {
	        List<MedicalRecord> medicalRecordsListTest = DataForTest.MedicalRecordList();
	        
	        assertEquals(true, datatreatment.deleteMedicalRecords(medicalRecordsToDeleteTest));
	        
	        medicalRecordsListTest.remove(0);
	        
	        assertEquals(medicalRecordsListTest, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void deleteMedicalRecordEmptyListTest() {
	    	allDataTest.setMedicalrecords(new ArrayList<>());
	    	
	        assertEquals(false, datatreatment.deleteMedicalRecords(medicalRecordsToDeleteTest));
	        
	        assertEquals(List.of(), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void deleteNoExistingMedicalRecordListTest() {
	    	
	    	assertEquals(false, datatreatment.deleteMedicalRecords(emptyMedicalRecord));
	    	
	    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void deleteNullMedicalRecordListTest() {
	    	
	    	assertEquals(false, datatreatment.deleteMedicalRecords(null));
	    	
	    	assertEquals(medicalRecordsList, datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void deleteMedicalRecordNullListTest() {
	    	allDataTest.setMedicalrecords(null);
	    	
	        assertEquals(false, datatreatment.deleteMedicalRecords(medicalRecordsToDeleteTest));
	        
	        assertEquals(List.of(), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void saveFirestationNoEmptyListTest() {
	        List<FireStation> firestationsListTest = new ArrayList<>(allDataTest.getFirestations());
	        
	        assertEquals(firestationToAdd, datatreatment.saveFireStation(firestationToAdd));
	        
	        firestationsListTest.add(firestationToAdd);
	        
	        assertEquals(firestationsListTest, datatreatment.getFirestations());
	    }

	    @Test
	    public void saveUniqueFirestationEmptyListTest() {
	    	allDataTest.setFirestations(new ArrayList<>());
	    	
	        assertEquals(firestationToAdd, datatreatment.saveFireStation(firestationToAdd));
	        
	        assertEquals(List.of(firestationToAdd), datatreatment.getFirestations());
	    }

	    @Test
	    public void saveExistingFirestationListTest() {
	    	
	    	assertEquals(null, datatreatment.saveFireStation(firestationsList.get(0)));
	    	
	    	assertEquals(firestationsList, datatreatment.getFirestations());
	    }

	    @Test
	    public void saveNullFirestationListTest() {
	    	
	    	assertEquals(null, datatreatment.saveFireStation(null));
	    	
	    	assertEquals(firestationsList, datatreatment.getFirestations());
	    }

	    @Test
	    public void saveFirestationNullListTest() {
	    	allDataTest.setFirestations(null);
	    	
	        assertEquals(firestationToAdd, datatreatment.saveFireStation(firestationToAdd));
	        
	        assertEquals(List.of(firestationToAdd), datatreatment.getFirestations());
	    }

	    @Test
	    public void deleteFirestationNoEmptyListTest() {
	    	
	        System.out.println(firestationsList);
	        List<FireStation> firestationsListTest = new ArrayList<>(allDataTest.getFirestations());
	        System.out.println(firestationsList);
	        
	        assertEquals(true, datatreatment.deleteFireStation(firestationToDelete));
	        
	        System.out.println(firestationsList);
	        firestationsListTest.remove(0);
	        
	        System.out.println("1" + firestationsList);
	        assertEquals(datatreatment.getFirestations(), firestationsListTest);
	    }

	    @Test
	    public void deleteFirestationEmptyListTest() {
	    	allDataTest.setFirestations(new ArrayList<>());
	    	
	        assertEquals(datatreatment.deleteFireStation(firestationToDelete), false);
	        
	        assertEquals(datatreatment.getFirestations(), List.of());
	    }

	    @Test
	    public void deleteNoExistingFirestationListTest() {
	    	
	    	assertEquals(datatreatment.deleteFireStation(emptyFirestation), false);
	    	
	    	assertEquals(datatreatment.getFirestations(), firestationsList);
	    }

	    @Test
	    public void deleteNullFirestationListTest() {
	    	
	    	assertEquals(false, datatreatment.deleteFireStation(null));
	    	
	    	assertEquals(firestationsList, datatreatment.getFirestations());
	    }

	    @Test
	    public void deleteFirestationNullListTest() {
	    	allDataTest.setFirestations(null);
	    	
	        assertEquals(false, datatreatment.deleteFireStation(firestationToDelete));
	        
	        assertEquals(List.of(), datatreatment.getFirestations());
	    }

}
