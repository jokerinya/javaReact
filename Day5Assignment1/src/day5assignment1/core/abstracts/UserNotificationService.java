package day5assignment1.core.abstracts;

import day5assignment1.entities.concretes.User;

public interface UserNotificationService {
	void sendCustomNotification(User user, String message);
	void sendActivationLink(User user);
	boolean hasClickedToLink(User user);
}
