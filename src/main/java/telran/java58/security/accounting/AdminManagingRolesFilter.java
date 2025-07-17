package telran.java58.security.accounting;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import telran.java58.accounting.Roles;
import telran.java58.accounting.dao.UserAccountRepository;
import telran.java58.accounting.model.UserAccount;

import java.io.IOException;

@Component
@Order(20)
@RequiredArgsConstructor
public class AdminManagingRolesFilter implements Filter {
    private final UserAccountRepository accountRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (checkEndPoint(request.getMethod(), request.getServletPath())) {
            System.out.println("AdminManaging principal = " + request.getUserPrincipal());
            String login = request.getUserPrincipal().getName();
            UserAccount account = accountRepository.findById(login).orElseThrow();
            if (!account.getRoles().contains(Roles.ADMINISTRATOR)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoint(String method, String servletPath) {
        return servletPath.matches("/account/user/\\w+/role/\\w+");
    }
}
