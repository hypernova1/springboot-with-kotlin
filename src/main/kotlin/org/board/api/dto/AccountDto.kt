package org.board.api.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

class AccountDto {

    class SignUpRequest {
        @Email(message = "이메일은 필수입니다.")
        var email: String = ""
        @NotBlank(message = "이름은 필수입니다.")
        var name: String = ""
        @NotEmpty(message = "비밀번호는 필수입니다.")
        var password: String = ""
    }

    class LoginRequest {
        @NotBlank(message = "이메일은 필수입니다.")
        var email: String = ""
        @NotEmpty(message = "비밀번호는 필수입니다.")
        var password: String = ""
    }

    class LoginResponse(
            var id: Long? = null,
            var email: String = "",
            var name: String = ""
    )

    class InfoResponse(
        var id: Long? = null,
        var email: String = "",
        var name: String = ""
    )

}