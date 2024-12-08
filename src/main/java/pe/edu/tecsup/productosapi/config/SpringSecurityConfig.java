package pe.edu.tecsup.productosapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.edu.tecsup.productosapi.filters.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Configure authorizations
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/" /*, "/**"*/).permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/api/**").permitAll() // Allow access to /api/profile endpoint

                )
                // Change csrf
                .csrf( (csrf) -> csrf.disable());
        //*/
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    //*/

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter("/api/**");
    }

}
