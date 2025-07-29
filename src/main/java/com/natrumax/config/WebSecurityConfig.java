package com.natrumax.config;
import com.natrumax.security.jwt.AuthEntryPointJwt;
import com.natrumax.security.jwt.AuthTokenFilter;
import com.natrumax.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
//(securedEnabled = true,
//jsr250Enabled = true,
//prePostEnabled = true) // by default
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/login").permitAll()
<<<<<<< HEAD
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .requestMatchers("/api/v1/users/**").permitAll()
                                .requestMatchers("/api/v1/roles/**").permitAll()
                                .requestMatchers("/api/v1/products/**").permitAll()
                                .requestMatchers("/api/v1/categories/**").permitAll()
                                .requestMatchers("/api/v1/promotions/**").permitAll()
                                .requestMatchers("/api/v1/discounts/**").permitAll()
                                .requestMatchers("/api/v1/orders/**").permitAll()
                                .requestMatchers("/api/v1/warehouses/**").permitAll()
                                .requestMatchers("/api/v1/lottery-codes/**").permitAll()
                                .requestMatchers("/api/v1/rewards/**").permitAll()
                                .requestMatchers("/api/v1/user-warehouses/**").permitAll()
                                .requestMatchers("/api/v1/provinces/**").permitAll()
                                .requestMatchers("/api/v1/commission-histories/**").permitAll()
                                .requestMatchers("/api/v1/commission-policies/**").permitAll()
                                .requestMatchers("/api/v1/commissions/**").permitAll()
                                .requestMatchers("/api/v1/warehouse-products/**").permitAll()
                                .requestMatchers("/api/v1/wallets/**").permitAll()
                                .requestMatchers("/api/v1/order-details/**").permitAll()
                                .requestMatchers("/api/v1/order-invoices/**").permitAll()
                                .requestMatchers("/api/v1/transactions/**").permitAll()
                                .requestMatchers("/api/v1/commission-histories-details/**").permitAll()
                                .requestMatchers("/api/v1/banks/**").permitAll()
                                .requestMatchers("/api/v1/misa-products/**").permitAll()
                                .requestMatchers("/api/v1/reports/**").permitAll()
=======
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
<<<<<<< HEAD

=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
