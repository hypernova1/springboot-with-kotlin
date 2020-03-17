package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import org.board.api.dto.CommentDto
import org.board.api.dto.PostDto
import java.util.stream.Collectors
import javax.persistence.*

@Entity
class Post(

        @Id
        @GeneratedValue
        val id: Long = -1,

        var title: String = "",

        @Lob
        var content: String = "",

        @ManyToOne(cascade = [CascadeType.ALL])
        var writer: Account = Account(),

        @OneToMany
        private val _comments: MutableList<Comment> = mutableListOf()

) : DateAudit() {

        val comments get() = _comments.toList()

        fun addComment(comment: Comment) {
                comment.post = this
                _comments.add(comment)
        }

}