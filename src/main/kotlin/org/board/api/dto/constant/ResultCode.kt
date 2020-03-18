package org.board.api.dto.constant

enum class ResultCode(val resultCode: String, val message: String) {

    EXIST_ACCOUNT("001", "이미 존재하는 이메일입니다."),
    NOT_EXIST_ACCOUNT("002", "사용 가능한 이메일입니다."),
    ACCOUNT_RESISTER_SUCCESS("003", "가입이 완료되었습니다."),
    LOGIN_SUCCESS("004", "로그인을 성공하였습니다."),
    ACCOUNT_LIST_SEARCH_SUCCESS("005", "유저 리스트 조회가 완료되었습니다."),
    ACCOUNT_SEARCH_SUCCESS("006", "유저 정보 조회가 완료되었습니다."),
    ACCOUNT_UPDATE_SUCCESS("007", "유저 정보 변경이 완료되었습니다."),
    ACCOUNT_DELETE_SUCCESS("008", "유저 삭제가 완료되었습니다."),

    POST_LIST_SEARCH_SUCCESS("100", "글 목록 조회가 완료되었습니다."),
    POST_REGISTER_SUCCESS("101", "글 등록이 완료되었습니다."),
    POST_SEARCH_SUCCESS("102", "글 조회가 완료되었습니다."),
    POST_UPDATE_SUCCESS("103", "글 수정이 완료되었습니다."),
    POST_DELETE_SUCCESS("104", "글 삭제가 완료되었습니다."),

    CATEGORY_REGISTER_SUCCESS("201", "메뉴 등록이 완료되었습니다."),
    CATEGORY_UPDATE_SUCCESS("202", "메뉴 수이 완료되었습니다."),
    CATEGORY_LIST_SEARCH_SUCCESS("203", "메뉴 리스트 조회가 완료되었습니다.")


}