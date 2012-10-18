package org.integrallis.drools.loans.domain;

public final class MessageCatalog {
	public static final String MSG_ACME_FICO_680 = "Declined by ACME Mortgage because a FICO score of at least 680 is required";
	public static final String MSG_ACME_PRINCIPAL_50K = "Declined by ACME Mortgage because a Principal of at least $50,000 is required";
	public static final String MSG_ACME_4_UNIT_MAX = "Declined by ACME Mortgage because the number of units must not be greater than 4";
	public static final String MSG_ACME_1_UNIT_PRINCIPAL = "Declined by ACME Mortgage because the principal for a 1 unit property must be no more than $203,150";
	public static final String MSG_ACME_2_UNIT_PRINCIPAL = "Declined by ACME Mortgage because the principal for a 2 unit property must be no more than $259,850";
	public static final String MSG_ACME_3_UNIT_PRINCIPAL = "Declined by ACME Mortgage because the principal for a 3 unit property must be no more than $314,100";
	public static final String MSG_ACME_4_UNIT_PRINCIPAL = "Declined by ACME Mortgage because the principal for a 4 unit property must be no more than $390,400";
	public static final String MSG_ACME_MULT_OF_50_PRINCIPAL = "Declined by ACME Mortgage because a principal that is a multiple of $50 is required";
	public static final String MSG_ACME_LOAN_PURPOSE = "Declined by ACME Mortgage because lender only provides loans for purchasing or refinancing";
}
