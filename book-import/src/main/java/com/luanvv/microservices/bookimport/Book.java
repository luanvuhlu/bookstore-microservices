package com.luanvv.microservices.bookimport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
	
	private String title;
	private String author;
	private String genre;
	private int height;
	private String publisher;
}
