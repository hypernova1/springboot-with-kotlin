package org.board.api.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

class PostDto {

    class RegisterRequest {
        @NotEmpty
        var title: String = ""

        @NotEmpty
        val content: String = ""
    }

    class UpdateRequest {

        @NotEmpty
        var title: String = ""

        @NotEmpty
        val content: String = ""

    }

    class DetailResponse(
            var id: Long = 0,
            var title: String = "",
            var content: String = "",
            var writer: AccountDto.InfoResponse = AccountDto.InfoResponse(),
            var createdDate: LocalDateTime? = null,
            var updatedDate: LocalDateTime? = null,
            var commentList: List<CommentDto.Response>? = null
    )

    class Response(
            var id: Long = 0,
            var title: String = "",
            var writer: String = "",
            var createdDate: LocalDateTime? = null
    )

}