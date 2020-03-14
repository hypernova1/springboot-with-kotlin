package org.board.api.controller

import org.board.api.repository.PostRepository
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class PostControllerTest(
        @Autowired
        val mockMvc: MockMvc,
        @Autowired
        val postRepository: PostRepository) {

    @AfterEach
    fun deleteAll() {

    }

    @Test
    fun getPosts() {
        mockMvc.perform(get("/post"))
                .andExpect(status().isOk)
    }
}
