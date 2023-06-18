package com.snw.rest;

import javax.servlet.http.HttpServletRequest;

import com.snw.entity.Book;
import com.snw.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books/v1")
public class BasicBookRestController {

	@Autowired
	private BookService bookService;

	// new a controller method to read book data and
	// add it to the model
	@RequestMapping("/find-book")
	public String searchBook(HttpServletRequest request,
								@RequestParam("title") String title,
								Model model) {

		List<Book> books = bookService.findBooks(title);

		// read the request parameter from the HTML form
		// String theName = request.getParameter("title");
		
		// create the message
		String result = "Search result for '" + title + "'";
		
		// add message and books to the model
		model.addAttribute("message", result);
		model.addAttribute("books", books);

		return "view-book";
	}
}






