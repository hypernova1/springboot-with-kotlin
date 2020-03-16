package org.board.api.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Post(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var title: String = "",
        var content: String = "",
        @ManyToOne
        var writer: Account? = null
)