package day4assignment3.concrete;

import day4assignment3.abstracts.BasePlayerManager;
import day4assignment3.abstracts.IPlayerCheckService;
import day4assignment3.entities.Player;

public class PlayerManager extends BasePlayerManager {
	
	public IPlayerCheckService playerCheckService;

	public PlayerManager(IPlayerCheckService playerCheckService) {
		this.playerCheckService = playerCheckService;
	}
	
	@Override
	public void save(Player player) {
		if (playerCheckService.checkIfRealPerson(player)) {
			System.out.println("Saved to db: " + player.getFullName());
		}
	}
	
	@Override
	public void delete(Player player) {
		System.out.println(player.getFullName() + " has been deleted from db.");
	}
	
	public void editfistName(Player player, String newFirstName) {
		player.setFirstName(newFirstName);
		System.out.println("First name has been changed: " + player.getFirstName());
	}
	public void editLastName(Player player, String newLastName) {
		player.setLastName(newLastName);
		System.out.println("Last name has been changed: " + player.getLastName());
	}
	public void editNationalityID(Player player, String newNationalityID) {
		player.setNationlityId(newNationalityID);
		System.out.println("National ID has been changed: " + player.getNationlityId());
	}
	public void editYearOfBirth(Player player, String newYearOfBirth) {
		player.setYearOfBirth(newYearOfBirth);
		System.out.println("First name has been changed: " + player.getYearOfBirth());
	}

}
