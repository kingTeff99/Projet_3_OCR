package com.ocr.safety.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.CompletePerson;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;


@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private DataTreatment dataTreatment;

	@Override
	public Person savePerson(Person prototype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updatePerson(Person prototype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePerson(Person prototype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChildAlert giveChildAlertByAddress(String address) {
		
		List<CompletePerson> TheChildren = new ArrayList<>();
		
		List<CompletePerson> TheAdults = new ArrayList<>();
		
		for (Person person : dataTreatment.getPersonByItsAddress(address)) {
			
			CompletePerson completePerson = new CompletePerson(person.getFirstName(), person.getLastName(),
					address, dataTreatment.getAgeForPerson(person));
			
			if(completePerson.getAge() <= 18) {
				
				TheChildren.add(completePerson);
				
			} else {
				
				TheAdults.add(completePerson);
			}
		}
		return new ChildAlert(address, TheChildren, TheAdults);
	}

}
