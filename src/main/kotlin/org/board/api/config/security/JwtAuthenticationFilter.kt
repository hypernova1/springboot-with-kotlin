package org.board.api.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider
    @Autowired
    private lateinit var userDetailService: CustomUserDetailService

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
            logger.error("Could not set user authentication in security context, $e")
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