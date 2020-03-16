package org.board.api.repository

import org.board.api.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {

    fun countByEmail(email: String): Int

}