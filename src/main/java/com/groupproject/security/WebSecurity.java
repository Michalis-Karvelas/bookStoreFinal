package com.groupproject.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.groupproject.services.UserDetailsServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.groupproject.constants.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, "/api/author/getall").permitAll()
                .antMatchers(HttpMethod.GET, "/api/author/getbyid/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/category/getall").permitAll()
                .antMatchers(HttpMethod.GET, "/api/category/getbyid/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/language/getall").permitAll()
                .antMatchers(HttpMethod.GET, "/api/language/getbyid/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/book/getbyid/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/purchasehistory/new").permitAll()
                //                .antMatchers("/api/**").hasRole("User")
                .antMatchers(HttpMethod.GET, "/api/account/getaccountidbyusername/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/api/account/getbyid/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/api/account/update/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/order/new").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/api/order/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/api/orderdetails/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/orderdetails/new").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "api/account/getaccountidbyusername/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/book/checkownership/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/api/receipt/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/api/**").access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        List<String> list = new ArrayList<String>();
        list.add("*");
        List<String> list1 = new ArrayList<String>();
        list1.add("HEAD");
        list1.add("GET");
        list1.add("POST");
        list1.add("PUT");
        list1.add("DELETE");
        list1.add("PATCH");
        List<String> list2 = new ArrayList<String>();
        list2.add("Authorization");
        list2.add("Cache-Control");
        list2.add("Content-Type");
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(list);
        configuration.setAllowedMethods(list1);
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(list2);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
