package org.board.api.exception.category

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "존재하지 않는 메뉴입니다.")
class CategoryNotFoundException(id: Long) : RuntimeException("메뉴를 찾을 수 없습니다. id: $id")