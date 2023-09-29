package com.example.opd.configurations;

import com.example.opd.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/", "/registration","/block/info/{id}").permitAll()
                        .requestMatchers("/block/info/{id}/quality").hasAnyRole("EXPERT")
                        .requestMatchers("/TestLobby/SoundTest","/TestLobby","/TestLobby/SoundTest/saveResult",
                                "/TestLobby/NumberTest","/TestLobby/NumberTest/saveResult","/TestLobby/ColorTest/saveResult",
                                "/TestLobby/ColorTest","/TestLobby/ColorTestHard/saveResult","/TestLobby/ColorTestHard",
                                "/TestLobby/moveTest/saveResult","/TestLobby/moveTest","/TestLobby/moveTestHard/saveResult","/TestLobby/moveTestHard","/TestLobby/RavenTest/saveResult","/TestLobby/RavenTest","/TestLobby/RedBlackTest/saveResult","/TestLobby/RedBlackTest","/TestLobby/MemoryTest/saveResult","/TestLobby/MemoryTest").hasAnyRole("USER")
                        .requestMatchers("/admin","/admin/ban/{id}","/admin/unban/{id}","/admin/edit/{user}","/admin/edit").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                );

        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
