package com.swedist.computer_lab_gradle.config;

import com.swedist.computer_lab_gradle.aspect.JwtAuthenticationFilter;
import com.swedist.computer_lab_gradle.repository.UserRepository;
import com.swedist.computer_lab_gradle.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http,
                                         UserDetailsService userDetailsService,
                                         AuthenticationProvider authenticationProvider) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth
                        // api
                        .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                        .requestMatchers("/api/user/**").authenticated()
                        .requestMatchers("/api/user/password/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/computer/**").hasAnyAuthority("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.POST, "/api/computer/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/computer/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/computer/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/student/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/student/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/student/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/student/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/reservation/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/users-reservation/**").hasAnyAuthority("STUDENT", "ADMIN")

                        // mvc
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/computer/**").permitAll()
                        .requestMatchers("/student/**").permitAll()
                        .requestMatchers("/reservation/**").permitAll()

                        // static
                        .requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/images/**").permitAll()

                        .anyRequest().authenticated()
                )
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider);
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> {
                            if (request.getHeader("Authorization") == null) {
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                            } else {
                                response.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
                            }
                        }
                ));
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, userDetailsService);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
