package org.board.api

import org.board.api.domain.Account
import org.board.api.domain.Post
import org.board.api.repository.AccountRepository
import org.board.api.repository.PostRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun init(postRepository: PostRepository, accountRepository: AccountRepository) = CommandLineRunner {
        val post = Post()
        post.title = "title"
        post.content = "content"

        postRepository.save(post)

        val account = Account()
        account.email = "chtlstjd01@naver.com"
        account.name = "sam"
        account.password = "1111"

        val savedAccount = accountRepository.save(account)

        println("======== ${savedAccount.id} =======")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

