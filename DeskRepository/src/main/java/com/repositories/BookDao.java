package com.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.models.Book;

/**
 * 
 * @author RITESH SINGH
 */
public interface BookDao extends PagingAndSortingRepository<Book, String> {
	
}
