package org.integrallis.drools.loans;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.integrallis.drools.junit.BaseDroolsTestCase;
import org.integrallis.drools.loans.domain.Applicant;
import org.integrallis.drools.loans.domain.LoanApplication;
import org.integrallis.drools.loans.domain.Mortgage;
import org.integrallis.drools.loans.util.LoansUtil;
import org.junit.Before;
import org.junit.Test;

public class AcmeLoansDSLTest extends BaseDroolsTestCase {

	private LoanApplication application;

	public AcmeLoansDSLTest() {
		super("acme-loans.dslr", "loans.dsl");
	}

	@Before
	public void setUpBaseApplication() {
		application = new LoanApplication();

		application.setApplicant(new Applicant("Brian", "Sam-Bodden", LoansUtil
				.getDateForSimpleFormat("1972-06-21"), true,
				"Integrallis Software, LLC."));

		application.addLender("ACME Mortgage");
		application.addTermInYears("5");
		application.addTermInYears("15");
		application.addTermInYears("25");
		application.addTermInYears("30");
		application.addMortgage("XA-25");
		application.addMortgage("XA-30");
		application.addMortgage("XB-20");
		application.addMortgage("XC-15");

		application.setFicoScore(681);
		application.setPrincipal(50000);
		application.setPropertyValue(75000);
		application.setNumberOfUnits(1);

		application.setLoanPurpose("Purchase");
		application.setPropertyType("Primary Residence");

		for (Mortgage mortgage : application.getMortgagesForApplication()) {
			knowledgeSession.insert(mortgage);
		}
	}

	@Test
	public void testBaseTest() {
		knowledgeSession.insert(application);
		knowledgeSession.fireAllRules();
		assertTrue(application.getMessages().isEmpty());
	}

	@Test
	public void testACMEFico() {
		application.setFicoScore(600);
		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because of low FICO",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because a FICO score of at least 680 is required"));
	}

	@Test
	public void testACMEPrincipal() {
		application.setPrincipal(49950);
		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because of principal is too low",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because a Principal of at least $50,000 is required"));
	}

	@Test
	public void testACMENumberOfUnits() {
		application.setNumberOfUnits(5);
		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the number of units is greater than 5",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the number of units must not be greater than 4"));
	}

	@Test
	public void testACMEOneUnitPrincipal() {
		application.setNumberOfUnits(1);
		application.setPrincipal(203200);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the principal for a 1 unit property must be no more than $203,150",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the principal for a 1 unit property must be no more than $203,150"));
	}

	@Test
	public void testACMETwoUnitPrincipal() {
		application.setNumberOfUnits(2);
		application.setPrincipal(259900);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the principal for a 2 unit property must be no more than $259,850",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the principal for a 2 unit property must be no more than $259,850"));
	}

	@Test
	public void testACMEThreeUnitPrincipal() {
		application.setNumberOfUnits(3);
		application.setPrincipal(314150);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the principal for a 3 unit property must be no more than $314,100",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the principal for a 3 unit property must be no more than $314,100"));
	}

	@Test
	public void testACMEFourUnitPrincipal() {
		application.setNumberOfUnits(4);
		application.setPrincipal(390450);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the principal for a 4 unit property must be no more than $390,400",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the principal for a 4 unit property must be no more than $390,400"));
	}

	@Test
	public void testACMEPrincipalMultipleOf50() {
		application.setPrincipal(390401);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because a principal that is a multiple of $50 is required",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because a principal that is a multiple of $50 is required"));
	}

	@Test
	public void testACMELoanPurpose() {
		application.setLoanPurpose("Improvement");

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because lender only provides loans for purchasing or refinancing",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because lender only provides loans for purchasing or refinancing"));
	}

	@Test
	public void testACMEPercentOfProperyPrimaryResidence() {
		application.setPropertyType("Primary Residence");
		application.setPropertyValue(70000);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because lender will not loan more than 70% of the value a primary residence",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because lender will not loan more than 70% of the value a primary residence"));
	}

	@Test
	public void testACMEPercentOfProperyInvestmentProperty() {
		application.setPropertyType("Investment Property");
		application.setPropertyValue(51000);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because lender will not loan more than 95% of the value a investment property",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because lender will not loan more than 95% of the value a investment property"));
	}

	@Test
	public void testACMEApplicantIsTooYoung() {
		int year = Calendar.getInstance().get(Calendar.YEAR) - 17;
		application.getApplicant().setDateOfBirth(
				LoansUtil.getDateForSimpleFormat("" + year + "-06-21"));

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the primary applicant must be older than 18 years of age",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the primary applicant must be older than 18 years of age"));
	}

	@Test
	public void testACMEApplicantCannotBeAnEmployee() {
		application.getApplicant().setEmployer("ACME Mortgage");

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the primary applicant cannot be an ACME-Loans employee",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the primary applicant cannot be an ACME-Loans employee"));
	}

	@Test
	public void testACMEApplicantMustBeAResident() {
		application.getApplicant().setResident(false);

		knowledgeSession.insert(application);

		knowledgeSession.fireAllRules();

		assertTrue(
				"Application should have been declined because the primary applicant must be a US resident",
				application
						.getMessages()
						.contains(
								"Declined by ACME Mortgage because the primary applicant must be a US resident"));

	}
}
