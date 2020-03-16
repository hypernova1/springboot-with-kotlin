package org.board.api.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AccountDto {

    data class SignUpRequest(
         @NotBlank
         val email: String = "",
         @NotNull
         val username: String = "",
         @NotNull
         val password: String = ""
    )

}