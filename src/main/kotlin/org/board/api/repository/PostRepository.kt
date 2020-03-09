package org.board.api.repository

import org.board.api.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<Post, Long> {
    fun findByTitle(title: String): Optional<Post>
}

