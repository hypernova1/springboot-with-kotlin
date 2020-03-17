package org.board.api.controller


import com.fasterxml.jackson.databind.ObjectMapper
import org.board.api.domain.Account
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
internal class AccountControllerTest(
        @Autowired val mockMvc: MockMvc,
        @Autowired val mapper: ObjectMapper) {

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

    @Test
    fun modifyUser() {

        val account = Account()
        account.email = "rnjstoacks@naver.com"
        account.name = "melchor"
        account.password = "2222"

        mockMvc.put("/user/2") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(account)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { json("{}") }
        }.andDo { print() }
    }

    @Test
    fun deleteUser() {

        mockMvc.delete("/user/2")
                .andExpect {
                    status { isOk }
                }

    }
}