package day4assignment2.concretes;

import day4assignment2.abstracts.BaseCustomerManager;
import day4assignment2.abstracts.ICustomerCheckService;
import day4assignment2.entities.Customer;

public class StarbucksCustomerManager extends BaseCustomerManager {
	// dependency injection
	public ICustomerCheckService checkService;

	public StarbucksCustomerManager(ICustomerCheckService checkService) {
		this.checkService = checkService;
	}

	@Override
	public void save(Customer customer) {
		if (checkService.checkIfRealPerson(customer)) {
			super.save(customer);			
		} else {
			System.out.println("Can't save to db");;
		}
	}
	
	
	

}
