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
		when(creditCardRepository.save(any(CreditCard.class))).thenReturn(cCard);
		String card = creditCardService.saveCreditCard(new CreditCard());
		assertNotNull("", card);
		assertEquals("", "CREDIT CARD HAS BEEN CREATED WITH NAME:Test name", card);
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
		cCard.setBalance(10.00);
		when(creditCardRepository.findByName(anyString())).thenReturn(null);
		String response = creditCardService.checkAndUpdateOutStanding(new ChargeAmountRequest("test name", 10));
	}

}
