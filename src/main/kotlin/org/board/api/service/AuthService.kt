package org.board.api.service

import org.board.api.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService(@Autowired val accountRepository: AccountRepository) {

    fun countByEmail(email: String) = accountRepository.countByEmail(email)


}