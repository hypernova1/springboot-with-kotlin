package org.board.api.repository

import org.board.api.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, Long> {

    fun countByEmail(email: String): Int
    fun findByEmailAndPassword(email: String, password: String): Optional<Account>

}