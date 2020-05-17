package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnLinePaymentService onLinePaymentService;
	
	public ContractService(OnLinePaymentService onLinePaymentService) {
		this.onLinePaymentService = onLinePaymentService;
	}

	public void processContract(Contract contract, int months) {
		
		double portion = contract.getTotalValue() / months;
		
		for (int i=1; i<=months; i++) {
			Date date = addMonths(contract.getDate(), i);
			double updatedPortion = portion + onLinePaymentService.interest(portion, i);
			double fullPortion = updatedPortion + onLinePaymentService.paymentFee(updatedPortion);
			contract.addInstallment(new Installment(date, fullPortion));
		}
	}
	
	private Date addMonths(Date date, int n) {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
}
