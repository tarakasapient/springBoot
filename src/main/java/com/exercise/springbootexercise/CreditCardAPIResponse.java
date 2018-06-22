package com.exercise.springbootexercise;

/**
 * Response object
 * 
 * @author tartirum
 *
 */
public class CreditCardAPIResponse {
	/**
	 * Default Constructor
	 */
	public CreditCardAPIResponse() {

	}

	/**
	 * Paramaterized constructor
	 * 
	 * @param name
	 * @param creditCardNumber
	 * @param limit
	 * @param pound
	 * @param amount
	 */
	public CreditCardAPIResponse(String name, String creditCardNumber, String limit, String pound, String amount) {
		this.name = name;
		this.creditCardNumber = creditCardNumber;
		this.limit = limit;
		this.pound = pound;
		this.amount = amount;
	}

	private String name;
	private String creditCardNumber;
	private String limit;
	private String pound = "\u00a3";
	private String amount;

	public String getAmount() {
		return amount;
	}

	public void setAmount(double amountInpounds) {
		this.amount = preFixPoundSymbol(amountInpounds);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(double limitInPounds) {
		this.limit = preFixPoundSymbol(limitInPounds);
	}

	private String preFixPoundSymbol(double amountInpounds) {
		if(amountInpounds>=0) {
		return pound + Double.toString(amountInpounds);
		} else {
			return "-"+pound+Double.toString(Math.abs(amountInpounds));
		}
	}
}
