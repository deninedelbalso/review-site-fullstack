package org.wecancodeit.reviewsitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	public String author;

	@ManyToOne
	public Review review;
	public String content;

	@Id
	@GeneratedValue
	public Long id;

	public String getAuthor() {
		return author;
	}

	public Review getReview() {
		return review;
	}

	public long getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}

	public Comment() {
	}

	public Comment(String author, Review review, String content) {

		this.author = author;
		this.review = review;
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
