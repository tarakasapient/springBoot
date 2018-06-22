package com.exercise.springbootexercise;

/**
 * Request object for carrying name and amount to api
 * @author tartirum
 *
 */

public class ChargeAmountRequest {

	private String name;
	private double amount;

	public ChargeAmountRequest() {

	}

	public ChargeAmountRequest(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
