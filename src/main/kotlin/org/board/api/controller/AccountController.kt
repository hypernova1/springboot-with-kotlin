package org.board.api.controller

import org.board.api.dto.AccountDto
import org.board.api.dto.ApiResponse
import org.board.api.dto.constant.ResultCode
import org.board.api.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class AccountController(@Valid val accountService: AccountService) {

    @GetMapping
    fun getUsers(
            @RequestParam(defaultValue = "1") page: Int,
            @RequestParam(defaultValue ="10") size: Int): ResponseEntity<ApiResponse<List<AccountDto.InfoResponse>>> {

        val accountList = accountService.findAll(page, size)

        val response: ApiResponse<List<AccountDto.InfoResponse>> = ApiResponse(ResultCode.ACCOUNT_LIST_SEARCH_SUCCESS)
        response.data = accountList

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getUserDetail(@PathVariable id: Long): ResponseEntity<ApiResponse<AccountDto.InfoResponse>> {

        val account = accountService.findById(id)

        val result: ApiResponse<AccountDto.InfoResponse> = ApiResponse(ResultCode.ACCOUNT_SEARCH_SUCCESS)
        result.data = account

        return ResponseEntity.ok(result)
    }

    @PutMapping("/{id}")
    fun modifyUserInfo(
            @PathVariable id: Long,
            @Valid @RequestBody request: AccountDto.UpdateRequest): ResponseEntity<AccountDto.UpdateRequest> {
        accountService.updateInfo(id, request)
        val account = accountService.findById(id)

        val result: ApiResponse<AccountDto.InfoResponse> = ApiResponse(ResultCode.ACCOUNT_UPDATE_SUCCESS)
        result.data = account

        return ResponseEntity.ok(request)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<ApiResponse<Any>> {
        accountService.delete(id)

        val result: ApiResponse<Any> = ApiResponse(ResultCode.ACCOUNT_DELETE_SUCCESS)

        return ResponseEntity.ok(result)
    }

}