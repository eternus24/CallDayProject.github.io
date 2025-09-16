package com.cdp.health.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.cdp.health.user.UserSecurityService;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserSecurityService userSecurityService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userSecurityService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
    	
    	return new HandlerMappingIntrospector();
    
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    	
    	MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

	    http
	    .authorizeHttpRequests(auth -> auth
	    .requestMatchers(
	    mvc.pattern("/"),
	    mvc.pattern("/index"),
	    mvc.pattern("/login"),
	    mvc.pattern("/signup"),
	    mvc.pattern("/error"),
	    mvc.pattern("/css/**"),
	    mvc.pattern("/js/**"),
	    mvc.pattern("/images/**"),
	    mvc.pattern("/address/**"),
	    mvc.pattern("/addr/**"),
	    mvc.pattern("/api/public/**")
	    ).permitAll()
	    .anyRequest().authenticated()
	    )
	    .formLogin(login -> login
	    .loginPage("/login")
	    .defaultSuccessUrl("/", true) // 로그인 성공 시 메인 페이지로 이동
	    .permitAll()
	    )
	    .logout(logout -> logout.logoutUrl("/logout"))
	    .authenticationProvider(daoAuthenticationProvider());
	
	
	    return http.build();
        
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        
    	return config.getAuthenticationManager();
        
    }
    
}
