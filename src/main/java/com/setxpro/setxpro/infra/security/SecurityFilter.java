package com.setxpro.setxpro.infra.security;

import com.setxpro.setxpro.domain.user.UserRepository;
import com.setxpro.setxpro.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        // have token
        if(token != null) {
            // Validation
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);

            // verifications of the user
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            // Save token in context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // token is null ?  next filter
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
