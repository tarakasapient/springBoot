package com.exercise.springbootexercise;

import java.io.Serializable;
/**
 * Class responsilbe for populating for 
 * request object when adding a card to
 * database.
 * @author tartirum
 *
 */
public class SaveCreditCardRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String cardNumber;
	private double limit;
	private double balance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
