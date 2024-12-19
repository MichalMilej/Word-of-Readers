package pl.milej.michal.worldofreaders.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.milej.michal.worldofreaders.user.UserServiceImpl;

@Configuration
@RequiredArgsConstructor
public class AuthenticationProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserServiceImpl userService;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
