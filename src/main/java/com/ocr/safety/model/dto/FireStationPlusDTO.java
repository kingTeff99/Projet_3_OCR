package com.ocr.safety.model.dto;

import java.util.List;

import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.Person;

public class FireStationPlusDTO {
	
	private List<Person> person;
	
	private long NumberOfadult;
	
	private long NumberOfchildren;
	
	private Integer stationNumber;
	
	public FireStationPlusDTO(FireStationPlus fireStationPlus) {
		this.setPerson(fireStationPlus.getPerson());
		setNumberOfadult(fireStationPlus.getNumberOfAdult());
		setNumberOfchildren(fireStationPlus.getNumberOfChildren());
		this.setStationNumber(fireStationPlus.getStationNumber());
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public long getNumberOfadult() {
		return NumberOfadult;
	}

	public void setNumberOfadult(long numberOfadult) {
		NumberOfadult = numberOfadult;
	}

	public long getNumberOfchildren() {
		return NumberOfchildren;
	}

	public void setNumberOfchildren(long numberOfchildren) {
		NumberOfchildren = numberOfchildren;
	}

	public Integer getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
	}
	

}
