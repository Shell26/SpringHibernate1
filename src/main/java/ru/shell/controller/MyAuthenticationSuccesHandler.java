package ru.shell.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.shell.model.User;

import javax.jws.HandlerChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyAuthenticationSuccesHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        httpServletResponse.sendRedirect("/");
    }
}
