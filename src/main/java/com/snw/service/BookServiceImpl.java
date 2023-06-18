package com.snw.service;

import java.util.List;

import com.snw.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snw.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	// need to inject book dao
	@Autowired
	private BookDAO bookDAO;
	
	@Override
	@Transactional
	public List<Book> getBooks() {
		return bookDAO.get();
	}

	@Override
	@Transactional
	public void saveBook(Book theBook) {

		bookDAO.save(theBook);
	}

	@Override
	@Transactional
	public Book getBook(int theId) {
		
		return bookDAO.get(theId);
	}

	@Override
	@Transactional
	public List<Book> findBooks(String title) {

		return bookDAO.getByTitle(title);
	}

	@Override
	@Transactional
	public void deleteBook(int theId) {
		
		bookDAO.delete(theId);
	}
}





