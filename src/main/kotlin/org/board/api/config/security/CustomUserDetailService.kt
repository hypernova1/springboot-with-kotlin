package org.board.api.config.security

import org.board.api.exception.account.AccountNotFoundException
import org.board.api.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(@Autowired val accountRepository: AccountRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val account = accountRepository.findByEmail(email)
                .orElseThrow { AccountNotFoundException(email) }

        return UserPrincipal.create(account)
    }

}