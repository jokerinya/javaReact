package day4assignment3.adapters;

import java.rmi.RemoteException;
import java.util.Locale;
import day4assignment3.abstracts.IPlayerCheckService;
import day4assignment3.entities.Player;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;
//Compiler must be 1.8
public class MernisCheckManager implements IPlayerCheckService {

	@Override
	public boolean checkIfRealPerson(Player player) {
		boolean res = false;
		// ready fields for query
		long nationalityId = Long.parseLong(player.getNationlityId()); // long
		String firstName = player.getFirstName().toUpperCase(new Locale("tr", "TR")); // local TR
		String lastName = player.getLastName().toUpperCase(new Locale("tr", "TR")); // local TR
		int yearOfBirth = Integer.parseInt(player.getYearOfBirth()); // int
		// check
		KPSPublicSoapProxy mernisClient = new KPSPublicSoapProxy();
		try {
			res = mernisClient.TCKimlikNoDogrula(nationalityId, firstName, lastName, yearOfBirth);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
				
		return res;
	}

}
