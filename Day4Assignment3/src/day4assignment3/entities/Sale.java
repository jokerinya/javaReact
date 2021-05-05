package day4assignment3.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sale implements IEntity {
	final String id;
	final double basePrice;
	final LocalDateTime saleDate;

	public Sale(double basePrice) {
		this.id = (Math.random() * Math.random()) + "";
		this.basePrice = basePrice;
		this.saleDate = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public LocalDate getSaleDate() {
		return saleDate.toLocalDate();
	}

}
