package com.exercise.springbootexercise;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {

	CreditCard findByName( String name);
	

}
