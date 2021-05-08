package day5assignment1.core.concretes;

import day5assignment1.core.abstracts.UserAuthService;
import day5assignment1.entities.concretes.User;
import day5assignment1.gMailAuth.GmailAuthManager;

public class GmailAuthManagerAdapter implements UserAuthService {

	@Override
	public boolean checkUserCredentials(User user) {

		GmailAuthManager gAuthManager = new GmailAuthManager();

		if (gAuthManager.checkGmailAccount(user.getEmail())) {
			return true;
		}
		System.out.println("Gmail credentials failed.");
		return false;
	}

}
