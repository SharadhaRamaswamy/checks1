package edu.scu.dp.smartcals.payment;

/**
 * 
 * @author Sharadha Ramaswamy
 *
 */

public interface PaymentCreator {
	
	public PaymentProduct makePayment(String type,long SmartCardNum);
}
