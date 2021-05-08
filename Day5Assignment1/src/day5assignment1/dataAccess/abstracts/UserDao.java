package day5assignment1.dataAccess.abstracts;

import java.util.List;

import day5assignment1.entities.concretes.User;

public interface UserDao {
	void add(User user);

	void update(User user);

	void delete(User user);

	User get(String email); // All email will be unique in the system

	List<User> getAll();
	
	void login(User user);
	
	void logout(User user);
}
