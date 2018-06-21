package com.exercise.springbootexercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
	
	
	private String pound = "\u00a3";

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * 
	 * @param creditCard
	 * @return
	 */
	public String saveCreditCard(CreditCard creditCard) {
		CreditCard cr = creditCardRepository.save(creditCard);
		return "CREDIT CARD HAS BEEN CREATED WITH NAME:" + cr.getName();
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
	public String checkAndUpdateOutStanding(ChargeAmountRequest chargeAmountRequest) {
		CreditCard cr = creditCardRepository.findByName(chargeAmountRequest.getName());
		if (null != cr) {
			long totalOutstanding = (long) (cr.getBalance() + chargeAmountRequest.getAmount());
			if (totalOutstanding < cr.getLimit()) {
				cr.setBalance(totalOutstanding);
			} else {
				throw new LimitReachedExcpetion("CREDIT LIMIT REACHED "+"CURRENT OUTSTANDING AMOUNT "+pound+cr.getBalance() + " CREDIT LIMIT "+pound+cr.getLimit());
			}
		CreditCard updatedCard = creditCardRepository.save(cr);
			return "SUCCESS: UPDATED OUTSTANDING " +pound+updatedCard.getBalance();
		} else {
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
				CreditCard updatedCard = creditCardRepository.save(cr);
			return "BALANCE HAS BEEN UPDATED " +pound+updatedCard.getBalance();
		} else {
			throw new NoCardsFoundException("No Credit cards found for the given Name" + chargeAmountRequest.getName());
		}
	}

}
