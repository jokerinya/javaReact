package day4assignment3.concrete;

import day4assignment3.abstracts.BaseSaleManager;
import day4assignment3.abstracts.ICampaingService;
import day4assignment3.entities.Campaing;
import day4assignment3.entities.Player;
import day4assignment3.entities.Sale;

public class CashSaleManager extends BaseSaleManager {

	ICampaingService campaingService;

	public CashSaleManager(ICampaingService campaingService) {
		this.campaingService = campaingService;
	}

	public void cashSale(Player player, Sale sale ,Campaing campaing) {
		double salePrice = this.campaingService.getCampaingPrice(campaing, sale);
		super.saveSale(sale, player, campaing, salePrice);
	}
	
}
