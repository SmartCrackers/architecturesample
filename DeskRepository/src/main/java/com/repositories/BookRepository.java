package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.Book;

/**
 * @author RITESH SINGH
 *
 */
public interface BookRepository extends MongoRepository<Book, String>  {

}
