package telran.java58.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import telran.java58.accounting.dao.UserAccountRepository;
import telran.java58.accounting.model.UserAccount;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PasswordExpirationFilter extends OncePerRequestFilter {
    private final UserAccountRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            String login = authentication.getName();
            UserAccount user = repository.findById(login).orElse(null);
            if(user != null && user.getPasswordExpirationDate().isBefore(LocalDateTime.now()) && !request.getRequestURI().equals("/account/password")){
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"Password expired");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
