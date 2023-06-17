package com.snw.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snw.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Book> get() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Book> theQuery =
				currentSession.createQuery("from Book order by title",
											Book.class);
		
		// execute query and get result list
		List<Book> books = theQuery.getResultList();
				
		// return the results		
		return books;
	}

	@Override
	public void save(Book theBook) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the book ... finally LOL
		currentSession.saveOrUpdate(theBook);
		
	}

	@Override
	public Book get(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Book theBook = currentSession.get(Book.class, theId);
		
		return theBook;
	}

	@Override
	public void delete(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Book where id=:bookId");
		theQuery.setParameter("bookId", theId);
		
		theQuery.executeUpdate();		
	}

}











