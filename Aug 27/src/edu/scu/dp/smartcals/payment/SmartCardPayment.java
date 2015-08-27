package edu.scu.dp.smartcals.payment;

import edu.scu.dp.smartcals.model.SmartCardModelInterface;

/**
 * 
 * @author Sharadha Ramaswamy
 *
 */

public class SmartCardPayment implements PaymentProduct{
	double amtPayable;
	long SmartCardNum;
	double amtPaying;
	double amtToReturn;
	
	public SmartCardPayment(long SmartCardNum)
	{
		this.SmartCardNum = SmartCardNum;
	}

	@Override
	public boolean getPaymentStatus() {
		if (amtPayable > amtPaying)
			return false;
		amtToReturn = amtPaying - amtPayable;	
		return true;
	}

	@Override
	public double getAmtToReturn() {
		// TODO Auto-generated method stub
		return amtToReturn;
	}

	@Override
	public double getAmtPayable() {
		// TODO Auto-generated method stub
		return amtPayable;
	}

	@Override
	public void setValues(double amtPayable,double amtPaying) {
		this.amtPayable = amtPayable;
		this.amtPaying = amtPaying;	
	}




}

