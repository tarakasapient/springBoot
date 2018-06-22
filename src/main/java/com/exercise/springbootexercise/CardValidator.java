package com.exercise.springbootexercise;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CardValidator {
	/**
	 * this method resposible for credit card number validations
	 * - should contain only numerics
	 * - length up to 19 characters
	 * - Luhn validation for credit card numbers
	 * 
	 * @param cardNo
	 * @return
	 */
	static boolean checkLuhn(String cardNo)
	{
		String regex = "\\d+";
		if(!StringUtils.isEmpty(cardNo) && cardNo.matches(regex) && cardNo.length()<=19) {
			
			
			int[] ints = new int[cardNo.length()];
			for (int i = 0; i < cardNo.length(); i++) {
				ints[i] = Integer.parseInt(cardNo.substring(i, i + 1));
			}
			for (int i = ints.length - 2; i >= 0; i = i - 2) {
				int j = ints[i];
				j = j * 2;
				if (j > 9) {
					j = j % 10 + 1;
				}
				ints[i] = j;
			}
			int sum = 0;
			for (int i = 0; i < ints.length; i++) {
				sum += ints[i];
			}
			if (sum % 10 == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
