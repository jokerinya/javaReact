package day5assignment1.dataAccess.concretes;

import java.util.List;

import day5assignment1.dataAccess.abstracts.UserDao;
import day5assignment1.entities.concretes.User;

public class HibernateUserDao implements UserDao {

	@Override
	public void add(User user) {
		System.out.println(user.getFullName() + " has been added to db.");
	}

	@Override
	public void update(User user) {
		System.out.println(user.getFullName() + " has been updated.");
	}

	@Override
	public void delete(User user) {
		System.out.println(user.getFullName() + " has been deleted from db.");
	}

	@Override
	public User get(String email) {
		// after db query returns either null or User obj...
		return null;
		// For Login testing should return a dummy user obj.
		// return new User("Valid", "User", "valid@mypage.com", "123456");
	}

	@Override
	public List<User> getAll() {
		// with db query get all users (if necessary)
		return null;
	}

	@Override
	public void login(User user) {
		user.setLogin(true);
	}

	@Override
	public void logout(User user) {
		user.setLogin(false);
	}

}
