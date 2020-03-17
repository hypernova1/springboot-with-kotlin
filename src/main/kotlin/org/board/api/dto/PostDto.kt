package org.board.api.dto

import org.board.api.domain.Account
import org.board.api.domain.Post
import java.time.LocalDateTime
import java.util.stream.Collectors
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

    companion object {
        fun getDetailResponse(post: Post): DetailResponse {

            val result = DetailResponse()

            result.id = post.id
            result.title = post.title
            result.content = post.content
            result.writer = Account.getDto(post.writer)
            result.createdDate = post.createdDate
            result.commentList = post.comments
                    .stream()
                    .map { p -> CommentDto.Response(p.id, p.content, p.writer.name, p.createdDate) }
                    .collect(Collectors.toList())

            return result
        }

        fun getResponse(post: Post): Response {

            val result = Response()

            result.id = post.id
            result.title = post.title
            result.writer = post.writer.name
            result.createdDate = post.createdDate

            return result
        }
    }

}