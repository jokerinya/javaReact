package day4assignment2;

import day4assignment2.abstracts.BaseCustomerManager;
import day4assignment2.adapters.MernisServiceAdapter;
import day4assignment2.concretes.CustomerCheckManager;
import day4assignment2.concretes.NeroCustomerManager;
import day4assignment2.concretes.StarbucksCustomerManager;
import day4assignment2.entities.Customer;

public class Main {

	public static void main(String[] args) {
		// Starbucks with Mernis
		BaseCustomerManager customerManager = new StarbucksCustomerManager(new MernisServiceAdapter());
		Customer properCustomer = new Customer("1", "İbrahim", "Şakacı", "1989", "68863159792");
		Customer improperCustomer = new Customer("2", "Demo", "Demo", "2000", "12345678901");
		customerManager.save(properCustomer); // Saved to db: İbrahim
		customerManager.save(improperCustomer); // Can't save to db
		// Nero without Mernis
		customerManager = new NeroCustomerManager();
		customerManager.save(properCustomer); // Saved to db: İbrahim
		customerManager.save(improperCustomer); // Saved to db: Demo   --> !!Doesn't check
		// We can use our custom CustomerCheckService for development
		// it will return true now
		customerManager = new StarbucksCustomerManager(new CustomerCheckManager());
		customerManager.save(properCustomer); // Saved to db: İbrahim
		customerManager.save(improperCustomer); // Saved to db: Demo
	}

}
