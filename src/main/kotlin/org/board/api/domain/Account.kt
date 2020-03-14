package org.board.api.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Account(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var name: String = "",
        @OneToMany
        var posts: MutableList<Post>? = null
)
