package org.wecancodeit.reviewsitefullstack;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("series")

public class ReviewNotFoundException extends Exception {

}
