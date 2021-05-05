package day4assignment3;

import java.time.LocalDate;

import day4assignment3.adapters.MernisCheckManager;
import day4assignment3.concrete.CashCampaingManager;
import day4assignment3.concrete.CashSaleManager;
import day4assignment3.concrete.PlayerManager;
import day4assignment3.entities.Campaing;
import day4assignment3.entities.Player;
import day4assignment3.entities.Sale;

public class Main {

	public static void main(String[] args) {
		// Player & PlayerManager
		Player joker = new Player("01234567890", "Trial", "Demo", "1989");
		PlayerManager playerManager = new PlayerManager(new MernisCheckManager());
		playerManager.editfistName(joker, "ibrahim"); // First name has been changed: ibrahim
		playerManager.editLastName(joker, "şakacı"); // Last name has been changed: şakacı
		playerManager.editNationalityID(joker, "68863159792"); // National ID has been changed: 68863159792
		playerManager.save(joker); // Saved to db: ibrahim şakacı (After Mernis Check)
		// playerManager.delete(joker);

		// Campaign & CashSaleManager
		Campaing campaing = new Campaing("1", "Mother's Day Campaign", LocalDate.of(2021, 6, 1), 0.30);
		CashCampaingManager campaingManager = new CashCampaingManager();
		CashSaleManager cashSaleManager = new CashSaleManager(campaingManager); // Dependency Injection

		cashSaleManager.cashSale(joker, new Sale(20.0), campaing);
		// ibrahim şakacı bought at the date 2021-05-05,
		// with campaign: New Year Campaign to $14.0

		// Campaign Functionality
		campaingManager.editCampaingName(campaing, "Father's Day Campaing");
		// New campaign name : Father's Day Campaing
		campaingManager.editCampaingLastDate(campaing, LocalDate.of(2021, 7, 1));
		// Last day of campaing is : 2021-07-01
		campaingManager.editCampaingDiscountRate(campaing, 0.18);
		// New rate of campaing is: 0.18
		campaingManager.deleteCampaing(campaing);
		// Father's Day Campaing has been deleted.

	}

}
