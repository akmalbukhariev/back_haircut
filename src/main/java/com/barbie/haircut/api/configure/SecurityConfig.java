package com.barbie.haircut.api.configure;

import com.barbie.haircut.api.security.AdminDetailsServiceImpl;
import com.barbie.haircut.api.security.UserDetailsServiceImpl;
import com.barbie.haircut.api.security.filter.AdminAuthenticationFilter;
import com.barbie.haircut.api.security.filter.UserAuthenticationFilter;
import com.barbie.haircut.api.security.jwt.JwtAuthenticationFilter;
import com.barbie.haircut.api.security.jwt.JwtTokenProvider;
import com.barbie.haircut.api.security.provider.AdminAuthenticationProvider;
import com.barbie.haircut.api.security.provider.UserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminDetailsServiceImpl adminDetailService;
    private final UserDetailsServiceImpl userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            //Actuator
            "/actuator/**",

            "/**/webjars/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            // 유저 로그인
            "/haircut/api/v1/user/login",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/haircut/images/**",
            //"/passblock/api/v1/user/login",
            //일단 시큐리티 무시하고 전부허용
            // "/passblock/api/v1/admin/**",
            // "/passblock/api/v1/user/**"
    };

    public AdminAuthenticationProvider adminProvider() {
        return new AdminAuthenticationProvider(adminDetailService, passwordEncoder());
    }

    public UserAuthenticationProvider userProvider() {
        return new UserAuthenticationProvider(userDetailsService, passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        UserAuthenticationFilter userAuthFilter = new UserAuthenticationFilter(jwtTokenProvider, userProvider());
        userAuthFilter.setFilterProcessesUrl("/haircut/api/v1/user/login");

        AdminAuthenticationFilter adminAuthFilter = new AdminAuthenticationFilter(jwtTokenProvider, adminProvider());
        adminAuthFilter.setFilterProcessesUrl("/haircut/api/v1/admin/login");


        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"
                ,"http://localhost:8080"
                ,"http://localhost"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization", "access-token", "refresh-token","enckey"));
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.OPTIONS, "/passblock/api/v1/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/passblock/api/v1/admin/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/haircut/api/v1/user/**").hasAnyRole("USER");

        http.addFilter(userAuthFilter);
        http.cors().configurationSource(request -> corsConfiguration);
        http.addFilter(adminAuthFilter);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**/webjars/**",
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/v3/api-docs/**",
                "/haircut/api/v1//admin/**",
                "/haircut/api/v1/user/**",
                //"http://localhost:8080/images/",
                "/haircut/images/**",
                "/haircut/api/v1/hairdresser/**",
                "/error");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
