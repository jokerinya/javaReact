package day4assignment2.concretes;

import day4assignment2.abstracts.ICustomerCheckService;
import day4assignment2.entities.Customer;

public class CustomerCheckManager implements ICustomerCheckService {
	// can be used for devolopment purposes
	// Here decouple us from Mernis
	@Override
	public boolean checkIfRealPerson(Customer customer) {
		return true;
	}
	
}
