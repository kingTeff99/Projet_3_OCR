package com.ocr.safety.service;

import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.Person;

public interface PersonService {
	
	ChildAlert giveChildAlertByAddress(String address);
	
	Person savePerson(Person prototype);
	
	Person updatePerson(Person prototype);

	boolean deletePerson(Person prototype);

}
