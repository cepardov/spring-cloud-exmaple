package com.globallogic.catalogo.util

import com.globallogic.catalogo.entity.Movie

class FakeData {

    static movie = Movie.builder()
            .id(1L)
            .uuid("uuid")
            .title("title")
            .description("desc")
            .urlImagePoster("http://")
            .urlVideotrailer("http://")
            .createdOn(java.time.LocalDate.now())
            .build()

    static movies = [movie]
}
