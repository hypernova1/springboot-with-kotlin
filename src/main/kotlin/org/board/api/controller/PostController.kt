package org.board.api.controller

import org.board.api.dto.ApiResponse
import org.board.api.dto.PostDto
import org.board.api.dto.constant.ResultCode
import org.board.api.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/post")
class PostController(@Autowired val postService: PostService) {

    @GetMapping
    fun getPost(@RequestParam(defaultValue = "1") page: Int,
                @RequestParam (defaultValue = "10") size: Int): ResponseEntity<ApiResponse<MutableList<PostDto.Response>>> {

        val posts = postService.findAll(page, size)

        val response = ApiResponse<MutableList<PostDto.Response>>(ResultCode.POST_LIST_SEARCH_SUCCESS)
        response.data = posts

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ApiResponse<PostDto.DetailResponse>> {
        val post = postService.findById(id)

        val response = ApiResponse<PostDto.DetailResponse>(ResultCode.POST_SEARCH_SUCCESS)
        response.data = post

        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun registerPost(@RequestBody request: PostDto.RegisterRequest): ResponseEntity<ApiResponse<Any>> {
        val savedId = postService.save(request)

        val response = ApiResponse<Any>(ResultCode.POST_REGISTER_SUCCESS)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri()

        return ResponseEntity.created(location).body(response)
    }

    @PutMapping("/{id}")
    fun modifyPost(@PathVariable id: Long,
                   @RequestBody request: PostDto.UpdateRequest): ResponseEntity<ApiResponse<PostDto.DetailResponse>> {
        postService.update(id, request)

        val post = postService.findById(id)

        val response = ApiResponse<PostDto.DetailResponse>(ResultCode.POST_UPDATE_SUCCESS)
        response.data = post

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Any> {
        postService.delete(id)

        val response = ApiResponse<Any>(ResultCode.POST_DELETE_SUCCESS)

        return ResponseEntity.ok().body(response)
    }

}