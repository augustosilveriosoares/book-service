package com.augusto.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.augusto.exception.BookNotFoundException;
import com.augusto.model.Book;
import com.augusto.repository.BookRepository;
import com.augusto.response.Cambio;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book getBookById(Long id, String currency) {
		var book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
		
		HashMap<String,String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
		var cambio = response.getBody();
		book.setPrice(cambio.getConvertedValue());	
		
		return book;			
	}

}
