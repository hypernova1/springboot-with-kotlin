package org.board.api.exception.auth

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "이미 존재하는 이메일입니다.")
class AccountExistException(message: String) : RuntimeException(message)