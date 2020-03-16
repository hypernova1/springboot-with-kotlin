package org.board.api.controller

import org.board.api.constant.ResultCode
import org.board.api.dto.AccountDto
import org.board.api.dto.ApiResponse
import org.board.api.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(@Autowired val authService: AuthService) {

    @GetMapping("/email-check/{email}")
    fun checkEmail(@PathVariable email: String): ResponseEntity<Any> {

        val count = authService.countByEmail(email)

        val response: ApiResponse<Boolean>

        if (count != 0) {
            response = ApiResponse(ResultCode.EXIST_ACCOUNT)
            response.data = false
            return ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }

        response = ApiResponse(ResultCode.NOT_EXIST_ACCOUNT)
        response.data = true

        return ResponseEntity.ok(response)

    }

    @PostMapping("/sign-up")
    fun signUp(@Valid request: AccountDto.SignUpRequest) {

    }

}