package com.augusto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.augusto.model.Book;

public interface BookRepository extends JpaRepository<Book ,Long> {

}
