package org.board.api.service

import org.board.api.domain.Account
import org.board.api.dto.AccountDto
import org.board.api.exception.account.AccountListNotFoundException
import org.board.api.exception.account.AccountNotFoundException
import org.board.api.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AccountService(@Autowired val accountRepository: AccountRepository) {

    fun findAll(page: Int, size: Int): List<AccountDto.InfoResponse> {
        val userList =
                accountRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "id")).content

        if (userList.isEmpty()) throw AccountListNotFoundException("유저 리스트가 존재하지 않습니다. page: $page")

        return userList.map { u -> AccountDto.InfoResponse(u.id, u.email, u.name) }
    }

    fun findById(id: Long): AccountDto.InfoResponse {

        val account = accountRepository.findById(id)
                .orElseThrow { AccountNotFoundException("해당하는 계정이 없습니다. id: $id") }

        val result = AccountDto.InfoResponse()
        result.id = account.id
        result.email = account.email
        result.name = account.name

        return result
    }

}