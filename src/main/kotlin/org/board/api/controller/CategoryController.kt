package org.board.api.controller

import org.board.api.dto.ApiResponse
import org.board.api.dto.CategoryDto
import org.board.api.dto.constant.ResultCode
import org.board.api.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/category")
class CategoryController(@Autowired val categoryService: CategoryService) {

    @PostMapping
    fun registerCategory(@RequestBody request: CategoryDto.Request): ResponseEntity<ApiResponse<Any>> {

        val category = categoryService.save(request)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id)
                .toUri()

        val response = ApiResponse<Any>(ResultCode.CATEGORY_REGISTER_SUCCESS)

        return ResponseEntity.created(location).body(response)
    }

    @PutMapping("/{id}")
    fun modifyCategory(
            @PathVariable id: Long,
            @RequestBody request: CategoryDto.Request): ResponseEntity<ApiResponse<CategoryDto.Response>> {

        categoryService.update(id, request)
        val category = categoryService.findById(id)

        val response = ApiResponse<CategoryDto.Response>(ResultCode.CATEGORY_UPDATE_SUCCESS)
        response.data = category

        return ResponseEntity.ok(response)
    }

}