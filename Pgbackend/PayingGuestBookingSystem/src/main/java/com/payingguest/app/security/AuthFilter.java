package com.payingguest.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getTokenFromRequest(request);
            if (StringUtils.hasText(token)) {
                String email = jwtUtils.getUsernameFromToken(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                    if (jwtUtils.isTokenValid(token, userDetails)) {
                        // 🔍 TEMP DEBUG LOG - You can remove/comment this after confirming it's correct
                        log.info("Setting authentication for user: {}, roles: {}", userDetails.getUsername(), userDetails.getAuthorities());

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        var auth = SecurityContextHolder.getContext().getAuthentication();
                        if (auth != null && auth.isAuthenticated()) {
                            log.info("✅ Authenticated user from context: {}", auth.getName());
                            log.info("✅ Roles from context: {}", auth.getAuthorities());
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
