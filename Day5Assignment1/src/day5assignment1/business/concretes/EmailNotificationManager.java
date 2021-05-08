package day5assignment1.business.concretes;

import day5assignment1.core.abstracts.UserNotificationService;
import day5assignment1.entities.concretes.User;

public class EmailNotificationManager implements UserNotificationService{

	@Override
	public void sendCustomNotification(User user, String message) {
		System.out.println("Email send to: " + user.getEmail() + " " + message);
	}
	
	@Override
	public void sendActivationLink(User user) {
		// Generate a link or use a service for this, maybe store that link to db.
		System.out.println("Activation link send to: " + user.getEmail() + ".");
	}
	
	@Override
	public boolean hasClickedToLink(User user) {
		// activate when the produced link clicked.
		boolean res = true;
		if (res) {
			System.out.println(user.getFullName() + " has clicked the link.");
		}
		return res;
	}

}
