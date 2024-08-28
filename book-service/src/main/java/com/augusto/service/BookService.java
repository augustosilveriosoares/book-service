package com.augusto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augusto.exception.BookNotFoundException;
import com.augusto.model.Book;
import com.augusto.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());				
	}

}
