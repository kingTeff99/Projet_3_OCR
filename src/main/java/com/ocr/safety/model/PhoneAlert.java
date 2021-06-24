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

	@Override
	public String toString() {
		return "PhoneAlert [phoneList=" + phoneList + ", firestation=" + firestation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firestation == null) ? 0 : firestation.hashCode());
		result = prime * result + ((phoneList == null) ? 0 : phoneList.hashCode());
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
		PhoneAlert other = (PhoneAlert) obj;
		if (firestation == null) {
			if (other.firestation != null)
				return false;
		} else if (!firestation.equals(other.firestation))
			return false;
		if (phoneList == null) {
			if (other.phoneList != null)
				return false;
		} else if (!phoneList.equals(other.phoneList))
			return false;
		return true;
	}
	
	

	
}
