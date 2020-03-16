package org.board.api.exception.auth

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "일치하는 계정이 없습니다.")
class IdNotFoundException(message: String) : RuntimeException(message)