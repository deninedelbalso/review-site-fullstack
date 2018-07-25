package org.wecancodeit.reviewsitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


	@Component
	public class ReviewPopulator implements CommandLineRunner {

		@Resource
		private ReviewRepository reviewRepo;

		@Resource
		private CategoryRepository categoryRepo;

		@Resource
		private TagRepository tagRepo;

		@Override
		public void run(String... args) throws Exception {

			Tag hot = new Tag("Hot");
			hot = tagRepo.save(hot);
			Tag cold = new Tag("Cold");
			cold = tagRepo.save(cold);
			Tag nonAlcoholic = new Tag("Non Alcoholic");
			nonAlcoholic = tagRepo.save(nonAlcoholic);
			Tag alcoholic = new Tag("Alcoholic");
			alcoholic = tagRepo.save(alcoholic);

			Category coffee = new Category("Coffee");
			coffee = categoryRepo.save(coffee);
			Category beer = new Category("Beer");
			beer = categoryRepo.save(beer);
			Category tea = new Category("Tea");
			tea = categoryRepo.save(tea);
			Category wine = new Category("Wine");
			tea = categoryRepo.save(wine);


			 reviewRepo.save(new Review("Dunkin Donuts", "Best darned coffee ever", "/images/dunkin.jpg", coffee, hot, cold, nonAlcoholic));
	    	 reviewRepo.save(new Review("Starbucks", "Ew. I'll pass.", "/images/starbucks.jpg", coffee, hot, cold, nonAlcoholic)); 
	    	 reviewRepo.save(new Review("Budweiser", "Classic and refreshing", "/images/budlight.jpg", beer, cold, alcoholic)); 
	    	 reviewRepo.save(new Review("Miller Lite", "More classic and refreshing than Bud Light", "/images/miller.jpg", beer, cold, alcoholic));
	    	 reviewRepo.save(new Review("Tazo Green Tea", "Better than water!", "/images/tazo.jpg", tea, hot, cold, nonAlcoholic)); 
	    	 reviewRepo.save(new Review("Lipton", "Sweet goodness!", "/images/lipton.jpg", tea, hot, cold, nonAlcoholic));  
	    	 reviewRepo.save(new Review("Cabernet", "Perfect paired with a steak or desert", "/images/red.jpg", wine, cold, alcoholic)); 
	    	 reviewRepo.save(new Review("Wine", "Im okay spilling this on me!", "/images/white.jpg", wine, cold, alcoholic)); 

		}
	

	}

