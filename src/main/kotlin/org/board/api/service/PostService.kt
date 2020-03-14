package org.board.api.service

import org.board.api.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostService(@Autowired val postRepository: PostRepository) {

    fun findAll(page: Int, size: Int) = postRepository.findAll(PageRequest.of(page - 1, size));

}