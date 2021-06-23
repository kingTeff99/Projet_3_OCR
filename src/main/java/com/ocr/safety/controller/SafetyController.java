package com.ocr.safety.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.safety.model.AllData;
import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.CommunityEmail;
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Fire;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.FirestationService;
import com.ocr.safety.service.MedicalrecordService;
import com.ocr.safety.service.PersonService;

@RestController
public class SafetyController {
	
	@Autowired
	private DataTreatment dataTreatment;
	
	@Autowired
	private FirestationService firestationService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private MedicalrecordService medicalrecordService;
	
	//Display all data present in Json file
	@GetMapping(value = "/")
	public  AllData displayData() {
		
		return dataTreatment.loadFile();
	}
	
	//URL1 FONCTIONNE!!!
	@GetMapping(value = "/firestation")
	public FireStationPlus getPersonByItsStationNumber(@RequestParam int stationNumber) {
		
		return firestationService.getPersonFromFireStationArea(stationNumber);
		
	}
	
	//URL2 FONCTIONNE!!!
	@GetMapping(value = "/childAlert")
	public ChildAlert getChildrenByAddress(@RequestParam String address) {
		
		return personService.giveChildAlertByAddress(address);
	}
	
	//URL3 FONCTIONNE!!!
	@GetMapping(value = "/phoneAlert")
	public List<String> getPhoneNumbersByItsStationNumber(@RequestParam int firestation) {
			
		return firestationService.getPhoneNumberByFireStationNumber(firestation);
			
	}
	
	//URL4 FONCTIONNE!!!
	@GetMapping(value = "/fire")
	public Fire getAllPersonsByTheirAddress(@RequestParam String address) {
				
		return firestationService.getPersonsByItsAddress(address);
				
	}
	
	//URL5 FONCTIONNE!!!
	@GetMapping(value = "/flood/stations")
	public List<Fire> getAllPersonsLiveInThisArea(@RequestParam List<Integer> stations) {
					
		return firestationService.getPersonsByItsStationNumberArea(stations);
		
//		System.out.println(Arrays.asList(stations));
		
		
		
//			return null;
			
			
	}
	
	//URL6 FONCTIONNE!!!
	@GetMapping(value = "/personInfo")
	public List<CompletePerson> getPersonsFromNames(@RequestParam(value = "firstName", required = false) String firstName
			, @RequestParam(value = "lastName", required = true) String lastName) {
						
		return personService.getCompletePersonsByName(firstName, lastName);
						
	}
		
	//URL7 FONCTIONNE!!!
	@GetMapping(value = "/communityEmail")
	public CommunityEmail getEmailsFromTheCity(@RequestParam String city) {
				
		return personService.getCommunityEmailPersonsByCity(city);
				
	}	
		
	
	//---------------------------------------------------------------------------------------
	
	@GetMapping(value = "/person")
	public List<Person> listeDesPersonnes() {
		
		return dataTreatment.getPersons();
		
	}
	
	@PostMapping(value = "/person")
    public Person addPerson(@RequestBody Person person) {
		
        return personService.savePerson(person);
        
    }
	
	@PutMapping(value = "/person")
    public Person updatePerson(@RequestBody Person person) {
		
        return personService.updatePerson(person);
        
    }
	
	@DeleteMapping(value = "/person")
    public boolean deletePerson(@RequestBody Person person) {
		
        return personService.deletePerson(person);
        
    }
	
	//---------------------------------------------------------------------------------------
	
	@GetMapping(value = "/medicalRecord")
	public List<MedicalRecord> medicalInformations() {
		
		return dataTreatment.getMedicalrecords();
		
	}
	
	
	@PostMapping(value = "/medicalRecord")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
        return medicalrecordService.saveMedicalRecords(medicalRecord);
        
    }
	
	
	@PutMapping(value = "/medicalRecord")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
        return medicalrecordService.updateMedicalRecords(medicalRecord);
        
    }
	
	
	@DeleteMapping(value = "/medicalRecord")
    public boolean deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
        return medicalrecordService.deleteMedicalRecords(medicalRecord);
        
    }
	
	
	//---------------------------------------------------------------------------------------
	

	@PostMapping(value = "/firestation")
    public FireStation addFireStation(@RequestBody FireStation firestation) {
		
        return firestationService.saveFireStation(firestation);
        
    }
	
//	@PutMapping(value = "/firestation")
//    public FireStation updateFireStation(@RequestBody FireStation firestation) {
//		
//        return firestationService.updateFireStation(firestation);
//    }
	
	@DeleteMapping(value = "/firestation")
    public boolean deleteFireStation(@RequestBody FireStation firestation) {
		
        return firestationService.deleteFireStation(firestation);
        
    }
	
}
	
