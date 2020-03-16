package org.board.api.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
@AutoConfigureMockMvc
internal class AuthControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun checkEmail() {
        mockMvc.perform(get("/auth/email-check/chtlstjd01@naver.com"))
                .andDo(print())
                .andExpect(status().isBadRequest)
    }
}