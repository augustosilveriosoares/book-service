package com.augusto.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.model.Book;
import com.augusto.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
	
	 private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookService bookService;
	
	
	 @Operation(summary = "Busca por um livro especifico", description = "Busca um livro e realizar conversão de moeda")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
	@GetMapping(value="/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		
		log.info("method=findBook, step=starting, id={}", id);
		var book = bookService.getBookById(id,currency);
		var port = environment.getProperty("local.server.port") + "Feign";
		book.setEnvironment(book.getEnvironment() + "- Book Port" + port);
		log.info("method=findBook, step=finished, id={}, book={}", id, book);
		return book;
	}
	
	@Operation(summary = "Lista todos os livros")
	@GetMapping(value="/books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id){
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	

}
