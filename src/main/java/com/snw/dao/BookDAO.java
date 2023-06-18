package com.snw.dao;

import java.util.List;

import com.snw.entity.Book;

public interface BookDAO {

	public List<Book> get();

	public void save(Book theBook);

	public Book get(int theId);

	public List<Book> getByTitle(String title);

	public void delete(int theId);
	
}
