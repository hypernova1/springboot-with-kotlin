package org.board.api.controller

import org.board.api.domain.Category
import org.board.api.repository.CategoryRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
internal class CategoryControllerTest(@Autowired val mockMvc: MockMvc, @Autowired val categoryRepository: CategoryRepository) {


    @AfterEach
    fun deleteDb() {
        categoryRepository.deleteAll()
    }

    @Test
    fun registerCategory() {

        val json = """
            { "name": "java", "path": "/java", "order": 1 }
        """.trimIndent()

        mockMvc.post("/category") {
                    contentType = MediaType.APPLICATION_JSON
                    content = json
                    accept = MediaType.APPLICATION_JSON
                }
                .andDo { print() }
                .andExpect {
                    status { isCreated }
                    content { json("{}") }
                }
    }

    @Test
    fun updateCategory() {

        val category = Category();
        category.name = "java"
        category.path = "/java"
        category.orderNo = 0

        val savedCategory = categoryRepository.save(category)

        val json = """
            { "name": "kotlin", "path": "/kotlin", "order": 1 }
        """.trimIndent()

        mockMvc.put("/category/${savedCategory.id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = json
                    accept = MediaType.APPLICATION_JSON
                }
                .andDo { print() }
                .andExpect {
                    status { isOk }
                    content { json("{}") }
                }
    }

}