package com.github.pfrank13;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

/**
 * @author pfrank
 */

@JdbcRepository(dialect = Dialect.MYSQL)
public abstract class BookRepository implements CrudRepository<Book, String> {
  public abstract BookDtoResponse findOne(final String isbn);
}
