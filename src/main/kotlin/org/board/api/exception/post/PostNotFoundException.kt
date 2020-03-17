package org.board.api.exception.post

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "존재하지 않는 게시글입니다.")
class PostNotFoundException(id: Long) : RuntimeException("게시글을 찾을 수 없습니다. -> id: $id")