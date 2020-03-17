package org.board.api.exception.account

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "유저 정보가 존재하지 않습니다.")
class AccountNotFoundException : RuntimeException {
    constructor(id: Long) : super("해당하는 계정이 없습니다. id: $id")
    constructor(email: String) : super("해당하는 계정이 없습니다. email: $email")
}