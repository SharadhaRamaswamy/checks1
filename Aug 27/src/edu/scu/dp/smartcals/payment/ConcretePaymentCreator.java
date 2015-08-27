package edu.scu.dp.smartcals.payment;

/**
 * 
 * @author Sharadha Ramaswamy
 *
 */
/**
 * 
 * Null Object Pattern and Factory method pattern is implemented
 *
 */
public class ConcretePaymentCreator implements PaymentCreator{
    private PaymentProduct pay;
	@Override
	public PaymentProduct makePayment(String type,long SmartCardNum) {
		if(type == "Coin")
			pay = new CoinPayment();
		else if(type == "NullCoin")
			pay = new NullCoinPayment();
		else if(type == "Cash")
			pay = new CashPayment();
		else if(type == "NullCash")
			pay = new NullCashPayment();
	    else if(type == "SmartCard")
			pay = new SmartCardPayment(SmartCardNum);
		return pay;
	}

}
