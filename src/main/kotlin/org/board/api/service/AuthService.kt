package org.board.api.service

import org.board.api.domain.Account
import org.board.api.dto.AccountDto
import org.board.api.exception.auth.AccountExistException
import org.board.api.exception.auth.IdNotFoundException
import org.board.api.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService(@Autowired val accountRepository: AccountRepository) {

    fun countByEmail(email: String) = accountRepository.countByEmail(email)

    fun signUp(request: AccountDto.SignUpRequest): Long? {

        if (countByEmail(request.email) != 0) throw AccountExistException("이미 존재하는 계정입니다. email: ${request.email}")

        val account = Account().setSignUp(request)

        return accountRepository.save(account).id
    }

    fun login(request: AccountDto.LoginRequest): AccountDto.LoginResponse {

        val account = accountRepository.findByEmailAndPassword(request.email, request.password)
                .orElseThrow { IdNotFoundException("잘못된 정보입니다. email: ${request.email}") }

        val result = AccountDto.LoginResponse()
        result.id = account.id
        result.email = account.email
        result.name = account.name

        return result
    }


}