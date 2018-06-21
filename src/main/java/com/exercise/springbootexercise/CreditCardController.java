package com.exercise.springbootexercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/creditCard")
@RestController
public class CreditCardController {
	
	
	@Autowired
	private CreditCardService creditCardService;
	
	
	@PostMapping("/add")
	public String addCreditCard(@RequestBody SaveCreditCardRequest saveCreditCardRequest ) {
		
	if(CardValidator.checkLuhn(saveCreditCardRequest.getCardNumber())) {
			CreditCard creditCard = new CreditCard();
			creditCard.setBalance(0);
			creditCard.setCardNumber(saveCreditCardRequest.getCardNumber());
			creditCard.setLimit(saveCreditCardRequest.getLimit());
			creditCard.setName(saveCreditCardRequest.getName());
			return creditCardService.saveCreditCard(creditCard);
		} else {
			throw new InvalidCardException("CREDIT CARD ENTERED IS INVALID");
		}
	}
	
	@GetMapping("/getAll")
	public List<CreditCard> getAllCreditCards() {
	 return	creditCardService.getAllCreditCards();
	}
	
	@PostMapping("/charge")
	public String increaseBalance(@RequestBody ChargeAmountRequest chargeAmountRequest ) {
		return creditCardService.checkAndUpdateBalance(chargeAmountRequest);
	}
	
	@PostMapping("/credit")
	public String decreaseBalance(@RequestBody ChargeAmountRequest chargeAmountRequest ) {
		return creditCardService.deductAndUpdateBalance(chargeAmountRequest);
	}	

}
