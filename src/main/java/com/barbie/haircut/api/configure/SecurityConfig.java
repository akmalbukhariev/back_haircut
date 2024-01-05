package com.barbie.haircut.api.configure;

import com.barbie.haircut.api.security.AdminDetailsServiceImpl;
import com.barbie.haircut.api.security.filter.AdminAuthenticationFilter;
import com.barbie.haircut.api.security.jwt.JwtAuthenticationFilter;
import com.barbie.haircut.api.security.jwt.JwtTokenProvider;
import com.barbie.haircut.api.security.provider.AdminAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminDetailsServiceImpl adminDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AdminAuthenticationProvider adminProvider = new AdminAuthenticationProvider(adminDetailService, PasswordEncoderFactories.createDelegatingPasswordEncoder());
        AdminAuthenticationFilter adminAuthFilter = new AdminAuthenticationFilter(jwtTokenProvider, adminProvider);
        adminAuthFilter.setFilterProcessesUrl("/passblock/api/v1/admin/login");


        http.cors().and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
                .and().authorizeRequests().antMatchers("/swagger-ui/**").permitAll()
                .and().authorizeRequests().antMatchers("/v3/api-docs/**").permitAll();

        http.addFilter(adminAuthFilter);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
