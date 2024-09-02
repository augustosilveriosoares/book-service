package com.augusto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.model.Book;
import com.augusto.service.BookService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("book-service")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value="/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		
		var book = bookService.getBookById(id,currency);
		var port = environment.getProperty("local.server.port") + "Feign";
		book.setEnvironment(book.getEnvironment() + "- Book Port" + port);
		return book;
	}
	
	@GetMapping(value="/books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	

}
