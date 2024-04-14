package com.AnkitIndia.jwtauthentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;

import com.AnkitIndia.jwtauthentication.security.jwt.JwtAuthEntryPoint;
import com.AnkitIndia.jwtauthentication.security.jwt.JwtAuthTokenFilter;
import com.AnkitIndia.jwtauthentication.security.jwt.JwtProvider;
import com.AnkitIndia.jwtauthentication.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
    
    @Autowired
    private JwtProvider tokenProvider;
    
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(tokenProvider);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                //.antMatchers("/AnkitIndia/**").permitAll()
                
                .antMatchers(HttpMethod.POST,"/AnkitIndiaHajipur/signin").permitAll()
                .antMatchers(HttpMethod.POST,"/AnkitIndiaHajipur/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/AnkitIndiaHajipur/refreshToken").permitAll()
                
                //.antMatchers(HttpMethod.POST,"/AnkitIndia/signin").permitAll()
                //.antMatchers(HttpMethod.POST,"/AnkitIndia/signup").permitAll()
                //.antMatchers(HttpMethod.POST,"/AnkitIndia/refreshToken").permitAll()
                
               
                
               // .antMatchers(HttpMethod.POST,"/AnkitIndiaTesting/signin").permitAll()
              //  .antMatchers(HttpMethod.POST,"/AnkitIndiaTesting/signup").permitAll()
              //  .antMatchers(HttpMethod.POST,"/AnkitIndiaTesting/refreshToken").permitAll()
                
                
                /*.antMatchers(HttpMethod.POST,"/MeghnaBidi/signin").permitAll()
                .antMatchers(HttpMethod.POST,"/MeghnaBidi/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/MeghnaBidi/refreshToken").permitAll()*/
                
                /*.antMatchers(HttpMethod.POST,"/MeghanaPanMasala/signin").permitAll()
                .antMatchers(HttpMethod.POST,"/MeghanaPanMasala/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/MeghanaPanMasala/refreshToken").permitAll()*/
                
                /*.antMatchers(HttpMethod.POST,"/IndianSpices/signin").permitAll()
                .antMatchers(HttpMethod.POST,"/IndianSpices/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/IndianSpices/refreshToken").permitAll()*/
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}