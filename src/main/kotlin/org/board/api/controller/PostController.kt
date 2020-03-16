package org.board.api.controller

import org.board.api.domain.Post
import org.board.api.dto.PostDto
import org.board.api.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriBuilderFactory
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/post")
class PostController(@Autowired val postService: PostService) {

    @GetMapping
    fun getPost(@RequestParam(defaultValue = "1") page: Int,
                @RequestParam (defaultValue = "10") size: Int) = postService.findAll(page, size);

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = postService.findById(id);

    @PostMapping
    fun registerPost(@RequestBody request: PostDto.RegisterRequest): ResponseEntity<Any> {
        val savedId = postService.save(request)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri()

        return ResponseEntity.created(location).build()
    }

    @PutMapping("/{id}")
    fun modifyPost(@PathVariable id: Long,
                   @RequestBody request: PostDto.UpdateRequest): ResponseEntity<Post> {
        postService.update(id, request)
        val post = postService.findById(id)

        return ResponseEntity.ok(post)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Any> {
        postService.delete(id)

        return ResponseEntity.ok().build()
    }

}