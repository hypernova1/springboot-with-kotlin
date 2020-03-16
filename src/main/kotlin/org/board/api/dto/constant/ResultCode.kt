package org.board.api.dto.constant

enum class ResultCode(val resultCode: String, val message: String) {

    EXIST_ACCOUNT("001", "이미 존재하는 이메일입니다."),
    NOT_EXIST_ACCOUNT("002", "사용 가능한 이메일입니다."),
    ACCOUNT_RESISTER_SUCCESS("003", "가입이 완료되었습니다."),
    LOGIN_SUCCESS("004", "로그인을 성공하였습니다.")

}