package com.FoodCompanion.REST.filter;

import com.FoodCompanion.REST.constant.SecurityConstant;
import com.FoodCompanion.REST.utility.JWTTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private JWTTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            if(httpServletRequest.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)){
                httpServletResponse.setStatus(HttpStatus.OK.value());
            }else
            {
                String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
                if(authHeader == null || authHeader.startsWith(SecurityConstant.TOKEN_HEADER))
                {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                    return;
                }
                String token = authHeader.substring(SecurityConstant.TOKEN_HEADER.length());
                String username = jwtTokenProvider.getSubject(token);
                    if(jwtTokenProvider.isTokenValid(username,token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                        List<GrantedAuthority> authorities = jwtTokenProvider.getAuth(token);
                        Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, httpServletRequest);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }else {
                    SecurityContextHolder.clearContext();
                    }
                }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
    }

