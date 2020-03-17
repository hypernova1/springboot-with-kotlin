package org.board.api.controller

import org.board.api.dto.constant.ResultCode
import org.board.api.dto.AccountDto
import org.board.api.dto.ApiResponse
import org.board.api.dto.JwtAuthenticationResponse
import org.board.api.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
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
    fun signUp(@Valid @RequestBody request: AccountDto.SignUpRequest): ResponseEntity<ApiResponse<Any>> {
        val savedId = authService.signUp(request)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri()

        val response = ApiResponse<Any>(ResultCode.ACCOUNT_RESISTER_SUCCESS)

        return ResponseEntity.created(location).body(response)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: AccountDto.LoginRequest): ResponseEntity<ApiResponse<JwtAuthenticationResponse>> {

        val token = authService.login(request)

        val result = ApiResponse<JwtAuthenticationResponse>(ResultCode.LOGIN_SUCCESS)
        result.data = JwtAuthenticationResponse(token)

        return ResponseEntity.ok(result)
    }

}