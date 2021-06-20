package com.luanvv.microservices.bookstore.service.upload;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CsvBindByPosition(position = 0)
	private String title;
	
	@CsvBindByPosition(position = 1)
	private String author;
	
	@CsvBindByPosition(position = 2)
	private String genre;
	
	@CsvBindByPosition(position = 3)
	private int height;
	
	@CsvBindByPosition(position = 4)
	private String publisher;
}
