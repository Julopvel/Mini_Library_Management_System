package com.julopvel.mini_library_ms;

import com.google.common.base.MoreObjects;

public record Book(
        Integer id,
        String title,
        String author,
        String isbn,
        Boolean availabilityStatus
) {
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("author", author)
                .add("isbn", isbn)
                .add("availabilityStatus", availabilityStatus)
                .toString();
    }
}
