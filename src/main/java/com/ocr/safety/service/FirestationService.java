package com.ocr.safety.service;

import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.FireStationPlus;

public interface FirestationService {
	
	FireStationPlus getPersonFromFireStationArea(Integer station);
	
	FireStation saveFireStation(FireStation prototype);
	
	boolean deleteFireStation(FireStation prototype);
	

}
