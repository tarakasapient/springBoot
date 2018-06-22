package com.exercise.springbootexercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Rest Controller responsible for CreditCard API
 * @author tartirum
 *
 */
@RequestMapping("/creditCard")
@RestController
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;

	/**
	 * This method gets called for Post method which posts the request data to
	 * service class for executing business logic and returns response object if
	 * there are no errors. throws custom exceptions if any.
	 * 
	 * @param saveCreditCardRequest
	 * @return
	 */
	@PostMapping("/add")
	public Object addCreditCard(@RequestBody SaveCreditCardRequest saveCreditCardRequest) {

		if (CardValidator.checkLuhn(saveCreditCardRequest.getCardNumber())) {
			CreditCard creditCard = new CreditCard();
			creditCard.setBalance(0.0);
			creditCard.setCardNumber(saveCreditCardRequest.getCardNumber());
			creditCard.setLimit(saveCreditCardRequest.getLimit());
			creditCard.setName(saveCreditCardRequest.getName());
			return creditCardService.saveCreditCard(creditCard);
		} else {
			throw new InvalidCardException("CREDIT CARD ENTERED IS INVALID");
		}
	}

	@GetMapping("/getAll")
	public List<CreditCardAPIResponse> getAllCreditCards() {
		return creditCardService.getAllCreditCards();
	}

	@PostMapping("/charge")
	public Object spending(@RequestBody ChargeAmountRequest chargeAmountRequest) {
		return creditCardService.checkAndUpdateOutStanding(chargeAmountRequest);
	}

	@PostMapping("/credit")
	public Object recharging(@RequestBody ChargeAmountRequest chargeAmountRequest) {
		return creditCardService.deductAndUpdateBalance(chargeAmountRequest);
	}

}
