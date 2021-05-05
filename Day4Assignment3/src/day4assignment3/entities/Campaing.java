package day4assignment3.entities;

import java.time.LocalDate;

public class Campaing implements IEntity {
	String id;
	String name;
	LocalDate lastDate;
	double discountRate;

	public Campaing(String id, String name, LocalDate lastDate, double discountRate) {
		this.id = id;
		this.name = name;
		this.lastDate = lastDate;
		// arrange discountRate between 0 and 1;
		if (discountRate > 1) {
			this.discountRate = 1;
		} else if (discountRate < 0) {
			this.discountRate = 0;
		} else {
			this.discountRate = discountRate;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

}
