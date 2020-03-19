package org.board.api.controller

import org.board.api.domain.Post
import org.board.api.repository.AccountRepository
import org.board.api.repository.CategoryRepository
import org.board.api.repository.PostRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
@AutoConfigureMockMvc
internal class PostControllerTest(
        @Autowired
        val mockMvc: MockMvc,
        @Autowired
        val postRepository: PostRepository,
        @Autowired
        val categoryRepository: CategoryRepository
) {

    @AfterEach
    fun deleteAll() {
        postRepository.deleteAll()
    }

    @Test
    fun getPosts() {
        mockMvc.perform(get("/post/java"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$").isNotEmpty)
    }

    @Test
    fun getPost() {

        val post = postRepository.findAll()[0]

        mockMvc.perform(get("/post/java/${post.id}"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$").isNotEmpty)
    }

    @Test
    fun registerPost() {
        val json = """
            {"title": "test", "content": "test"}
        """.trimIndent()

        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated)
    }

    @Test
    fun updatePost() {

        val category = categoryRepository.findAll()[0]

        val post = Post()
        post.title = "title"
        post.content = "content"
        post.category = category

        val postId = postRepository.save(post).id

        val json = """
            {"title": "test2", "content": "test2"}
        """.trimIndent()

        mockMvc.perform(put("/post/$postId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk)
    }

    @Test
    fun deletePost() {

        val category = categoryRepository.findAll()[0]

        val post = Post()
        post.title = "title"
        post.content = "content"
        post.category = category

        val postId = postRepository.save(post).id

        mockMvc.perform(delete("/post/$postId"))
                .andDo(print())
                .andExpect(status().isOk)

    }

}
