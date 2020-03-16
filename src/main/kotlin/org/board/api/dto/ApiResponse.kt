package org.board.api.dto

import org.board.api.constant.ResultCode

class ApiResponse<T>(resultCodeEnum: ResultCode) {
    val resultCode: String = resultCodeEnum.resultCode
    val message: String = resultCodeEnum.message
    var data: T? = null
}