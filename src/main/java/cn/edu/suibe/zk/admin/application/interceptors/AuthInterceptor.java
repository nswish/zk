package cn.edu.suibe.zk.admin.application.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request.getSession().getAttribute("user") == null) {
            try {
                response.sendRedirect("/admin");
                return false;
            } catch (IOException e) {}
        }

        return true;
    }
}
