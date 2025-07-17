package telran.java58.security.accounting;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import telran.java58.accounting.Roles;
import telran.java58.accounting.dao.UserAccountRepository;
import telran.java58.accounting.model.UserAccount;

import java.io.IOException;

@Component
@Order(30)
@RequiredArgsConstructor
public class UpdateDeleteUserFilter implements Filter {
    private final UserAccountRepository accountRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!request.getServletPath().matches("/account/user/\\w+")) {
            filterChain.doFilter(request, response);
            return;
        }
        String userForUpdate = request.getServletPath().split("/")[3];
        String login = request.getUserPrincipal().getName();
        if (isUpdate(request.getMethod(), request.getServletPath())) {
            if (!userForUpdate.equalsIgnoreCase(login)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        if (isDelete(request.getMethod(), request.getServletPath())) {
            UserAccount user = accountRepository.findById(login).orElseThrow();
            if (!userForUpdate.equalsIgnoreCase(login) && !user.getRoles().contains(Roles.ADMINISTRATOR)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    private boolean isUpdate(String method, String servletPath) {
        return servletPath.matches("/account/user/\\w+") && HttpMethod.PATCH.matches(method);
    }

    private boolean isDelete(String method, String servletPath) {
        return servletPath.matches("/account/user/\\w+") && HttpMethod.DELETE.matches(method);
    }
}
