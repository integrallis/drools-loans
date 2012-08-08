package org.integrallis.drools.loans.domain;

import java.util.Date;

public class Applicant {
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private boolean isResident;
	private String employer;
	
	public Applicant() {}
	
	public Applicant(String firstName, String lastName, Date dateOfBirth,
			boolean isResident, String employer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.isResident = isResident;
		this.employer = employer;
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
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public boolean isResident() {
		return isResident;
	}
	
	public void setResident(boolean isResident) {
		this.isResident = isResident;
	}
	
	public String getEmployer() {
		return employer;
	}
	
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
