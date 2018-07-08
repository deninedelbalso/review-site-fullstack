package org.wecancodeit.reviewsitefullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks // enables mock objects to be placed in the controller
	private ReviewController underTest;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private TagRepository tagRepo;

	@Mock
	private Review review1;

	@Mock
	private Review review2;

	@Mock
	private Category category1;

	@Mock
	private Category category2;

	@Mock
	private Tag tag1;

	@Mock
	private Tag tag2;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long review1Id = 1;
		when(reviewRepo.findById(review1Id)).thenReturn(Optional.of(review1));

		underTest.find1Review(review1Id, model);
		verify(model).addAttribute("reviewsModel", review1);
	}

	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long category1Id = 1;
		when(categoryRepo.findById(category1Id)).thenReturn(Optional.of(category1));

		underTest.find1Category(category1Id, model);
		verify(model).addAttribute("categoriesModel", category1);// categoriesmodel liknks java to thymeleaf template
	}// view in the html

	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException {
		long tag1Id = 1;
		when(tagRepo.findById(tag1Id)).thenReturn(Optional.of(tag1));

		underTest.find1Tag(tag1Id, model);
		verify(model).addAttribute("tagsModel", tag1);
	}

	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review1, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviewsModel", allReviews);
	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category1, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		underTest.findAllCategories(model);
		verify(model).addAttribute("categoriesModel", allCategories);
	}

	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = Arrays.asList(tag1, tag2);
		when(tagRepo.findAll()).thenReturn(allTags);
		underTest.findAllTags(model);
		verify(model).addAttribute("tagsModel", allTags);
	}

}