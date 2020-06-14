package com.github.pfrank13;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

/**
 * This is a DTO that Micronaut Data will project into via the Book object. If the properties match it will "just work"
 */
@Introspected
public class BookDtoResponse {

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private String isbn;

    public BookDtoResponse(@NonNull @NotBlank final String name,
                           @NonNull @NotBlank final String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NonNull String isbn) {
        this.isbn = isbn;
    }
}
