package org.board.api.config.security

import org.board.api.domain.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

data class UserPrincipal(
        val id: Long,
        val email: String,
        private val username: String,
        private val password: String,
        private val authorities: Set<GrantedAuthority>


) : UserDetails {

    override fun getAuthorities() = authorities
    override fun getUsername() = username
    override fun getPassword() = password
    override fun isCredentialsNonExpired() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isEnabled() = true

    companion object {
        fun create(account: Account): UserPrincipal {
            val authorities = account.roles.stream()
                    .map { role -> SimpleGrantedAuthority(role.name.name) }
                    .collect(Collectors.toSet())

            return UserPrincipal(account.id, account.email, account.name, account.password, authorities)
        }
    }
}