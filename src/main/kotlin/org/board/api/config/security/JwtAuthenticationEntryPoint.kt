package org.board.api.config.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java.name)

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, e: AuthenticationException) {

        logger.error("Responding with unauthorized error. Message - ${e.message}")

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
    }
}