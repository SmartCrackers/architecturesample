package com.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.HelperUtility;
import com.Response;
import com.google.gson.Gson;
import com.models.Book;
import com.models.User;
import com.services.BookService;

/**
 * @author RITESH SINGH
 * @since 2017-06-08
 * @version	1.8
 */
@Controller("bookController")
@ComponentScan("com.services")
@RequestMapping(value = "book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	@Qualifier("bookService")
	private BookService bookService;
	
	/**
	 * 
	 * @param bookString : json String Type , param Type RequestParam
	 * @param logo : MultipartFile Type , param Type RequestParam
	 * @param image : MultipartFile Type , param Type RequestParam
	 * @param accessToken
	 * @param response
	 * @return Object
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Response<Book>> saveBook(
			@RequestParam(value = "bookString")  String bookString,
			@RequestParam(value = "logo", required = false) MultipartFile logo,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		Book book = new Gson().fromJson(bookString, Book.class);
		
		return new ResponseEntity<Response<Book>>(new Response<Book>(
                HttpStatus.OK.value(), "Book saved successfully.",bookService.save(book,logo,image)), HttpStatus.OK);
	}
	
	/**
	 * <b>Update the Book.</b> 
	 * <h3>Request Method PUT</h3>
	 *
	 * @param bookString : json String Type , param Type RequestParam
	 * @param logo : MultipartFile Type , param Type RequestParam
	 * @param image : MultipartFile Type , param Type RequestParam
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Response<Book>> updateBook(
			@RequestParam(value = "bookString")  String bookString,
			@RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		Book book = new Gson().fromJson(bookString, Book.class);
		
		
		return new ResponseEntity<Response<Book>>(new Response<Book>(
                HttpStatus.OK.value(), "Book updates successfully.",bookService.update(book,coverImage,image)), HttpStatus.OK);
	}
}