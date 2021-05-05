package day4assignment3.abstracts;

import day4assignment3.entities.Campaing;
import day4assignment3.entities.Player;
import day4assignment3.entities.Sale;

public interface ISaleService {
	public void saveSale(Sale sale, Player player, Campaing campaing, double salePrice);
}
