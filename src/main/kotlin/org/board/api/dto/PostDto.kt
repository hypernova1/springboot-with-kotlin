package org.board.api.dto

import javax.validation.constraints.NotEmpty

class PostDto {

    data class RegisterRequest (
        @NotEmpty
        var title: String = "",
        @NotEmpty
        val content: String = ""
    )

    data class UpdateRequest (
            @NotEmpty
            var title: String = "",
            @NotEmpty
            val content: String = ""
    )
}