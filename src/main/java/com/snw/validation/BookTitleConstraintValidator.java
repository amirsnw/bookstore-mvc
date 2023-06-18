package com.snw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookTitleConstraintValidator
	implements ConstraintValidator<BookTitle, String> {

	private String titlePattern;
	
	@Override
	public void initialize(BookTitle bookTitle) {
		titlePattern = bookTitle.pattern();
	}

	@Override
	public boolean isValid(String title,
						ConstraintValidatorContext theConstraintValidatorContext) {

		return title.matches(titlePattern);
	}
}








