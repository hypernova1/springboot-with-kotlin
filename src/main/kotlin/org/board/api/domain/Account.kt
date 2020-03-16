package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import org.board.api.dto.AccountDto
import javax.persistence.*

@Entity
class Account : DateAudit() {

        @Id
        @GeneratedValue
        val id: Long = -1

        @Column(unique = true)
        var email: String = ""

        var name: String = ""

        var password: String = ""

        @OneToMany
        private val _posts = mutableListOf<Post>()

        val posts get() = _posts.toList()

        @OneToMany
        private val _comments = mutableListOf<Comment>()
        val comments get() = _comments.toList()

        fun addPost(post: Post) {
                post.writer = this
                _posts.add(post)
        }

        fun addComment(comment: Comment) {
                comment.writer = this
                _comments.add(comment)
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

        companion object {
                fun getDto(account: Account): AccountDto.InfoResponse {
                        val result = AccountDto.InfoResponse()
                        result.id = account.id
                        result.name = account.name
                        result.email = account.email

                        return result
                }
        }

}
