package org.board.api.config

import org.board.api.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,  //@Secured
        jsr250Enabled = true,   //@RolesAllowed
        prePostEnabled = true   //@PreAuthorize
)
class SecurityConfig(@Autowired private val accountService: AccountService) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

}