package org.board.api.controller


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
internal class AccountControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun getUsers() {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(jsonPath("$.data.content").isNotEmpty)
    }

    @Test
    fun getUser() {
        mockMvc.get("/user/2")
                .andDo { print() }
                .andExpect {
                    status { isOk }
                    content { json("{}") }
                }
    }
}