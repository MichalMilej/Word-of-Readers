package pl.milej.michal.wordofreaders.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/authors/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/books/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/publishers/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/reviews/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/users/public/**")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/users")
                    .permitAll()
                    .antMatchers("/api-docs**")
                    .permitAll()
                    .antMatchers("/swagger-ui**")
                    .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/authors/**").hasAnyAuthority("MOD", "ADMIN")
                    .antMatchers("/books/**").hasAnyAuthority("USER", "MOD", "ADMIN")
                    .antMatchers("/publishers/**").hasAnyAuthority("MOD", "ADMIN")
                    .antMatchers("/users/**").hasAnyAuthority("USER", "MOD", "ADMIN")
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
