package org.board.api.dto

import org.board.api.domain.Account
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class CommentDto {

    class Request {
        @NotBlank
        var postId: Long = -1
        @NotBlank
        var content: String = ""
    }

    class Response(
            var id: Long = 0,
            var content: String = "",
            var writer: Account = Account(),
            var createdDate: LocalDateTime? = null
    )
}