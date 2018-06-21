package com.exercise.springbootexercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * 
	 * @param creditCard
	 * @return
	 */
	public String saveCreditCard(CreditCard creditCard) {
		CreditCard cr = creditCardRepository.save(creditCard);
		return "credit card has been created with ID" + cr.getId();
	}

	/**
	 * 
	 * @return
	 */
	public List<CreditCard> getAllCreditCards() {
		return creditCardRepository.findAll();
	}

	/**
	 * 
	 * @param chargeAmountRequest
	 * @return
	 */
	public String checkAndUpdateBalance(ChargeAmountRequest chargeAmountRequest) {
		CreditCard cr = creditCardRepository.findByName(chargeAmountRequest.getName());
		if (null != cr) {
			long totalBalance = (long) (cr.getBalance() + chargeAmountRequest.getAmount());
			if (totalBalance < cr.getLimit()) {
				cr.setBalance(totalBalance);
			} else {
				// Throw Limit reached exception
				throw new LimitReachedExcpetion("limit reached");
			}
		CreditCard card = creditCardRepository.save(cr);
			return "success with balance" + card.getBalance();
		} else {
			// Throw no card exception
			throw new NoCardsFoundException("No Credit cards found for the given Name" + chargeAmountRequest.getName());
		}

	}
	/**
	 * 
	 * @param chargeAmountRequest
	 * @return
	 */
	public String deductAndUpdateBalance(ChargeAmountRequest chargeAmountRequest) {
		
		CreditCard cr = creditCardRepository.findByName(chargeAmountRequest.getName());

		if (null != cr) {
			long updatedBalance = (long) (cr.getBalance() - chargeAmountRequest.getAmount());
				cr.setBalance(updatedBalance);
				CreditCard card = creditCardRepository.save(cr);
			return "success with balance" + card.getBalance();
		} else {
			// Throw no card exception
			throw new NoCardsFoundException("No Credit cards found for the given Name" + chargeAmountRequest.getName());
		}
	
	}

}
