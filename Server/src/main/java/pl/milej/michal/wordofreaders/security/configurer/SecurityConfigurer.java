package pl.milej.michal.wordofreaders.security.configurer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurer {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/users")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/users/{userId}/public")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/books/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/reviews/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/authors/**")
                    .permitAll()
                .and().authorizeRequests()
                    .antMatchers("/books/**").hasAnyAuthority("MOD", "ADMIN")
                    .antMatchers("/authors/**").hasAnyAuthority("MOD", "ADMIN")
                    .antMatchers("/users/**").hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                .and()
                    .httpBasic()
                .and()
                    .build();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider);
    }
}
