package org.board.api

import org.board.api.domain.Account
import org.board.api.domain.Post
import org.board.api.repository.AccountRepository
import org.board.api.repository.PostRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class Application {

    @Bean
    fun init(postRepository: PostRepository, accountRepository: AccountRepository) = CommandLineRunner {

        val account = Account()
        account.email = "chtlstjd01@naver.com"
        account.name = "sam"
        account.password = "1111"
        val savedAccount = accountRepository.save(account)

        val post = Post()
        post.title = "title"
        post.content = "content"
        post.writer = savedAccount
        postRepository.save(post)

        println("======== ${savedAccount.id} =======")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

