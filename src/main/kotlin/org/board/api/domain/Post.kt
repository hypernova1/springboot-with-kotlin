package org.board.api.domain

import org.board.api.domain.audit.DateAudit
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
        var writer: Account = Account()

) : DateAudit() {

        @OneToMany(mappedBy = "post", cascade = [CascadeType.REMOVE])
        val comments: MutableList<Comment> = mutableListOf()

        fun addComment(comment: Comment) {
                comment.post = this
                comments.add(comment)
        }

}