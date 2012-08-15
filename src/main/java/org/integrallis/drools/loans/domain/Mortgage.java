/*
 * @(#)Mortgage.java	Oct 15, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package org.integrallis.drools.loans.domain;

import java.util.ArrayList;
import java.util.List;


public class Mortgage {
	
	private static Mortgage A105VB_5 = new Mortgage("A1-05VB", "ABC Credit", 8.00, 5);
	private static Mortgage A2_15 = new Mortgage("A2-15", "ABC Credit", 8.50, 15);
	private static Mortgage H1_1_1520_15 = new Mortgage("H1-1-1520", "H.I. Finance", 8.99, 15);
	private static Mortgage H1_1_1520_20 = new Mortgage("H1-1-1520", "H.I. Finance", 8.99, 20);
	private static Mortgage XA25_30_25 = new Mortgage("XA-25", "ACME Mortgage", 10.75, 25);
	private static Mortgage XA25_30_30 = new Mortgage("XA-30", "ACME Mortgage", 10.75, 30);
	private static Mortgage XB_20 = new Mortgage("XB-20", "ACME Mortgage", 10.00, 20);
	private static Mortgage XC_15 = new Mortgage("XC-15", "ACME Mortgage", 9.50, 15);
	
	public static List<Mortgage> mortgages = new ArrayList<Mortgage>();
	static {
		mortgages.add(A105VB_5);
		mortgages.add(A2_15);
		mortgages.add(H1_1_1520_15);
		mortgages.add(H1_1_1520_20);
		mortgages.add(XA25_30_25);
		mortgages.add(XA25_30_30);
		mortgages.add(XB_20);
		mortgages.add(XC_15);
	}
	
	public static Mortgage getMortgage(String name, int term) {
		Mortgage result = null;
		for (Mortgage mortgage : mortgages) {
			if (mortgage.getMortgageName().equals(name)
					&& (mortgage.getYears() == term)) {
				result = mortgage;
			}
		}
		return result;
    }
	
    private String lenderName;
    private String mortgageName;
    private double interestRate;
    private int years;
    
    public Mortgage() {}
    
    public Mortgage(String mortgageName, String lenderName, double interestRate, int years) {
		this.lenderName = lenderName;
		this.mortgageName = mortgageName;
		this.interestRate = interestRate;
		this.years = years;
    }
   
	/**
	 * @return Returns the lenderName.
	 */
	public String getLenderName() {
		return lenderName;
	}
	/**
	 * @return Returns the mortgageName.
	 */
	public String getMortgageName() {
		return mortgageName;
	}

	/**
	 * @param lenderName The lenderName to set.
	 */
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	/**
	 * @param mortgageName The mortgageName to set.
	 */
	public void setMortgageName(String mortgageName) {
		this.mortgageName = mortgageName;
	}
	/**
	 * @return Returns the interestRate.
	 */
	public double getInterestRate() {
		return interestRate;
	}
	/**
	 * @return Returns the years.
	 */
	public int getYears() {
		return years;
	}
	/**
	 * @param interestRate The interestRate to set.
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * @param years The years to set.
	 */
	public void setYears(int years) {
		this.years = years;
	}
	
	@Override
	public String toString() {
		return mortgageName + ", " + lenderName + ", " + interestRate + ", " + years;		
	}
	
}
