package day4assignment3.abstracts;

import day4assignment3.entities.Player;

public abstract class BasePlayerManager implements IPlayerService {

	@Override
	public void save(Player player) {
		// Some default processes, and adding player to db without checking if
		// the person is real or not.
		System.out.println("Saved to Db successfully: " + player.getFullName());
	}
	
	@Override
	public void delete(Player player) {
		System.out.println(player.getFullName() + " has been deleted (DEFAULT).");
	}
}
