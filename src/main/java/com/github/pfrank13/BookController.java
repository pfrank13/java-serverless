package com.github.pfrank13;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Controller
public class BookController {
    private final BookRepository bookRepository;

    @Inject
    public BookController(@NonNull final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Post("/books")
    public BookDtoResponse save(@Valid @Body BookDtoRequest bookDtoRequest) {
        final Book book = new Book(UUID.randomUUID().toString(), bookDtoRequest.getName());
        final Book persisted = bookRepository.save(book);
        return new BookDtoResponse(persisted.getName(), book.getIsbn());
    }

    @Get("/book/{isbn}")
    public BookDtoResponse find(@NotBlank @PathVariable final String isbn){
        return bookRepository.findOne(isbn);
    }
}
