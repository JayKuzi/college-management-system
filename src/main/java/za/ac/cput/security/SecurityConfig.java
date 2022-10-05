package za.ac.cput.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
               .password(bCryptPasswordEncoder.encode("721087c4-0ede-407e-8c1f-ac57e531f292"))
               .roles("USER")
               .build()
        );

        manager.createUser(User.withUsername("admin")
               .password(bCryptPasswordEncoder.encode("721087c4-0ede-407e-8c1f-ac57e531f293"))
               .roles("ADMIN")
               .build()
        );

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.httpBasic()
            .and().csrf().disable().formLogin().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "college-management-system/**/create").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "college-management-system/**/delete").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.GET, "college-management-system/**/read").hasAnyRole("USER")
            .antMatchers(HttpMethod.GET, "college-management-system/**/find-all").hasAnyRole("USER")
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}