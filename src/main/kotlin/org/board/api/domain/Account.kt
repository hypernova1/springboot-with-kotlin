package org.board.api.domain

import javax.persistence.*

@Entity
data class Account(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        var email: String = "",

        var name: String = "",

        @OneToMany
        var posts: MutableList<Post>? = null
) {

        fun addPost(post: Post) {
                post.writer = this
                posts?.add(post)
        }

}
