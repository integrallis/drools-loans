/**
 * 
 */
package org.integrallis.drools.loans.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bsbodden
 *
 */
public class LoansUtil {
	private LoansUtil() {    	
    }
    
    /**
     * CashFlow based payment calculation assuming future value is 0 (loan paid off)
     * 
     * @return
     */
    public static double calculatePayment(double principal, double interest, int years) {
    	double ip = (interest / 100) / 12;
    	int n = years * 12;
    	return (-principal + (-principal / (Math.pow((1 + ip), n) - 1))) * -ip;    	
    }
    
	public static Date getDateForSimpleFormat(String dateString) {
        Date result = null;
		try {
			result = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			result = new Date();
		}
		return result;
	}
}
