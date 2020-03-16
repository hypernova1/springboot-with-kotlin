package org.board.api.exception.account

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "유저 목록이 존재하지 않습니다.")
class AccountListNotFoundException(message: String) : RuntimeException(message)