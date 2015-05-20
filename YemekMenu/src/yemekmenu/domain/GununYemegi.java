package yemekmenu.domain;

import java.util.Date;

public class GununYemegi {
	private Date day;
	private String[] meal;

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String[] getMeal() {
		return meal;
	}

	public void setMeal(String[] meal) {
		this.meal = meal;
	}
}