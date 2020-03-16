package org.board.api.constant

enum class ResultCode(val resultCode: String, val message: String) {

    EXIST_ACCOUNT("001", "이미 존재하는 이메일입니다."),
    NOT_EXIST_ACCOUNT("002", "사용 가능한 이메일입니다.")

}