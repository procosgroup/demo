package com.procosgroup.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class QueryUtils {
	
	private QueryUtils() {
		// static methods only
	}	
	
	public static Pageable getPageableFrom(Integer start, Integer end, String sort, String order) {
		if (start != null && end != null) {
			int size = end - start;
			return PageRequest.of(start / size, size, Sort.by(Direction.fromString(order), sort));
		} else {
			return Pageable.unpaged();
		}
	}

}
