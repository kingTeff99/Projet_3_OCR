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
	
	

}
