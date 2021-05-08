package day5assignment1.business.concretes;

import day5assignment1.business.abstracts.UserService;
import day5assignment1.core.abstracts.UserAuthService;
import day5assignment1.core.abstracts.UserNotificationService;
import day5assignment1.dataAccess.abstracts.UserDao;
import day5assignment1.entities.concretes.User;

public class UserManager implements UserService {

	UserNotificationService notificationService;
	UserAuthService authService;
	UserDao userDao;
	
	public UserManager(UserNotificationService notificationService, UserAuthService authService, UserDao userDao) {
		this.notificationService = notificationService;
		this.authService = authService;
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		// First check if there is any user with same email
		if (userDao.get(user.getEmail()) != null) {
			System.out.println("There is a user with that email. Login instead.");
			return;
		}
		// check credentials
		if (authService.checkUserCredentials(user)) {
			// send link
			notificationService.sendActivationLink(user);
			// link clicked
			if (notificationService.hasClickedToLink(user)) {
				userDao.add(user);
			} else {
				System.out.println("You must click the link to activate your account.");
			}
			return;
		}
		System.out.println("User couldn't be added.");
	}

	@Override
	public void delete(User user) {
		// Check User has authentication and authorization
		userDao.delete(user);
	}

	@Override
	public void login(String email, String password) {
		User user = userDao.get(email);
		if (user == null) {
			System.out.println("We couldn't find your email, check your email address or register first.");
			return;
		}
		if (user.getPassword() != password) {
			System.out.println("Check your password.");
			return;
		}
		System.out.println("Welcome " + user.getFullName() + ", login successful!");
		// Produce a Unique Token for user for a specific time so user can make request with that
		// and store it to DB
	}

	@Override
	public void logout(User user) {
		// Erase the token from DB.
		userDao.logout(user);
		System.out.println("Logout successful.");

	}

	@Override
	public void update(User user) {
		// Put new info of the user to DB
		userDao.update(user);
	}

}
