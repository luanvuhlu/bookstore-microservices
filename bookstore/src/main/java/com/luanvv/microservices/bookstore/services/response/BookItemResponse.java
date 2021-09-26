package com.luanvv.microservices.bookstore.services.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BookItemResponse {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int height;
    private String publisher;
}
