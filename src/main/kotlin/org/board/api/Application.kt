package org.board.api

import org.board.api.domain.*
import org.board.api.repository.AccountRepository
import org.board.api.repository.CategoryRepository
import org.board.api.repository.PostRepository
import org.board.api.repository.RoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.event.ContextClosedEvent
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
@EnableJpaAuditing
class Application {

    @Bean
    fun init(postRepository: PostRepository,
             accountRepository: AccountRepository,
             roleRepository: RoleRepository,
             categoryRepository: CategoryRepository,
             passwordEncoder: PasswordEncoder) = CommandLineRunner {

        val category = Category()
        category.name = "자바"
        category.path = "java"
        category.orderNo = 0

        val savedCategory = categoryRepository.save(category)


        postRepository.deleteAll()
        accountRepository.deleteAll()
        roleRepository.deleteAll()

        val roleAdmin = Role()
        roleAdmin.name = RoleName.ADMIN

        val roleUser = Role()
        roleUser.name = RoleName.USER

        roleRepository.saveAll(listOf(roleUser))

        val account = Account()
        account.email = "chtlstjd01@naver.com"
        account.name = "sam"
        account.password = passwordEncoder.encode("1111")
        account.roles = mutableSetOf(roleAdmin)
        val savedAccount = accountRepository.save(account)

        val post = Post()
        post.title = "title"
        post.content = "content"
        post.writer = savedAccount
        post.category = savedCategory
        postRepository.save(post)

        println("======== ${savedAccount.id} =======")
    }

    @Bean
    fun applicationLister(
            categoryRepository: CategoryRepository,
            postRepository: PostRepository
    ): ApplicationListener<ContextClosedEvent> {
        return ApplicationListener {
            postRepository.deleteAll()
            categoryRepository.deleteAll()
            println("application end")
        }
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

