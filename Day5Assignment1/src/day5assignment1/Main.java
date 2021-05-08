package day5assignment1;

import day5assignment1.business.abstracts.UserService;
import day5assignment1.business.concretes.UserManager;
import day5assignment1.core.concretes.GmailAuthManagerAdapter;
import day5assignment1.business.concretes.EmailNotificationManager;
import day5assignment1.business.concretes.UserAuthManager;
import day5assignment1.dataAccess.concretes.HibernateUserDao;
import day5assignment1.entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		
		// user manager all dependency injections made with Interfaces
		UserService userManager = new UserManager(new EmailNotificationManager(), 
												  new UserAuthManager(), 
												  new HibernateUserDao());
		// Some demo users, and console outputs
		User userNameError = new User("T", "One", "wrong@my.com", "123456");
		userManager.add(userNameError);
		// First and last name must be at least 2 chars.
		// User couldn't be added.
		User userLastNameError = new User("Trial", "O", "wrong@my.com", "123456");
		userManager.add(userLastNameError);
		// First and last name must be at least 2 chars.
		// User couldn't be added.
		User userEmailError = new User("Trial", "One", "wrong#my.com", "123456");
		userManager.add(userEmailError);
		// Email address error, please check your email address.
		// User couldn't be added.
		User userPasswordError = new User("Trial", "One", "wrong@my.com", "12345");
		userManager.add(userPasswordError);
		// Password should be at least 6 chars, please check your password.
		// User couldn't be added.
		
		
		// True user
		User user = new User("Valid", "User", "valid@mypage.com", "123456");
		userManager.add(user);
		// Activation link send to: valid@mypage.com.
		// Valid User has clicked the link.
		// Valid User has been added to db.
		
		// **** LOGIN ****
		// For simulation added HibernateDoa a dummy data which returns "user" obj.
		userManager.login("valid@my.page", "1234"); 
		// Check your password.
		userManager.login("valid@my.page", "123456"); 
		// Welcome Valid User, login successful!
		
		
		
		// **** GMAIL ADAPTER ****
		UserService userManager2 = new UserManager(new EmailNotificationManager(), 
												   new GmailAuthManagerAdapter(), 
												   new HibernateUserDao());
		
		User gmailUser = new User("Gmail", "User", "user@gmail.com", "gmailGeneratedPassword");
		userManager2.add(gmailUser);
		// Activation link send to: user@gmail.com.
		// Gmail User has clicked the link.
		// Gmail User has been added to db.
		
		
		
	}

}
