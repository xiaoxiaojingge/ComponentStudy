package com.itjing.designpatterns.proxy;

/**
 * @author: lijing
 * @Date: 2021年05月27日 17:00
 * @Description:
 */
public class Food {

	private String chicken;

	private String spicy;

	private String salt;

	public Food() {
	}

	public void addCondiment(String condiment) {
		System.out.println("addCondiment---------" + condiment);
	}

	public Food(String chicken, String spicy, String salt) {
		this.chicken = chicken;
		this.spicy = spicy;
		this.salt = salt;
	}

	public String getChicken() {
		return chicken;
	}

	public void setChicken(String chicken) {
		this.chicken = chicken;
	}

	public String getSpicy() {
		return spicy;
	}

	public void setSpicy(String spicy) {
		this.spicy = spicy;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Food{" + "chicken='" + chicken + '\'' + ", spicy='" + spicy + '\'' + ", salt='" + salt + '\'' + '}';
	}

}
