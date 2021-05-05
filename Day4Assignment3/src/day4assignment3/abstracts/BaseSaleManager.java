package day4assignment3.abstracts;

import day4assignment3.entities.Campaing;
import day4assignment3.entities.Player;
import day4assignment3.entities.Sale;

public abstract class BaseSaleManager implements ISaleService {

	@Override
	public void saveSale(Sale sale, Player player, Campaing campaing, double salePrice) {
		System.out.println(player.getFullName() + " bought at the date " +
							sale.getSaleDate().toString() + ", with campaign: " + campaing.getName() + 
							" to $" + salePrice);
	}

}
