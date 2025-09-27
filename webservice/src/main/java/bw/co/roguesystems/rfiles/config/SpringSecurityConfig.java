package bw.co.roguesystems.rfiles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

//    private final RateLimitingFilter rateLimitFilter;

//    public SpringSecurityConfig(RateLimitingFilter rateLimitFilter) {
//        this.rateLimitFilter = rateLimitFilter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("********************************************");

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                "/swagger-ui/*",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/v3/**",
                                "/authorisation/search/paged",
                                "/swagger-resources/**",
                                "/regulator",
                                "/regulator/{id}",
                                "/regulator/search",
                                "/regulator/paged",
                                "/operation",
                                "/operation/{id}",
                                "/document",
                                "/document/type",
                                "/document/target/**",
                                "/operation/process/by-operation/**",
                                "/operation/process/paged",
                                "/operation/process/*",
                                "/process/{id}",
                                "/actuator/**",
                                "/public/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults()));

        return http.build();
    }
}
