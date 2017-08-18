package com.wanghao.springmvc;

import com.wanghao.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 8/6/17.
 */
public class SessionTimeoutInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(SessionTimeoutInterceptor.class);

    private static final String LOGIN_URL="/user/login";
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        /* @param create	<code>true</code> to create
        *        			a new session for this request if necessary;
        *			        <code>false</code> to return <code>null</code>
        *			        if there's no current session
        *			        */
        HttpSession session = request.getSession(true);
        //session中获取用户信息
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        if (obj == null || "".equals(obj.toString())) {
            logger.info("session已经失效，请求被拦截");
            System.out.println(request.getSession().getServletContext().getContextPath());
            response.sendRedirect(request.getSession().getServletContext().getContextPath()+LOGIN_URL);
            return false;
        }
        return true;
    }
}
