package telran.java58.security.accounting;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import telran.java58.accounting.Roles;
import telran.java58.forum.dao.ForumRepository;
import telran.java58.forum.model.Post;
import telran.java58.security.model.User;

import java.io.IOException;

@Component
@Order(50)
@RequiredArgsConstructor
public class UpdatePostFilter implements Filter {
    private final ForumRepository forumRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (checkEndPoints(request.getMethod(), request.getServletPath())) {
            User user = (User) request.getUserPrincipal();
            String[] parts = request.getServletPath().split("/");
            String postId = parts[parts.length - 1];
            Post post = forumRepository.findById(postId).orElse(null);
            if (post == null ||
                    !(user.getName().equalsIgnoreCase(post.getAuthor()) || user.getRoles().contains(Roles.MODERATOR.name()))) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    private boolean checkEndPoints(String method, String servletPath) {
        return (servletPath.matches("/forum/post/\\w+") && HttpMethod.PATCH.matches(method));
    }

}
