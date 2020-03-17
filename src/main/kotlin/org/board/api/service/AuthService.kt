package org.board.api.service

import org.board.api.domain.Account
import org.board.api.dto.AccountDto
import org.board.api.exception.auth.AccountExistException
import org.board.api.exception.auth.IdNotFoundException
import org.board.api.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.AbstractPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
        @Autowired val accountRepository: AccountRepository,
        @Autowired val passwordEncoder: PasswordEncoder
) {

    fun countByEmail(email: String) = accountRepository.countByEmail(email)

    fun signUp(request: AccountDto.SignUpRequest): Long? {

        if (countByEmail(request.email) != 0) throw AccountExistException("이미 존재하는 계정입니다. email: ${request.email}")

        request.password = passwordEncoder.encode(request.password)
        val account = Account().setSignUp(request)

        return accountRepository.save(account).id
    }

    fun login(request: AccountDto.LoginRequest): AccountDto.LoginResponse {

        val encodedPassword = passwordEncoder.encode(request.password)

        val account = accountRepository.findByEmailAndPassword(request.email, encodedPassword)
                .orElseThrow { IdNotFoundException("잘못된 정보입니다. email: ${request.email}") }

        return AccountDto.LoginResponse(account.id, account.email, account.name)
    }

}