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
	
	

}
