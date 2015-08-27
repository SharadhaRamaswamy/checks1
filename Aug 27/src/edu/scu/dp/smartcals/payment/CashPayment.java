package edu.scu.dp.smartcals.payment;

/**
 * 
 * @author Sharadha Ramaswamy
 *
 */

public class CashPayment implements PaymentProduct{
	double amtPayable;
	double amtToReturn;
	double totValue;
 	double fiveDollar;
	double tenDollar;
	double oneDollar;
	double amtPaying;
	

	@Override
	public boolean getPaymentStatus(){
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
	    return amtPayable;
		
	}
	@Override
	public void setValues(double amtPayable,double amtPaying) {
		this.amtPayable = amtPayable;
		this.amtPaying = amtPaying;
	}



}
