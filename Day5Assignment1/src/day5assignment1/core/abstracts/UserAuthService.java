package day5assignment1.core.abstracts;

import day5assignment1.entities.concretes.User;

public interface UserAuthService {
	boolean checkUserCredentials(User user);
}
