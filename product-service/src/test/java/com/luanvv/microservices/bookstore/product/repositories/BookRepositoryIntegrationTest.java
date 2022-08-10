package com.luanvv.microservices.bookstore.product.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@TestPropertySource(properties = {
//    "spring.jpa.hibernate.ddl-auto=validate"
//})
class BookRepositoryIntegrationTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void testFindAll(){
    final var books = bookRepository.findAll();
    assertThat(books).hasSize(1);
  }
}