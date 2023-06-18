package com.snw.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = BookTitleConstraintValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface BookTitle {

	/*This regular expression pattern matches a book title that consists of the following characters:
	*	Unicode letters, including non-English letters (\p{L})
	*	Digits (0-9)
	*	Ampersand (&)
	*	Single quote and apostrophe (', ’)
	*	Common punctuation marks, such as comma, period, colon, semicolon, exclamation mark, and question mark (,.:;?!)
	*	Parentheses, square brackets, and hyphen (-) for grouping and punctuation ((), [])
	*	Whitespace characters, such as space, tab, and newline (\s)
	*/
	public String pattern() default "^[\\p{L}0-9&'’,.:;?!()\\[\\]\\-\\s]+$";
	
	// define default error message
	public String message() default "Must have valid characters";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}












