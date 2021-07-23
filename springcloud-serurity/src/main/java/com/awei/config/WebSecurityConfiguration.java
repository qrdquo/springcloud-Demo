package com.awei.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalAuthentication
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    //创建密码的加密类
       @Bean
       public PasswordEncoder passwordEncoder(){
           //创建PasswordEncoder的实现类，实现类是加密算法
           return new BCryptPasswordEncoder();
       }

      @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          PasswordEncoder pe = passwordEncoder();
          auth.inMemoryAuthentication()
                .withUser("liyawei")
                .password(pe.encode("123456"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(pe.encode("123456"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //把csrf拦截置为不可用，401问题
        //http.csrf().disable();
    }


}
