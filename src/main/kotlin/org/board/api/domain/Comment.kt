package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import javax.persistence.*

@Entity
data class Comment(

        @Lob
        var content: String = "",

        @ManyToOne
        var writer: Account = Account(),

        @ManyToOne
        var post: Post = Post()

) : DateAudit()
