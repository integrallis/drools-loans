[condition][]the lender is "{mortgage_company}"=mortgage:Mortgage(lender:lenderName == "{mortgage_company}",product:mortgageName)
[condition][]and there is an application=application:LoanApplication(lenders contains lender)
[condition][]- with a FICO score below {score}=ficoScore<{score}
[condition][]- with a Principal that is equal or greater than {principal}=principal>={principal}
[condition][]- with a Principal that is less than {principal}=principal<{principal}
[condition][]- with a Principal that is equal or more than {percentage}% of the Property Value=eval((((principal / propertyValue) * 100) > {percentage}))
[condition][]- with a Principal that is not a multiple of {factor}=eval(principal % {factor} != 0)
[condition][]- with a Number of Units greater than {units}=numberOfUnits>{units}
[condition][]- with a Number of Units equal to {units}=numberOfUnits=={units}
[condition][]- with a Loan Purpose that is not one of \[ {values} \]=loanPurpose not in ( {values} )
[condition][]- with a Property Type of "{property_type}"=propertyType=="{property_type}"
[condition][]- with an applicant that is younger that {age} years of age=eval(yearsOfAge(applicant.getDateOfBirth()) < {age})
[condition][]- with an applicant that is an employee of the lender=applicant.employer==lender
[condition][]- with an applicant that is not a resident=applicant.resident == false
[consequence][]reject the application because "{message}"=application.addMessage("Declined by " + lender + " because {message}");
[consequence][]failed to prequalify the application because "{message}"=application.addMessage("Declined by " + lender + " for " + product + " because {message}"); application.setPrequalified(false); update(application);
[consequence][]recommend "{message}"=application.addMessage("Lender " + lender + " recommends {message}");
