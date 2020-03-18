package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import org.board.api.dto.AccountDto
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.stream.Collectors
import javax.persistence.*

@Entity
data class Account(

        @Column(unique = true)
        var email: String = "",

        var name: String = "",

        var password: String = "",

        @Enumerated(EnumType.STRING)
        @OneToMany(cascade = [CascadeType.ALL])
        var roles: MutableSet<Role> = HashSet(),

        @OneToMany(mappedBy = "writer")
        val posts: MutableList<Post> = mutableListOf()


) : DateAudit() {

        fun addPost(post: Post) {
                post.writer = this
                posts.add(post)
        }

        fun getAutorities(): User {

                val autorities = this.roles
                        .stream()
                        .map { role -> SimpleGrantedAuthority("ROLE_$role") }
                        .collect(Collectors.toSet())

                return User(this.email, this.password, autorities)
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
