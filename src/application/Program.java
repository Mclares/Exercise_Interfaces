package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data:");
		System.out.print("Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.nextLine());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		Contract c = new Contract(number, date, totalValue);
		
		System.out.print("Enter number os installments: ");
		int qtInstallments = sc.nextInt();
		ContractService ps = new ContractService(new PaypalService());
		
		ps.processContract(c, qtInstallments);
		
		System.out.println();
		System.out.println("Installments:");
		for (Installment ins : c.getInstallments()) {
			System.out.println(ins); ;
		}

		sc.close();
	}

}
