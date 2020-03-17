package org.board.api.service

import org.board.api.domain.Post
import org.board.api.dto.PostDto
import org.board.api.exception.post.PostNotFoundException
import org.board.api.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class PostService(@Autowired val postRepository: PostRepository) {

    fun findAll(page: Int, size: Int): MutableList<PostDto.Response>? {
        val posts = postRepository.findAll(PageRequest.of(page - 1, size, Sort.Direction.DESC, "id"))

        return posts.stream()
                .map { p -> PostDto.getResponse(p) }
                .collect(Collectors.toList())
    }

    fun findById(id: Long): PostDto.DetailResponse {
        val post = postRepository.findById(id)
                .orElseThrow { PostNotFoundException(id) }

        return PostDto.getDetailResponse(post)
    }

    fun save(request: PostDto.RegisterRequest): Long? {

        val post = Post()
        post.title = request.title
        post.content = request.content

        val savedPost = postRepository.save(post)

        return savedPost.id
    }

    @Transactional
    fun update(id: Long, request: PostDto.UpdateRequest) {
        val post = postRepository.findById(id)
                .orElseThrow { PostNotFoundException(id) }

        post.title = request.title
        post.content = request.content
    }

    @Transactional
    fun delete(id: Long) = postRepository.deleteById(id)

}