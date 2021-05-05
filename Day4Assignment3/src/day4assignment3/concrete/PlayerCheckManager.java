package day4assignment3.concrete;

import day4assignment3.abstracts.IPlayerCheckService;
import day4assignment3.entities.Player;

public class PlayerCheckManager implements IPlayerCheckService {
	@Override
	public boolean checkIfRealPerson(Player player) {
		return false;
	}
}
