package org.board.api.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class CommentDto {

    class Request {
        @NotBlank
        var content: String = ""
    }

    class Response(
            var id: Long = 0,
            var content: String = "",
            var writer: String? = null,
            var createdDate: LocalDateTime? = null
    )
}