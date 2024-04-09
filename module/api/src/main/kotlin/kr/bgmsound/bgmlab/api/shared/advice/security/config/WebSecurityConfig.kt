package kr.bgmsound.bgmlab.api.shared.advice.security.config

import kr.bgmsound.bgmlab.api.shared.advice.AuthEntryPoint
import kr.bgmsound.bgmlab.api.shared.advice.security.filter.CustomJwtFilter
import kr.bgmsound.bgmlab.api.shared.advice.WebAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class WebSecurityConfig(
    private val jwtFilter: CustomJwtFilter,
    private val authEntryPoint: AuthEntryPoint,
    private val webAccessDeniedHandler: WebAccessDeniedHandler
) {

    @Bean
    fun doFilter(http: HttpSecurity): SecurityFilterChain? {
        http
            .headers {
                it.frameOptions { frameOption ->
                    frameOption.sameOrigin()
                }
            }
            .csrf {
                it.disable()
            }
            .formLogin {
                it.disable()
            }
            .httpBasic {
                it.disable()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/login").anonymous()
                    .requestMatchers("/oauth/**").anonymous()
                    .requestMatchers("/refresh").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it
                    .authenticationEntryPoint(authEntryPoint)
                    .accessDeniedHandler(webAccessDeniedHandler)
            }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}