package com.ocr.safety.model;

import java.util.List;

public class ChildAlert {
	
	private String address;
	
	private List<ChildAlertPerson> children;
	
	private List<ChildAlertPerson> adults;

	public ChildAlert(String address, List<ChildAlertPerson> theChildren, List<ChildAlertPerson> theAdults) {
		super();
		this.address = address;
		this.children = theChildren;
		this.adults = theAdults;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ChildAlertPerson> getChildren() {
		return children;
	}

	public void setChildren(List<ChildAlertPerson> children) {
		this.children = children;
	}

	public List<ChildAlertPerson> getAdults() {
		return adults;
	}

	public void setAdults(List<ChildAlertPerson> adults) {
		this.adults = adults;
	}
	
	
}
