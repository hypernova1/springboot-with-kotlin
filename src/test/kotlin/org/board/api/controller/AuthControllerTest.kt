package org.board.api.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header

@SpringBootTest
@AutoConfigureMockMvc
internal class AuthControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun checkEmail() {
        mockMvc.perform(get("/auth/email-check/chtlstjd01@naver.com"))
                .andDo(print())
                .andExpect(status().isBadRequest)
    }

    @Test
    fun signUp() {

        val json = """
            {"email": "hypemova@gmail.com", "name": "sam", "password": "1111"}
        """.trimIndent()

        mockMvc.perform(post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated)
                .andExpect(header().exists(HttpHeaders.LOCATION))
    }

    @Test
    fun login() {

        val json = """
            {"email": "chtlstjd01@naver.com", "password": "1111"}
        """.trimIndent()

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk)

    }
}