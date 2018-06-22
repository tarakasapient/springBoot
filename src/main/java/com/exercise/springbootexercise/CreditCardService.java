package com.exercise.springbootexercise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service component which executes business logic and sets the response object
 * accordingly, throws custom exceptions if there are any exceptions during
 * execution * @author tartirum
 *
 */
@Service
public class CreditCardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	private static String POUND = "\u00a3";

	/**
	 * this method will create an entry to the database, new card starts with zero
	 * outstanding amount.
	 * 
	 * @param creditCard
	 * @return
	 */
	public CreditCardAPIResponse saveCreditCard(CreditCard creditCard) {
		CreditCard savedCard = creditCardRepository.save(creditCard);
		return setResponseObject(savedCard);
	}

	/**
	 * this method responsible for getting all the avaliable credit cards from the
	 * database and populates to response object.
	 * 
	 * @return
	 */
	public List<CreditCardAPIResponse> getAllCreditCards() {

		List<CreditCard> creditCards = creditCardRepository.findAll();
		List<CreditCardAPIResponse> listofResponse = new ArrayList<CreditCardAPIResponse>();
		for (CreditCard creditCard : creditCards) {
			listofResponse.add(setResponseObject(creditCard));
		}
		return listofResponse;
	}

	/**
	 * this method responsible for increasing out standing amount. checks if total
	 * outstanding should be with in the credit card limit amount.
	 * 
	 * 
	 * @param chargeAmountRequest
	 * @return
	 */
	public Object checkAndUpdateOutStanding(ChargeAmountRequest chargeAmountRequest) {
		CreditCard cr = creditCardRepository.findByName(chargeAmountRequest.getName());
		if (null != cr) {
			long totalOutstanding = (long) (cr.getBalance() + chargeAmountRequest.getAmount());
			if (totalOutstanding < cr.getLimit()) {
				cr.setBalance(totalOutstanding);
			} else {
				throw new LimitReachedExcpetion("CREDIT LIMIT REACHED " + "AND CURRENT OUTSTANDING AMOUNT IS " + POUND
						+ cr.getBalance() + " AND CREDIT LIMIT WAS SET TO " + POUND + cr.getLimit());
			}
			CreditCard updatedCard = creditCardRepository.save(cr);
			return setResponseObject(updatedCard);
		} else {
			throw new NoCardsFoundException("No Credit cards found for the given Name" + chargeAmountRequest.getName());
		}
	}

	/**
	 * this method resposible for deducting outstanding amount by deducting the
	 * current out standing amount.
	 * 
	 * @param chargeAmountRequest
	 * @return
	 */
	public Object deductAndUpdateBalance(ChargeAmountRequest chargeAmountRequest) {

		CreditCard cr = creditCardRepository.findByName(chargeAmountRequest.getName());

		if (null != cr) {
			long updatedBalance = (long) (cr.getBalance() - chargeAmountRequest.getAmount());
			cr.setBalance(updatedBalance);
			CreditCard updatedCard = creditCardRepository.save(cr);
			return setResponseObject(updatedCard);
		} else {
			throw new NoCardsFoundException("No Credit cards found for the given Name" + chargeAmountRequest.getName());
		}
	}

	/**
	 * this method responsible for setting response object from dto object.
	 * 
	 * @param cr
	 * @return
	 */
	private CreditCardAPIResponse setResponseObject(CreditCard cr) {
		CreditCardAPIResponse response = new CreditCardAPIResponse();
		response.setLimit(cr.getLimit());
		response.setName(cr.getName());
		response.setAmount(cr.getBalance());
		response.setCreditCardNumber(cr.getCardNumber());
		return response;
	}

}
