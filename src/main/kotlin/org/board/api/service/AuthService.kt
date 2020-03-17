package org.board.api.service

import org.board.api.config.security.JwtTokenProvider
import org.board.api.domain.Account
import org.board.api.dto.AccountDto
import org.board.api.exception.auth.AccountExistException
import org.board.api.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
        @Autowired val accountRepository: AccountRepository,
        @Autowired val authenticationManager: AuthenticationManager,
        @Autowired val jwtTokenProvider: JwtTokenProvider,
        @Autowired val passwordEncoder: PasswordEncoder
) {

    fun countByEmail(email: String) = accountRepository.countByEmail(email)

    fun signUp(request: AccountDto.SignUpRequest): Long? {

        if (countByEmail(request.email) != 0) throw AccountExistException("이미 존재하는 계정입니다. email: ${request.email}")

        request.password = passwordEncoder.encode(request.password)
        val account = Account().setSignUp(request)

        return accountRepository.save(account).id
    }

    fun login(request: AccountDto.LoginRequest): String {

        val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.email, request.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        return jwtTokenProvider.generateToken(authentication)
    }

}