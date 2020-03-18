package org.board.api.service

import org.board.api.domain.Category
import org.board.api.dto.CategoryDto
import org.board.api.exception.category.CategoryNotFoundException
import org.board.api.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService(@Autowired val categoryRepository: CategoryRepository) {

    fun save(request: CategoryDto.Request): Category {
        val category = Category.setInstance(request)

        return categoryRepository.save(category)
    }

    fun update(id: Long, request: CategoryDto.Request) {
        val category = categoryRepository.findById(id)
                .orElseThrow { CategoryNotFoundException(id) }
        category.update(request)
    }

    fun findById(id: Long): CategoryDto.Response {
        val category = categoryRepository.findById(id)
                .orElseThrow { CategoryNotFoundException(id) }

        return CategoryDto.getResponse(category)
    }

}