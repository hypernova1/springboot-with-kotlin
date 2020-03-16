package org.board.api.domain

import org.board.api.dto.AccountDto
import javax.persistence.*

@Entity
data class Account(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        var email: String = "",

        var name: String = "",

        var password: String = "",

        @OneToMany
        var posts: MutableList<Post>? = null
) {

        fun addPost(post: Post) {
                post.writer = this
                posts?.add(post)
        }

        fun setSignUp(signUpDto: AccountDto.SignUpRequest): Account {
                val account = Account()
                this.email = signUpDto.email
                this.name = signUpDto.name
                this.password = signUpDto.password
                return account
        }

        fun update(updateDto: AccountDto.UpdateRequest) {
                this.email = updateDto.email
                this.name = updateDto.name
                this.password = updateDto.password
        }

}
