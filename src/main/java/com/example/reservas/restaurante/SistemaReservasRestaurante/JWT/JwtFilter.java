package com.example.reservas.restaurante.SistemaReservasRestaurante.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.PasswordAuthentication;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String autheader = request.getHeader("Authorization");

        if (autheader == null || !autheader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }


        String token = autheader.substring(7);
        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            var userDetail = this.userService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token)){
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetail,
                        null,
                        userDetail.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);


            }
        }

        filterChain.doFilter(request, response);
    }
}
