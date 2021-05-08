package day5assignment1.business.abstracts;

import day5assignment1.entities.concretes.User;

public interface UserService {
	void add(User user);
	void delete(User user);
	void login(String email, String password);  // password should be encrypted...
	void logout(User user);
	void update(User user);
}
