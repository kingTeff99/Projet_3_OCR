package com.ocr.safety.model;

import java.util.List;

public class CommunityEmail {
	
	List<String> emailsOfCity;

	public CommunityEmail(List<String> email) {
		super();
		this.emailsOfCity = email;
	}

	public List<String> getEmail() {
		return emailsOfCity;
	}

	public void setEmail(List<String> email) {
		this.emailsOfCity = email;
	}

	@Override
	public String toString() {
		return  ""+ emailsOfCity ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailsOfCity == null) ? 0 : emailsOfCity.hashCode());
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
		CommunityEmail other = (CommunityEmail) obj;
		if (emailsOfCity == null) {
			if (other.emailsOfCity != null)
				return false;
		} else if (!emailsOfCity.equals(other.emailsOfCity))
			return false;
		return true;
	}
	
	
	

}
