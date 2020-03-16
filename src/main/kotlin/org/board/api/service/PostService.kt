package org.board.api.service

import org.board.api.domain.Post
import org.board.api.dto.PostDto
import org.board.api.exception.PostNotFoundException
import org.board.api.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(@Autowired val postRepository: PostRepository) {

    fun findAll(page: Int, size: Int)
            = postRepository.findAll(PageRequest.of(page - 1, size, Sort.Direction.DESC, "id"));

    fun findById(id: Long): Post = postRepository.findById(id)
            .orElseThrow{ PostNotFoundException("게시글을 찾을 수 없습니다. -> id: $id") }

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
                .orElseThrow { PostNotFoundException("게시글을 찾을 수 없습니다. -> id: $id") }

        post.title = request.title
        post.content = request.content
    }

    @Transactional
    fun delete(id: Long) = postRepository.deleteById(id)



}