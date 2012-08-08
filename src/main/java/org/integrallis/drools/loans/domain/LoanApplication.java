/*
 * @(#)LoanApplication.java	Aug 2, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package org.integrallis.drools.loans.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class LoanApplication {
	//private static Log log = LogFactory.getLog(LoanApplication.class);
	private String applicationName;	
	private Set<String> messages = new HashSet<String>();
	private double principal;
	private double monthlyDebtPayment;
	private double montlyIncome;
	private double propertyValue;
	private int ficoScore;
	private int numberOfUnits;
	private String loanPurpose;
	private String propertyType;
	private Set<String> mortgages = new HashSet<String>();
	private Set<String> lenders = new HashSet<String>();
	private Set<String> termsInYears = new HashSet<String>();
	private Applicant applicant;
	private boolean prequalified = true;
    
	public boolean isPrequalified() {
		return prequalified;
	}

	public void setPrequalified(boolean prequalified) {
		System.out.println("setting prequalified to " + prequalified);
		this.prequalified = prequalified;
	}

	/**
	 * @return Returns the ficoScore.
	 */
	public int getFicoScore() {
		return ficoScore;
	}

	/**
	 * @return Returns the monthlyDebtPayment.
	 */
	public double getMonthlyDebtPayment() {
		return monthlyDebtPayment;
	}
	/**
	 * @return Returns the montlyIncome.
	 */
	public double getMontlyIncome() {
		return montlyIncome;
	}
	/**
	 * @return Returns the principal.
	 */
	public double getPrincipal() {
		return principal;
	}
	/**
	 * @param ficoScore The ficoScore to set.
	 */
	public void setFicoScore(int ficoScore) {
		this.ficoScore = ficoScore;
	}
	
	/**
	 * @param monthlyDebtPayment The monthlyDebtPayment to set.
	 */
	public void setMonthlyDebtPayment(double monthlyDebtPayment) {
		this.monthlyDebtPayment = monthlyDebtPayment;
	}
	/**
	 * @param montlyIncome The montlyIncome to set.
	 */
	public void setMontlyIncome(double montlyIncome) {
		this.montlyIncome = montlyIncome;
	}
	/**
	 * @param principal The principal to set.
	 */
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	/**
	 * @return Returns the messages.
	 */
	public List<String> getMessages() {
		return new ArrayList<String>(messages);
	}
	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(List<String> messages) {
		this.messages.clear();
		this.messages.addAll(messages);
	}
	
	public void addMessage(String message) {
	    messages.add(message);	
	}
	
	public void addLender(String lender) {
		lenders.add(lender);
	}
	
	public void addTermInYears(String termInYears) {
		termsInYears.add(termInYears);
	}
	
	public void addMortgage(String mortgage) {
		mortgages.add(mortgage);
	}
	
	public void resetMessages() {
		messages.clear();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("Application for: " + applicant);
		sb.append(", Pre-qualified = " + isPrequalified()).append('\n');
		if (!messages.isEmpty()) {
	        for (Iterator<String> i = messages.iterator(); i.hasNext();) {
				sb.append(i.next()).append('\n');
			}
		}
		else {
			sb.append("No messages in this application");
		}
        return sb.toString();
	}
	/**
	 * @return Returns the applicationName.
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName The applicationName to set.
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return Returns the lenders.
	 */
	public Set<String> getLenders() {
		return lenders;
	}

	/**
	 * @return Returns the loanPurpose.
	 */
	public String getLoanPurpose() {
		return loanPurpose;
	}

	/**
	 * @return Returns the mortgages.
	 */
	public Set<String> getMortgages() {
		return mortgages;
	}

	/**
	 * @return Returns the numberOfUnits.
	 */
	public int getNumberOfUnits() {
		return numberOfUnits;
	}

	/**
	 * @return Returns the propertyType.
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * @return Returns the propertyValue.
	 */
	public double getPropertyValue() {
		return propertyValue;
	}

	/**
	 * @param lenders The lenders to set.
	 */
	public void setLenders(Set<String> lenders) {
		this.lenders = lenders;
	}

	/**
	 * @param loanPurpose The loanPurpose to set.
	 */
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	/**
	 * @param mortgages The mortgages to set.
	 */
	public void setMortgages(Set<String> mortgages) {
		this.mortgages = mortgages;
	}

	/**
	 * @param numberOfUnits The numberOfUnits to set.
	 */
	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	/**
	 * @param propertyType The propertyType to set.
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * @param propertyValue The propertyValue to set.
	 */
	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * @return Returns the termsInYears.
	 */
	public Set<String> getTermsInYears() {
		return termsInYears;
	}

	/**
	 * @param termsInYears The termsInYears to set.
	 */
	public void setTermsInYears(Set<String> termsInYears) {
		this.termsInYears = termsInYears;
	}
	
	public boolean principalIsMultipleOf(int factor) {
		return getPrincipal() % factor == 0;
	}
	
	public List<Mortgage> getMortgagesForApplication() {
		List<Mortgage> result = new ArrayList<Mortgage>();
		// find and add mortgages
		for (String mortgageName : getMortgages()) {
			for (String term : getTermsInYears()) {
				int termAsInt = Integer.parseInt(term);
				Mortgage mortgage = Mortgage.getMortgage(mortgageName, termAsInt);
				if (mortgage != null) {
	                result.add(mortgage);
				}
			}			
		}
		return result;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
    
}
