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

	@Override
	public String toString() {
		return "ChildAlert [address=" + address + ", children=" + children + ", adults=" + adults + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((adults == null) ? 0 : adults.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
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
		ChildAlert other = (ChildAlert) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (adults == null) {
			if (other.adults != null)
				return false;
		} else if (!adults.equals(other.adults))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		return true;
	}
	
	
	
	
}
