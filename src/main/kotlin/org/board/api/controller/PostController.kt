package org.board.api.controller

import org.board.api.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(@Autowired val postService: PostService) {

    @GetMapping("/post")
    fun getPost(@RequestParam(defaultValue = "1") page: Int,
                @RequestParam (defaultValue = "10") size: Int) = postService.findAll(page, size);
}