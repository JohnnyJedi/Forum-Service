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
import telran.java58.security.model.User;

import java.io.IOException;

@Component
@Order(30)
@RequiredArgsConstructor
public class UpdateDeleteUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isUpdate(request.getMethod(), request.getServletPath())) {
            String[] parts = request.getServletPath().split("/");
            String userForUpdate = parts[parts.length - 1];
            User user = (User) request.getUserPrincipal();
            if (!userForUpdate.equalsIgnoreCase(user.getName())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        if (isDelete(request.getMethod(), request.getServletPath())) {
            String userForUpdate = request.getServletPath().split("/")[3];
            String login = request.getUserPrincipal().getName();
            User user = (User) request.getUserPrincipal();
            if (!userForUpdate.equalsIgnoreCase(login) && !user.getRoles().contains(Roles.ADMINISTRATOR.name())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    private boolean isUpdate(String method, String servletPath) {
        return (
                servletPath.matches("/account/user/\\w+") && HttpMethod.PATCH.matches(method) ||
                        servletPath.matches("/forum/post/\\w+") && HttpMethod.POST.matches(method) ||
                        servletPath.matches("/forum/post/\\w+/comment/\\w+") && HttpMethod.PATCH.matches(method)

        );
    }

    private boolean isDelete(String method, String servletPath) {
        return servletPath.matches("/account/user/\\w+") && HttpMethod.DELETE.matches(method);
    }
}
