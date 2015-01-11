package org.integrallis.drools.loans;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.integrallis.drools.junit.BaseDroolsTestCase;
import org.integrallis.drools.loans.domain.Applicant;
import org.integrallis.drools.loans.domain.LoanApplication;
import org.integrallis.drools.loans.domain.Mortgage;
import org.integrallis.drools.loans.util.LoansUtil;
import org.junit.Before;
import org.junit.Test;


public class AcmeLoansDSLRuleFlowTest extends BaseDroolsTestCase {
	
	private LoanApplication application;
	

	public AcmeLoansDSLRuleFlowTest() {
		super("ksession-process");
	}
	
	@Before
	public void setUpBaseApplication() {
		application = new LoanApplication();
		
		application.setApplicant(new Applicant("Brian", "Sam-Bodden", LoansUtil.getDateForSimpleFormat("1972-06-21"), true, "Integrallis Software, LLC."));
		
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
		knowledgeSession.startProcess("org.integrallis.drools.loans");
        knowledgeSession.fireAllRules();
		assertTrue(application.getMessages().isEmpty());
	}	
	
	@Test
	public void testACMERuleFlowFailPrequalificationGetsRecommendation() {
		application.setFicoScore(600);
		application.setApplicant(new Applicant("Brian", "Sam-Bodden", LoansUtil.getDateForSimpleFormat("1972-06-21"), true, "ACME Mortgage"));
		
		knowledgeSession.insert(application);
		knowledgeSession.startProcess("org.integrallis.drools.loans");
		knowledgeSession.fireAllRules();
		
		String recommendation = "Lender ACME Mortgage recommends that you quit your job!";
		
		// there should be a recommendations
		assertTrue(application.getMessages().contains(recommendation));
	}
	
	@Test
	public void testACMERuleFlowPassPrequalificationFailFico() {
		application.setFicoScore(600);
		
		knowledgeSession.insert(application);
		knowledgeSession.startProcess("org.integrallis.drools.loans");
		knowledgeSession.fireAllRules();
		
		String declinedBecauseFico = "Declined by ACME Mortgage because a FICO score of at least 680 is required";
		
		System.out.println(application);
		
		assertTrue(application.getMessages().contains(declinedBecauseFico));	
	}
}
