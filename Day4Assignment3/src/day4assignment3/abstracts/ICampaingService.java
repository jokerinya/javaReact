package day4assignment3.abstracts;

import java.time.LocalDate;

import day4assignment3.entities.Campaing;
import day4assignment3.entities.Sale;

public interface ICampaingService {
	public void saveCampaing(Campaing campaing);
	public double getCampaingPrice(Campaing campaing, Sale sale);
	public void deleteCampaing(Campaing campaing);
	public void editCampaingName(Campaing campaing, String newName);
	public void editCampaingLastDate(Campaing campaing, LocalDate newlastDate);
	public void editCampaingDiscountRate(Campaing campaing, double newRate);
}
