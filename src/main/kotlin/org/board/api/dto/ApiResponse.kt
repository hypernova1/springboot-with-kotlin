package org.board.api.dto

import org.board.api.dto.constant.ResultCode

class ApiResponse<T>(resultCodeEnum: ResultCode) {
    val resultCode: String = resultCodeEnum.resultCode
    val message: String = resultCodeEnum.message
    var data: T? = null
}