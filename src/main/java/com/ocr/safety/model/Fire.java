package com.ocr.safety.model;

import java.util.List;

public class Fire {
	
	List<CompletePerson> peopleLiveAtThisAddress;

	public Fire(List<CompletePerson> peopleLiveAtThisAddress) {
		super();
		this.peopleLiveAtThisAddress = peopleLiveAtThisAddress;
	}

	public List<CompletePerson> getPeopleLiveAtThisAddress() {
		return peopleLiveAtThisAddress;
	}

	public void setPeopleLiveAtThisAddress(List<CompletePerson> peopleLiveAtThisAddress) {
		this.peopleLiveAtThisAddress = peopleLiveAtThisAddress;
	}

	@Override
	public String toString() {
		return "Fire " + peopleLiveAtThisAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((peopleLiveAtThisAddress == null) ? 0 : peopleLiveAtThisAddress.hashCode());
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
		Fire other = (Fire) obj;
		if (peopleLiveAtThisAddress == null) {
			if (other.peopleLiveAtThisAddress != null)
				return false;
		} else if (!peopleLiveAtThisAddress.equals(other.peopleLiveAtThisAddress))
			return false;
		return true;
	}
	
	

}
