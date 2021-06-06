package com.ocr.safety.model.dto;

import com.ocr.safety.model.Person;

public class PersonDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String city;
	
	private String zip;
	
	private String phone;
	
	private String email;
	
	public PersonDTO() {
		
	}

	public PersonDTO(Person person) {
		
		this.setFirstName(person.getFirstName());
		
		this.setLastName(person.getLastName());
		
		this.setAddress(person.getAddress());
		
		this.setCity(person.getCity()); 
		
		this.setZip(person.getZip());
		
		this.setPhone(person.getPhone()); 
		
		this.setEmail(person.getEmail());
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String Zip) {
		this.zip = Zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
