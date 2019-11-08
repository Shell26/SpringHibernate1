package ru.shell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.shell.controller.MyAuthenticationSuccesHandler;
import ru.shell.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.shell")
public class SpringConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    MyAuthenticationSuccesHandler myAuthenticationSuccesHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll() // доступ анонимным пользователям
                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")//страница входа
//                .loginProcessingUrl("/")
                .usernameParameter("name")
//                .passwordParameter("password")
//                .successHandler(myAuthenticationSuccesHandler)
//                .loginProcessingUrl("/")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?errorrrrrrrrrrr").permitAll() //При успешном входе пользователь попадет на страницу со списком, при ошибке — останется на странице входа
//            .and()
//                .logout().logoutSuccessUrl("/").permitAll()//При успешном выходе пользователь попадет на главную страницу
            .and()
                .csrf().disable();
    }
}
