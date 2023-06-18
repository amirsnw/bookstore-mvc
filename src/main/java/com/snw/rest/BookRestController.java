package com.snw.rest;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.snw.entity.Book;
import com.snw.exception.BookNotFoundException;
import com.snw.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books/v2")
public class BookRestController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getBooks() {
		
		return bookService.getBooks();
	}
	
	@GetMapping("/{bookId}")
	public Book getBook(@PathVariable int bookId) {
		
		Book theBook = bookService.getBook(bookId);
		
		if (theBook == null) {
			throw new BookNotFoundException("Book id not found - " + bookId);
		}
		
		return theBook;
	}
	
	@PostMapping
	public Book addBook(@RequestBody Book theBook) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		
		theBook.setId(0);
		
		bookService.saveBook(theBook);
		
		return theBook;
	}
	
	@PutMapping
	public Book updateBook(@RequestBody Book theBook) {
		
		bookService.saveBook(theBook);
		
		return theBook;
		
	}
	
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		
		Book tempBook = bookService.getBook(bookId);
		
		// throw exception if null
		
		if (tempBook == null) {
			throw new BookNotFoundException("Book id not found - " + bookId);
		}
				
		bookService.deleteBook(bookId);
		
		return "Deleted book id - " + bookId;
	}
}