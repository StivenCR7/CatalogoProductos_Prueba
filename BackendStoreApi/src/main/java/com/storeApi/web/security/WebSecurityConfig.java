package com.storeApi.web.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
        http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class) 
                .authorizeHttpRequests(requests -> requests            
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers("/api/categorias/**", "/api/productos/**", "/api/catalogo/**").authenticated() 
                        .anyRequest().authenticated())
                .sessionManagement(management -> management.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
	
	@Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // url fronted
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}


