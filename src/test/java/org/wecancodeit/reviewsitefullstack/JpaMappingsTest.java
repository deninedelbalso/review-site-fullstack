package org.wecancodeit.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class JpaMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;

	@Test
	public void shouldSaveAndLoadCategory() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		long coffeeId = coffee.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(coffeeId); // we only use optional for findById
		coffee = result.get();
		// 3 different options of getting the test to pass. The last one is the easiest.
		assertThat(coffee.getBeverage(), is("coffee"));
		assertThat(result, is(Optional.of(coffee)));
		assertTrue(result.isPresent());
	}

	@Test
	public void shouldGenerateCategoryId() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		long coffeeId = coffee.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(coffeeId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadTag() {
		Tag hot = tagRepo.save(new Tag("hot"));
		long hotId = hot.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(hotId);
		hot = result.get();
		assertTrue(result.isPresent());
		assertThat(hot.getDescription(), is("hot"));
	}

	@Test
	public void shouldGenerateTagId() {
		Tag hot = tagRepo.save(new Tag("hot"));
		long hotId = hot.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(hotId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadAReview() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		Review dunkin = reviewRepo.save(new Review("dunkin", "description", "", coffee));
		long dunkinId = dunkin.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(dunkinId);
		assertTrue(result.isPresent());
		assertThat(dunkin.getDescription(), is("description"));
		assertThat(dunkin.getName(), is("dunkin"));
	}

	@Test
	public void shouldGenerateReviewId() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		Review dunkin = reviewRepo.save(new Review("dunkin", "description", "", coffee));
		long dunkinId = dunkin.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(dunkinId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishAReviewToCategoryRelationship() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		Review dunkin = reviewRepo.save(new Review("dunkin", "description", "", coffee));
		long dunkinId = dunkin.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(dunkinId);
		dunkin = result.get(); // this will generate hashcode and equals for entities
		assertThat(dunkin.getBeverage(), is(coffee));

	}

	@Test
	public void shouldEstablishACategoryToReviewRelationship() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		Review dunkin = reviewRepo.save(new Review("dunkin", "description", "", coffee));
		Review starbucks = reviewRepo.save(new Review("dunkin", "description", "", coffee));
		long coffeeId = coffee.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(coffeeId); // we only use optional for findById
		coffee = result.get();
		assertThat(coffee.getReviews(), containsInAnyOrder(dunkin, starbucks));
	}

	@Test
	public void shouldEstablishAReviewToTagRelationship() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		Tag hot = tagRepo.save(new Tag("hot"));
		Tag cold = tagRepo.save(new Tag("cold"));
		Review dunkin = reviewRepo.save(new Review("dunkin", "description", "", coffee, hot, cold));
		long dunkinId = dunkin.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(dunkinId);
		dunkin = result.get(); // this will generate hashcode and equals for entities
		assertThat(dunkin.getTags(), containsInAnyOrder(hot, cold));
	}
	
	@Test
	public void shouldEstablishATagToReviewRelationship() {
		Category coffee = categoryRepo.save(new Category("coffee"));
		Tag hot = tagRepo.save(new Tag("hot"));
		Tag cold = tagRepo.save(new Tag("cold"));
		Review dunkin = reviewRepo.save(new Review("dunkin", "description", "", coffee, hot, cold));
		Review starbucks = reviewRepo.save(new Review("dunkin", "description", "", coffee, hot, cold));
		long hotId = hot.getId();

		entityManager.flush();
		entityManager.clear();
			
		Optional<Tag> result = tagRepo.findById(hotId);
		hot = result.get();
		assertThat(hot.getReviews(), containsInAnyOrder(dunkin, starbucks));
	}
	
	@Test
	public void shouldHaveTwoCommentsOnOneReview() {
		Category coffee = new Category("Coffee");
		Tag hot = new Tag("Hot");
		coffee = categoryRepo.save(coffee);
		hot = tagRepo.save(hot);
		Review review = new Review("Test Review", "Stuff about review", "image url", coffee, hot);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		Comment testCommentOne = new Comment("Author", review, "Comment1");
		testCommentOne = commentRepo.save(testCommentOne);
		long testCommentOneId= testCommentOne.getId();

		Comment testCommentTwo = new Comment("Author2", review, "Comment2");
		testCommentTwo = commentRepo.save(testCommentTwo);
		long testCommentTwoId= testCommentTwo.getId();

		
		entityManager.flush();
		entityManager.clear();
		
		Iterable<Comment> comments = commentRepo.findAll();
		assertThat(comments, containsInAnyOrder(testCommentOne,testCommentTwo));
		Optional<Comment> testComment1Result = commentRepo.findById(testCommentOneId);
		testCommentOne = testComment1Result.get();
		Optional<Comment>testComment2Result= commentRepo.findById(testCommentTwoId);
		testCommentTwo = testComment2Result.get();
		Optional<Review>reviewResult= reviewRepo.findById(reviewId);
		review = reviewResult.get();
		
		assertThat(testCommentOne.getAuthor(), is("Author"));
		assertThat(testCommentTwo.getAuthor(), is("Author2"));
		assertThat(testCommentOne.getReview(), is(review));
		assertThat(testCommentTwo.getReview(), is(review));
		assertThat(review.getComments(), containsInAnyOrder("testCommentOne", "testCommentTwo"));

		



	}


}
