package com.ocr.safety.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
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
	
	private List<Person> personListByStationNumberAndAddressTest = new ArrayList<>();

	{
	   personListByStationNumberAndAddressTest.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
	   personListByStationNumberAndAddressTest.add(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"));
	   personListByStationNumberAndAddressTest.add(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"));
	}

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
	
	public AllData allData;

	    @Before
	    public void setup() {
	    	
	        personList = DataForTest.PersonList();
	        
	        firestationsList = DataForTest.FirestationList();
	        
	        medicalRecordsList = DataForTest.MedicalRecordList();
	        
	        AllData allData = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
					, DataForTest.FirestationList());
	        datatreatment.setAlldata(allData);

	    }

//	    @Test
//	    public void getNbStationByAddressFromValidPersonTest() {
//	    	assertEquals(3, datatreatment.getFirestationNumberByPersonsAddress(personList.get(0)));
//	    }
//
//
//	    @Test
//	    public void getNbStationByAddressFromNullPersonTest() {
//	    	assertEquals(0, datatreatment.getFirestationNumberByPersonsAddress(null));
//	    }
//
//	    @Test
//	    public void getAgeFromValidPersonTest() {
//	    	assertEquals(36, datatreatment.getAgeForPerson(personList.get(0)));
//	    }
//
//	    @Test
//	    public void getAgeFromNoValidPersonTest() {
//	    	assertEquals(0, datatreatment.getAgeFromPerson(emptyPerson));
//	    }
//
//	    @Test
//
//	    public void getAgeFromNullPersonTest() {
//	    	assertEquals(0, datatreatment.getAgeForPerson(null));
//	    }

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

//	    @Test
//	    public void getAgeFromValidBirthdate() {
//	    	assertEquals(36, datatreatment.getAgeWithBirthDate(medicalRecordsList.get(0).getBirthdate()));
//	    }
//	    
//	    @Test
//	    public void getAgeFromNoValidBirthdate() {
//	    	assertEquals(0, datatreatment.getAgeWithBirthDate(""));
//	    }
//
//	    @Test
//	    public void getAgeFromNullBirthdate() {
//	    	assertEquals(0, datatreatment.getAgeWithBirthDate(null));
//	    }

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
	    	
	    	allData.setPersons(new ArrayList<>());
	    	
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
	    	
	    	allData.setPersons(null);
	    	
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
	    	
	    	allData.setPersons(new ArrayList<>());
	    	
	        assertEquals(null, datatreatment.updatePerson(personToUpdateTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

//	    @Test
//	    public void updateNoExistingPersonListTest() {
//	    	assertEquals(null, datatreatment.updatePerson(emptyPerson));
//	    	assertEquals(personList, datatreatment.getPersons());
//	    }

	    @Test
	    public void updateNullPersonListTest() {
	    	
	    	assertEquals(null, datatreatment.updatePerson(null));
	    	
	    	assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void updatePersonNullListTest() {
	    	
	    	allData.setPersons(null);
	    	
	        assertEquals(null, datatreatment.updatePerson(personToUpdateTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

	    @Test
	    public void deletePersonNoEmptyListTest() {
	    	
	        List<Person> personListTest = new ArrayList<>(allData.getPersons());
	        
	        personListTest.remove(0);
	        
	        assertEquals(true, datatreatment.deletePerson(personToDeleteTest));
	        
	        assertEquals(personListTest, datatreatment.getPersons());
	    }

	    @Test
	    public void deletePersonEmptyListTest() {
	    	
	    	allData.setPersons(new ArrayList<>());
	    	
	        assertEquals(false, datatreatment.deletePerson(personToDeleteTest));
	        
	        assertEquals(List.of(), datatreatment.getPersons());
	    }

//	    @Test
//	    public void deleteNoExistingPersonListTest() {
//	    	assertEquals(false, datatreatment.deletePerson(emptyPerson));
//	    	assertEquals(personList, datatreatment.getPersons());
//	    }

	    @Test
	    public void deleteNullPersonListTest() {
	    	
	    	assertEquals(false, datatreatment.deletePerson(null));
	    	
	        assertEquals(personList, datatreatment.getPersons());
	    }

	    @Test
	    public void deletePersonNullListTest() {
	    	
	    	allData.setPersons(null);
	    	
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
	    	
	    	allData.setMedicalrecords(new ArrayList<>());
	    	
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
	    	allData.setMedicalrecords(null);
	    	
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
	    	
	    	allData.setMedicalrecords(new ArrayList<>());
	    	
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
	    	allData.setMedicalrecords(null);
	    	
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
	    	allData.setMedicalrecords(new ArrayList<>());
	    	
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
	    	allData.setMedicalrecords(null);
	    	
	        assertEquals(false, datatreatment.deleteMedicalRecords(medicalRecordsToDeleteTest));
	        
	        assertEquals(List.of(), datatreatment.getMedicalrecords());
	    }

	    @Test
	    public void saveFirestationNoEmptyListTest() {
	        List<FireStation> firestationsListTest = new ArrayList<>(allData.getFirestations());
	        
	        assertEquals(firestationToAdd, datatreatment.saveFireStation(firestationToAdd));
	        
	        firestationsListTest.add(firestationToAdd);
	        
	        assertEquals(firestationsListTest, datatreatment.getFirestations());
	    }

	    @Test
	    public void saveUniqueFirestationEmptyListTest() {
	    	allData.setFirestations(new ArrayList<>());
	    	
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
	    	allData.setFirestations(null);
	    	
	        assertEquals(firestationToAdd, datatreatment.saveFireStation(firestationToAdd));
	        
	        assertEquals(List.of(firestationToAdd), datatreatment.getFirestations());
	    }

	    @Test
	    public void deleteFirestationNoEmptyListTest() {
	    	
	        System.out.println(firestationsList);
	        List<FireStation> firestationsListTest = new ArrayList<>(allData.getFirestations());
	        System.out.println(firestationsList);
	        
	        assertEquals(true, datatreatment.deleteFireStation(firestationToDelete));
	        
	        System.out.println(firestationsList);
	        firestationsListTest.remove(0);
	        
	        System.out.println("1" + firestationsList);
	        assertEquals(datatreatment.getFirestations(), firestationsListTest);
	    }

	    @Test
	    public void deleteFirestationEmptyListTest() {
	    	allData.setFirestations(new ArrayList<>());
	    	
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
	    	allData.setFirestations(null);
	    	
	        assertEquals(false, datatreatment.deleteFireStation(firestationToDelete));
	        
	        assertEquals(List.of(), datatreatment.getFirestations());
	    }

}
