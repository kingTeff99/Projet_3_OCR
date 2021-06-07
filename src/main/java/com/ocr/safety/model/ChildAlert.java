package com.ocr.safety.model;

import java.util.List;

public class ChildAlert {
	
	private String address;
	
	private List<CompletePerson> children;
	
	private List<CompletePerson> adults;

	public ChildAlert(String address, List<CompletePerson> children, List<CompletePerson> adults) {
		super();
		this.address = address;
		this.children = children;
		this.adults = adults;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CompletePerson> getChildren() {
		return children;
	}

	public void setChildren(List<CompletePerson> children) {
		this.children = children;
	}

	public List<CompletePerson> getAdults() {
		return adults;
	}

	public void setAdults(List<CompletePerson> adults) {
		this.adults = adults;
	}

}
