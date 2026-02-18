package com.project.Fitness.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtills;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.startsWith("/api/auth");
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwt = parseJwt(request);

            // only attempt auth if token present, valid and no existing auth in context
            if (jwt != null && jwtUtills.validateToken(jwt) &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                String userId = jwtUtills.getUserID(jwt);
                List<String> roles = jwtUtills.getRoles(jwt);

                var authorities = roles.stream()
                        .map(r -> {
                            // ensure Spring-style ROLE_ prefix
                            String role = (r == null) ? "" : (r.startsWith("ROLE_") ? r : "ROLE_" + r);
                            return new SimpleGrantedAuthority(role);
                        })
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userId, null, authorities);

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        } catch (Exception e) {
            // log and continue filter chain - don't throw (so public endpoints remain reachable)
            e.printStackTrace();
        }


        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        return jwtUtills.getJwtFromHeader(request);
    }
}
