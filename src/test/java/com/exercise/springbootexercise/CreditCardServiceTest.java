package com.exercise.springbootexercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
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
		when(creditCardRepository.save(any(CreditCard.class))).thenReturn(cCard);
		String card = creditCardService.saveCreditCard(new CreditCard());
		assertNotNull("", card);
		assertEquals("", "credit card has been created with ID01234", card);
	}

}
