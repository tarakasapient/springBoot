package com.exercise.springbootexercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreditCardServiceTest {
	
	@InjectMocks
	CreditCardService creditCardService;

	@Mock
	private CreditCardRepository creditCardRepository;
	
	@Before
    public void setUp() throws MockitoException {
        MockitoAnnotations.initMocks(this);
    }
 
	@Test
	public void testSaveNewCard() {
		CreditCard cCard = new CreditCard();
		cCard.setId("01234");
		cCard.setName("Test name");
		cCard.setCardNumber("4386280524603440");

		when(creditCardRepository.save(any(CreditCard.class))).thenReturn(cCard);
		CreditCardAPIResponse card = creditCardService.saveCreditCard(new CreditCard());
		assertNotNull("", card);
		assertEquals("", "Test name", card.getName());
		assertEquals("", "4386280524603440", card.getCreditCardNumber());
		assertEquals("", "£0.0", card.getAmount());

	}
	

	
	@Test(expected= NoCardsFoundException.class)
	public void testOutStaindingforNullCard() {
		when(creditCardRepository.findByName(anyString())).thenReturn(null);
		creditCardService.checkAndUpdateOutStanding(new ChargeAmountRequest());
		
	}
	
	@Test
	public void testUpdatingOutStandingAmount() {
		CreditCard cCard = new CreditCard();
		cCard.setName("Test name");
		cCard.setLimit(100.00);
		cCard.setBalance(10.00);
		cCard.setCardNumber("4386280524603440");
		when(creditCardRepository.findByName(anyString())).thenReturn(cCard);
		when(creditCardRepository.save(any(CreditCard.class))).thenReturn(cCard);
		CreditCardAPIResponse response = (CreditCardAPIResponse) creditCardService.checkAndUpdateOutStanding(new ChargeAmountRequest("Test name", 10));
		assertNotNull("", response);
		assertEquals("", "Test name", response.getName());
		assertEquals("", "4386280524603440", response.getCreditCardNumber());
		assertEquals("", "£20.0", response.getAmount());
	}
	
	
	@Test
	public void testDeductAndUpdateBalanceWhenCardisNull() {
		CreditCard cCard = new CreditCard();
		cCard.setName("Test name");
		cCard.setLimit(100.00);
		cCard.setBalance(10.00);
		cCard.setCardNumber("4386280524603440");
		when(creditCardRepository.findByName(anyString())).thenReturn(cCard);
		when(creditCardRepository.save(any(CreditCard.class))).thenReturn(cCard);
		CreditCardAPIResponse response = (CreditCardAPIResponse) creditCardService.deductAndUpdateBalance(new ChargeAmountRequest("Test name", 10));
		assertNotNull("", response);
		assertEquals("", "Test name", response.getName());
		assertEquals("", "4386280524603440", response.getCreditCardNumber());
		assertEquals("", "£0.0", response.getAmount());
	}

}
