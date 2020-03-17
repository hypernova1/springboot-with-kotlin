package org.board.api.service

import org.board.api.domain.Account
import org.board.api.domain.Comment
import org.board.api.dto.CommentDto
import org.board.api.exception.post.PostNotFoundException
import org.board.api.repository.CommentRepository
import org.board.api.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService(
        @Autowired val commentRepository: CommentRepository,
        @Autowired val postRepository: PostRepository) {

    fun save(request: CommentDto.Request): Long {

        val post = postRepository.findById(request.postId)
                .orElseThrow { PostNotFoundException(request.postId) }

        val comment = Comment()
        comment.post = post
        comment.content = request.content

        val savedComment = commentRepository.save(comment)

        return savedComment.id
    }
}