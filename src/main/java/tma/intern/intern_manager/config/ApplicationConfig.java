package tma.intern.intern_manager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tma.intern.intern_manager.entity.Account;
import tma.intern.intern_manager.enums.Role;
import tma.intern.intern_manager.repository.AccountRepository;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig implements ApplicationRunner {
    private final AccountRepository accountRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username ->
                accountRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Account is not found"));
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Account> existingAccount = accountRepository.findByUsername("admin");
        if(existingAccount.isEmpty()) {
            Account account = new Account();
            account.setUsername("admin");
            account.setPassword(passwordEncoder().encode("123456"));
            account.setRole(Role.ROLE_ADMIN);
            accountRepository.save(account);
        }

    }
}
