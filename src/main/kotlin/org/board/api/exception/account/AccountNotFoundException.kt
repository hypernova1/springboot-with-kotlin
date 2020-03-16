package org.board.api.exception.account

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "유저 정보가 존재하지 않습니다.")
class AccountNotFoundException(message: String) : RuntimeException(message)