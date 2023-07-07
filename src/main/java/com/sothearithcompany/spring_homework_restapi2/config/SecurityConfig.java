package com.sothearithcompany.spring_homework_restapi2.config;
import com.sothearithcompany.spring_homework_restapi2.jwt.JwtTokenFilter;
import com.sothearithcompany.spring_homework_restapi2.jwt.jwtModel.JwtAuthenticationEntryPoint;
import com.sothearithcompany.spring_homework_restapi2.service.serviceImp.AuthServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final AuthServiceImp authServiceImp;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenFilter tokenFilter;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authServiceImp);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity

                    .csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.html").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
                    .and().
                    // make sure we use stateless session; session won't be used to
                    // store user's state.
                            exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                    ;

            return httpSecurity.build();
    }

}
