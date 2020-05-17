package model.services;

public interface OnLinePaymentService {
	
	double paymentFee(double amount);
	double interest(double amount, int months);

}
