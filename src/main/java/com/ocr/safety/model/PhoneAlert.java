package com.ocr.safety.model;

public class PhoneAlert {
	
	String phoneList;
	
	Integer firestation;

	public PhoneAlert(String phoneList, Integer firestation) {
		super();
		this.phoneList = phoneList;
		this.firestation = firestation;
	}

	public String getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(String phoneList) {
		this.phoneList = phoneList;
	}

	public Integer getFirestation() {
		return firestation;
	}

	public void setFirestation(Integer firestation) {
		this.firestation = firestation;
	}

	
}
