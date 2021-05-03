package day4assignment2.adapters;

import java.rmi.RemoteException;
import java.util.Locale;

import day4assignment2.abstracts.ICustomerCheckService;
import day4assignment2.entities.Customer;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

// Compiler must be 1.8
public class MernisServiceAdapter implements ICustomerCheckService {

	@Override
	public boolean checkIfRealPerson(Customer customer) {
		long nationalityId = Long.parseLong(customer.getNationalityId()); // long
		String firstName = customer.getFirstName().toUpperCase(new Locale("tr", "TR")); // local TR
		String lastName = customer.getLastName().toUpperCase(new Locale("tr", "TR")); // local TR
		int yearOfBirth = Integer.parseInt(customer.getYearOfBirth()); // int

		KPSPublicSoapProxy soapClient = new KPSPublicSoapProxy();
		boolean res = false;

		try {
			res = soapClient.TCKimlikNoDogrula(nationalityId, firstName, lastName, yearOfBirth);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return res;
	}

}
