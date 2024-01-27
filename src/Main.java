import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		BankLogic bank = new BankLogic();
		
		System.out.println("Skapar kund");
		bank.createCustomer("A", "B", "1");
		System.out.println("Skapar kund");
		bank.createCustomer("C", "D", "2");
		System.out.println("Skapar kund");
		bank.createCustomer("Lottis", "Lottensdotter", "120312-3456");
		System.out.println("Skapar kund");
		bank.createCustomer("E", "F", "1");
		System.out.println("Skapar kund");
		bank.createCustomer("G", "H", "4");
		System.out.println("Skapar kund");
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Hämtar alla kunder:");
		System.out.println(Arrays.toString(bank.getAllCustomers().toArray()));		
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Hämtar kund:");
		System.out.println(Arrays.toString(bank.getCustomer("120312-3456").toArray()));
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Skapar sparkonto");
		bank.createSavingsAccount("120312-3456");
		System.out.println("Skapar sparkonto");
		bank.createSavingsAccount("120312-3456");
		System.out.println("Skapar sparkonto");
		bank.createSavingsAccount("120312-3456");
		System.out.println("");
		System.out.println("Hämtar kund:");
		System.out.println(Arrays.toString(bank.getCustomer("120312-3456").toArray()));
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Ändrar kundnamn:");
		bank.changeCustomerName("Lotta", "Lottensdotter", "120312-3456");
		System.out.println("");	
		System.out.println("Hämtar kund:");
		System.out.println(Arrays.toString(bank.getCustomer("120312-3456").toArray()));
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");

		System.out.println("Sätter in pengar:");
		bank.deposit("120312-3456", 1002, 5000);
		System.out.println("");
		System.out.println("Hämtar konto:");
		System.out.println(bank.getAccount("120312-3456", 1002));
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Tar ut pengar:");
		bank.withdraw("120312-3456", 1002, 1000);
		System.out.println("");
		System.out.println("Hämtar konto:");
		System.out.println(bank.getAccount("120312-3456", 1002));		
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Avslutar konto:");
		System.out.println(bank.closeAccount("120312-3456", 1001));		
		System.out.println("");
		System.out.println("Hämtar konto:");
		System.out.println(bank.getAccount("120312-3456", 1001));
		System.out.println("");	
		System.out.println("Hämtar kund:");
		System.out.println(Arrays.toString(bank.getCustomer("120312-3456").toArray()));
		System.out.println("");
		System.out.println("* * *");
		System.out.println("");
		
		System.out.println("Raderar kund:");
		System.out.println(Arrays.toString(bank.deleteCustomer("120312-3456").toArray()));
		System.out.println("");
		System.out.println("Hämta alla kunder:");
		System.out.println(Arrays.toString(bank.getAllCustomers().toArray()));
		System.out.println("");
	}
	
}
