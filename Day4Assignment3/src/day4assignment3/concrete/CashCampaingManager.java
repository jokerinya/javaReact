package day4assignment3.concrete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import day4assignment3.abstracts.ICampaingService;
import day4assignment3.entities.Campaing;
import day4assignment3.entities.Sale;

public class CashCampaingManager implements ICampaingService {

	@Override
	public void saveCampaing(Campaing campaing) {
		System.out.println(campaing.getName() + " have been saved to db.");
	}

	@Override
	public double getCampaingPrice(Campaing campaing, Sale sale) {
		// check if time of the campaign is passed or not
		if (campaing.getLastDate().atStartOfDay().compareTo(LocalDateTime.now()) < 1) {
			return sale.getBasePrice();
		}
		return sale.getBasePrice() * (1 - campaing.getDiscountRate());
	}

	@Override
	public void deleteCampaing(Campaing campaing) {
		System.out.println(campaing.getName() + " has been deleted.");
	}

	@Override
	public void editCampaingName(Campaing campaing, String newName) {
		campaing.setName(newName);
		System.out.println("New campaign name : " + campaing.getName());
	}

	@Override
	public void editCampaingLastDate(Campaing campaing, LocalDate newlastDate) {
		campaing.setLastDate(newlastDate);
		System.out.println("Last day of campaing is : " + campaing.getLastDate());
	}

	@Override
	public void editCampaingDiscountRate(Campaing campaing, double newRate) {
		campaing.setDiscountRate(newRate);
		System.out.println("New rate of campaing is: " +  campaing.getDiscountRate());
	}

}
