package com.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import com.models.Book;

/**
 *  @author RITESH SINGH 
 */
public interface BookService {

	public Book save(Book book, MultipartFile logo, MultipartFile image);
	public Book update(Book book, MultipartFile logo, MultipartFile image);
	public Long count();
	public List<Book> getBooks();
	public List<Book> getBooks(Sort sort);
	public Page<Book> getBooks(Pageable pageable);
	public Book getBookById(String bookId);
	
}
