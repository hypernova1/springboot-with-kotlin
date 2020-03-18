package org.board.api.dto

import org.board.api.domain.Category
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

class CategoryDto {

    data class Request(
            @NotBlank
            var name: String = "",
            @NotBlank
            var path: String = "",
            @NotEmpty
            var orderNo: Int = -1
    )

    data class Response(
            @NotBlank
            var name: String = "",
            @NotBlank
            var path: String = "",
            @NotEmpty
            var orderNo: Int = -1
    )

    companion object {
        fun getResponse(category: Category): Response {
            val response = Response()
            response.name = category.name
            response.path = category.path
            response.orderNo = category.orderNo

            return response
        }
    }
}