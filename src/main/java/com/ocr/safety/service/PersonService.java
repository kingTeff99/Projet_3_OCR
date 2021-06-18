package com.ocr.safety.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.ChildAlertPerson;
import com.ocr.safety.model.CommunityEmail;
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;


@Service
public class PersonService {
	
	private static final Logger logger = LogManager.getLogger(PersonService.class);
	
	@Autowired
	private DataTreatment dataTreatment;
	
	@Autowired
	MedicalrecordService medicalrecordService;
	
	public Person savePerson(Person prototype) {
		
		Person person = dataTreatment.savePerson(prototype);
		
		if(person != null) {
			
			logger.info("new person saved");
		}
		
		return person;
	}

	public Person updatePerson(Person prototype) {
		
		Person person = dataTreatment.updatePerson(prototype);
		
		if(person != null) {
			
			logger.info("person updated");
		}
		
		return person;
	}

	public boolean deletePerson(Person prototype) {
		
		boolean person = dataTreatment.deletePerson(prototype);
		
		if(person) {
			
			logger.info("person deleted");
		}
		
		return person;
	}

	public ChildAlert giveChildAlertByAddress(String address) {
		
		List<ChildAlertPerson> TheChildren = new ArrayList<>();
		
		List<ChildAlertPerson> TheAdults = new ArrayList<>();
		
		for (Person person : dataTreatment.getPersonByItsAddress(address)) {
			
			ChildAlertPerson childAlertPerson = new ChildAlertPerson(person.getFirstName(), person.getLastName(),
					address, dataTreatment.getAgeForPerson(person));
			
			if(childAlertPerson.getAge() <= 18) {
				
				TheChildren.add(childAlertPerson);
				
			} else {
				
				TheAdults.add(childAlertPerson);
			}
		}
		return new ChildAlert(address, TheChildren, TheAdults);
	}

	public CommunityEmail getCommunityEmailPersonsByCity(String city) {
		
		List<String> emailsRetrieved = new ArrayList<>();
		
		for (Person person : dataTreatment.getPersons()) {
			
			if(person.getCity().equals(city)) {
				
				emailsRetrieved.add(person.getEmail());
			}
		}
		return new CommunityEmail(emailsRetrieved);
	}



	public List<CompletePerson> getCompletePersonsByName(String firstName, String lastName) {
		
		List<Person> pers = dataTreatment.getPersons();
		
		List<CompletePerson> per = new ArrayList<CompletePerson>();
		
		per = pers.stream().filter(p -> p.getLastName().equals(lastName) && p.getFirstName().equals(firstName))
			.map(perso -> new CompletePerson(perso.getFirstName(), perso.getLastName(), perso.getAddress()
	            , perso.getCity(),perso.getZip(), perso.getPhone(), perso.getEmail(), dataTreatment.getAgeForPerson(perso)
	            , medicalrecordService.getMedications(perso), medicalrecordService.getAllergies(perso), 0))
			.collect(Collectors.toList());
		
		return per;
	}


}
