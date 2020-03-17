package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import javax.persistence.*

@Entity
data class Comment(

        @Id
        @GeneratedValue
        val id: Long = -1,

        @Lob
        var content: String = "",

        @ManyToOne
        var writer: Account = Account(),

        @ManyToOne
        var post: Post = Post()

) : DateAudit()
