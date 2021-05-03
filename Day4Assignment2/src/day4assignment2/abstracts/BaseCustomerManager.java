package day4assignment2.abstracts;

import day4assignment2.entities.Customer;


public abstract class BaseCustomerManager implements ICustomerService {
	// Nero and Starbucks Customer Managers inherit this
	@Override
	public void save(Customer customer) {
		System.out.println("Saved to db: " + customer.getFirstName());
	}
	
}
