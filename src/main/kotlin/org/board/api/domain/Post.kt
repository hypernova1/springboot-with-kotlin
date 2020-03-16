package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import org.board.api.dto.CommentDto
import org.board.api.dto.PostDto
import java.util.stream.Collectors
import javax.persistence.*

@Entity
class Post : DateAudit() {

        @Id
        @GeneratedValue
        val id: Long = -1
        var title: String = ""
        @Lob
        var content: String = ""
        @ManyToOne(cascade = [CascadeType.ALL])
        var writer: Account = Account()

        @OneToMany
        private val _comments = mutableListOf<Comment>()

        val comments get() = _comments.toList()

        fun addComment(comment: Comment) {
                comment.post = this
                _comments.add(comment)
        }


        companion object {
                fun getDetailDto(post: Post): PostDto.DetailResponse {

                        val result = PostDto.DetailResponse()

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

                fun getDto(post: Post): PostDto.Response {

                        val result = PostDto.Response()

                        result.id = post.id
                        result.title = post.title
                        result.writer = post.writer.name
                        result.createdDate = post.createdDate

                        return result
                }
        }

}