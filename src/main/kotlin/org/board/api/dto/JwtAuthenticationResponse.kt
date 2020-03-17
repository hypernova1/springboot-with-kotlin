package org.board.api.dto

data class JwtAuthenticationResponse(var accessToken: String = "") {
    val tokenType: String = "Bearer"
}