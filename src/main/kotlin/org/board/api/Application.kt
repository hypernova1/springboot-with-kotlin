package org.board.api

import org.board.api.domain.Account
import org.board.api.domain.Post
import org.board.api.repository.PostRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun init(postRepository: PostRepository) = CommandLineRunner {
        val post: Post = Post();
        post.title = "title";
        post.content = "content"

        postRepository.save(post)

        val findByTitle = postRepository.findByTitle("title")
                .orElseThrow{ RuntimeException("test") }
        println(findByTitle.content)

    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

