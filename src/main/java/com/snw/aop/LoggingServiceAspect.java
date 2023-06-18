package com.snw.aop;

import java.util.List;
import java.util.logging.Logger;

import com.snw.entity.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(3)
public class LoggingServiceAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.snw.service.*.find*(..))")
	public Object aroundSearch(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			logger.warning(e.getMessage());

			// rethrow exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		logger.info("\n=====> Search Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}

	@After("execution(* com.snw.service.*.delete*(..))")
	public void afterFinallyDeleteAdvice(JoinPoint theJoinPoint) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @After (finally) on method: "
							+ method);
	}
	
	@AfterThrowing(
			pointcut="execution(* com.snw.service.*.get*(..))",
			throwing="theExc")
	public void afterThrowingGetByIdAdvice(
					JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @AfterThrowing on method: " + method);
		
		// log the exception
		logger.info("\n=====>>> The exception is: " + theExc);
	
	}
	
	@AfterReturning(
			pointcut="execution(* com.snw.service.*.getBooks())",
			returning="result")
	public void afterReturningGetAllAdvice(
					JoinPoint theJoinPoint, List<Book> result) {

		String method = theJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @AfterReturning on method: " + method);
				
		// print out the results of the method call
		logger.info("\n=====>>> result is: " + result);
		
		// let's post-process the data
		
		// convert the account names to uppercase
		convertBookTitlesToUpperCase(result);

		logger.info("\n=====>>> new result is: " + result);
		
	}

	@Before("execution(* com.snw.service.*.save*(..))")
	public void beforeSaveAdvice(JoinPoint theJoinPoint) {

		logger.info("\n=====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		logger.info("Method: " + methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();

		for (Object tempArg : args) {
			logger.info(tempArg.toString());
			
			if (tempArg instanceof Book) {
				
				// downcast and print Account specific stuff
				Book theBook = (Book) tempArg;

				logger.info("account title: " + theBook.getTitle());
				logger.info("account author: " + theBook.getAuthor());
				logger.info("account price: " + theBook.getPrice());

			}
		}
		
	}

	private void convertBookTitlesToUpperCase(List<Book> result) {

		// loop through accounts

		for (Book tempBook : result) {

			// get uppercase version of name
			String theUpperName = tempBook.getTitle().toUpperCase();

			// update the name on the account
			tempBook.setTitle(theUpperName);
		}

	}
}










