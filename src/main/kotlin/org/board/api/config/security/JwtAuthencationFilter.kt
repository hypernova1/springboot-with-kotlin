package org.board.api.config.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthencationFilter(
        @Autowired
        private val jwtTokenProvider: JwtTokenProvider,
        @Autowired
        private val userDetailService: CustomUserDetailService
) : OncePerRequestFilter() {

    val logger = LoggerFactory.getLogger(this.javaClass)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        try {
            val jwt = this.getJwtFromRequest(request)
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validationToken(jwt!!)) {
                val email = jwtTokenProvider.getEmailFromJwt(jwt)

                val userDetails = userDetailService.loadUserByUsername(email)

                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
        }
        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return null
    }
}