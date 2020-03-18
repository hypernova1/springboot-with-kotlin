package org.board.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.board.api.dto.constant.ResultCode

class ApiResponse<T>(resultCodeEnum: ResultCode) {
    val resultCode: String = resultCodeEnum.resultCode
    val message: String = resultCodeEnum.message
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: T? = null
}