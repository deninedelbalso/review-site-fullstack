package org.wecancodeit.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



	@RunWith(SpringRunner.class)
	@WebMvcTest(ReviewController.class)
	public class ReviewControllerMockMVCTest {
		
		@Resource
		private MockMvc mvc;

		@MockBean
		private ReviewRepository reviewRepo;

		@MockBean
		private Review review1;

		@MockBean
		private TagRepository tagRepo;

		@MockBean
		private Tag tag1;

		@MockBean
		private CategoryRepository categoryRepo;

		@MockBean
		private Category category1;

		@Test
		public void shouldRouteToSingleReviewView() throws Exception {
			long arbitraryReviewId = 1;
			when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review1));
			mvc.perform(get("/review?id=1")).andExpect(view().name(is("reviewTemplate")));
		}

		@Test
		public void shouldRouteToSingleCategoryView() throws Exception {
			long arbitraryCategoryId = 1;
			when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category1));
			mvc.perform(get("/category?id=1")).andExpect(view().name(is("categoryTemplate")));
		}

		@Test
		public void shouldRouteToSingleTagView() throws Exception {
			long arbitraryTagId = 1;
			when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag1));
			mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tagTemplate")));
		}

		@Test
		public void shouldBeOkForSingleCategory() throws Exception {
			long arbitraryCategoryId = 1;
			when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category1));
			mvc.perform(get("/category?id=1")).andExpect(view().name(is("categoryTemplate")));
		}

		@Test
		public void shouldBeOkForSingleReview() throws Exception {
			long arbitraryReviewId = 1;
			when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review1));
			mvc.perform(get("/review?id=1")).andExpect(status().isOk());
		}

		@Test
		public void shouldBeOkForSingleTag() throws Exception {
			long arbitraryTagId = 1;
			when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag1));
			mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tagTemplate")));
		}

		@Test
		public void shouldRouteToAllCategoriesView() throws Exception {
			mvc.perform(get("/categories")).andExpect(view().name(is("categoriesTemplate")));
		}

		@Test
		public void shouldRouteToAllReviewsView() throws Exception {
			mvc.perform(get("/reviews")).andExpect(view().name(is("reviewsTemplate")));
		}

		@Test
		public void shouldRouteToAllTagsView() throws Exception {
			mvc.perform(get("/tags")).andExpect(view().name(is("tagsTemplate")));
		}

		@Test
		public void shouldBeOkForAllCategories() throws Exception {
			mvc.perform(get("/categories")).andExpect(status().isOk());
		}

		@Test
		public void shouldBeOkForAllReviews() throws Exception {
			mvc.perform(get("/reviews")).andExpect(status().isOk());
		}

		@Test
		public void shouldBeOkForAllTags() throws Exception {
			mvc.perform(get("/tags")).andExpect(status().isOk());
		}

	}

