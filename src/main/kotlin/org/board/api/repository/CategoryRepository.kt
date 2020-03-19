package org.board.api.repository

import org.board.api.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByPath(categoryPath: String): Optional<Category>
}