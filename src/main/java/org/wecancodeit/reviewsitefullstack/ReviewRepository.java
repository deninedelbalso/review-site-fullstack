package org.wecancodeit.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review>findByBeverageContains(Category beverage);   //just using the getter .getBeverage to name findByBeverageContains
	//cant use findByCategoryContains because category isnt the getter

}
