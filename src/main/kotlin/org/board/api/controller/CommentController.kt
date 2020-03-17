package org.board.api.controller

import org.board.api.dto.CommentDto
import org.board.api.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/comment")
class CommentController(@Autowired val commentService: CommentService) {

    @PostMapping
    fun registerComment(@Valid @RequestBody request: CommentDto.Request) {

        commentService.save(request)

    }

}
