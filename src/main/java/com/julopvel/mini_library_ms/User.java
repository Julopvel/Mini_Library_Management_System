package com.julopvel.mini_library_ms;

import com.google.common.base.MoreObjects;

public record User(
        Integer id,
        String name,
        String email,
        String uniqueLibraryId
) {
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("uniqueLibraryId", uniqueLibraryId)
                .toString();
    }
}
