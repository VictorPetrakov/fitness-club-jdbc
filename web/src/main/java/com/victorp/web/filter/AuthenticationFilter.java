package com.victorp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "AuthFilter", urlPatterns = "/contact.html", initParams = {@WebInitParam(name = AuthenticationFilter.IS_ACTIVE_FILTER_PARAM, value = "true", description = "activation of this filter")})
public class AuthenticationFilter implements Filter {

    public static final String USER_ID_PARAM = "userId";
    public static final String IS_ACTIVE_FILTER_PARAM = "isActive";
    public static final String STATUS = "status";

    private boolean active;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        final String isActiveString = filterConfig.getInitParameter(IS_ACTIVE_FILTER_PARAM);
        active = isActiveString == null || isActiveString.toLowerCase().equals("true");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req =(HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        if (!active || hasValidSession(req)) {
            chain.doFilter(request,res);
        }else{
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }

    @Override
    public void destroy() {

    }

    private boolean hasValidSession(HttpServletRequest request){
        final HttpSession session = request.getSession();
        return session != null && session.getAttribute(USER_ID_PARAM) != null;
    }
}
