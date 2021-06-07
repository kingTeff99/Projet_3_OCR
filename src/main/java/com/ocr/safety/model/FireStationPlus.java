package com.ocr.safety.model;

import java.util.List;

public class FireStationPlus {
	
	private List<Person> person;
	private long NumberOfAdult;
	private long NumberOfChildren;
	private Integer stationNumber;
	
	public FireStationPlus(List<Person> person, long numberOfAdult, long numberOfChildren, Integer stationNumber) {
		this.person = person;
		NumberOfAdult = numberOfAdult;
		NumberOfChildren = numberOfChildren;
		this.stationNumber = stationNumber;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public long getNumberOfAdult() {
		return NumberOfAdult;
	}

	public void setNumberOfAdult(long numberOfAdult) {
		NumberOfAdult = numberOfAdult;
	}

	public long getNumberOfChildren() {
		return NumberOfChildren;
	}

	public void setNumberOfChildren(long numberOfChildren) {
		NumberOfChildren = numberOfChildren;
	}

	public Integer getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
	}
		
}
