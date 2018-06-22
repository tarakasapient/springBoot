package com.exercise.springbootexercise;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Respository which extends Mondgo Repository
 * @author tartirum
 *
 */

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
	/**
	 * method for querying a particular field
	 * @param name
	 * @return
	 */
	CreditCard findByName( String name);
	

}
