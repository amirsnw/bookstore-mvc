package com.snw.service;

import java.util.List;

import com.snw.entity.Book;

public interface BookService {

	public List<Book> getBooks();

	public void saveBook(Book theBook);

	public Book getBook(int theId);

	public List<Book> findBooks(String title);

	public void deleteBook(int theId);
	
}
