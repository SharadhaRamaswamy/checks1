package edu.scu.dp.smartcals.payment;
/**
 * @author Sharadha Ramaswmay
 */
public class NullCashPayment implements PaymentProduct{
	
	double amtPayable = 0.0;
	double amtToReturn = 0.0;
	@Override
	public boolean getPaymentStatus() {
		// TODO Auto-generated method stub
		return false;
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
	public void setValues(double amtPayable, double amtPaying) {
		// TODO Auto-generated method stub
		
	}


}
