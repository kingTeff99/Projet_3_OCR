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

	@Override
	public String toString() {
		return "FireStationPlus [person=" + person + ", NumberOfAdult=" + NumberOfAdult + ", NumberOfChildren="
				+ NumberOfChildren + ", stationNumber=" + stationNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (NumberOfAdult ^ (NumberOfAdult >>> 32));
		result = prime * result + (int) (NumberOfChildren ^ (NumberOfChildren >>> 32));
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((stationNumber == null) ? 0 : stationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FireStationPlus other = (FireStationPlus) obj;
		if (NumberOfAdult != other.NumberOfAdult)
			return false;
		if (NumberOfChildren != other.NumberOfChildren)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (stationNumber == null) {
			if (other.stationNumber != null)
				return false;
		} else if (!stationNumber.equals(other.stationNumber))
			return false;
		return true;
	}
	
	
	
	
		
}
