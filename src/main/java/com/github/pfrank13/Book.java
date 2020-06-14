package com.github.pfrank13;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import io.micronaut.data.annotation.MappedEntity;

/**
 * @author pfrank
 */
@MappedEntity
public class Book {
  @Id
  @NotBlank
  private String isbn;

  @NotBlank
  private String name;

  public Book(String isbn, String name) {
    this.isbn = isbn;
    this.name = name;
  }

  @NotBlank
  public String getIsbn() {
    return isbn;
  }

  @NotBlank
  public String getName() {
    return name;
  }
}
