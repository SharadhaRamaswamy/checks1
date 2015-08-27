package edu.scu.dp.smartcals.model;

/**
 * 
 * @author Sharadha Ramaswamy
 *
 */
public class NullSmartCardModel implements SmartCardModelInterface{

	private long smartCardId = 0;
	private double balance = 0.0;
	
	@Override
	public long getSmartCard() {
		// TODO Auto-generated method stub
		return smartCardId;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	
	public boolean getValidity(){
		return false;
	}



}
