/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.filters;

import com.coclear.entitys.User;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pencerval
 */
@WebFilter("/*")
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        FacesContext fc=FacesContext.getCurrentInstance();
        if (session.getAttribute("user") != null && (req.getRequestURI() == null ? req.getContextPath() == null : req.getRequestURI().equals(req.getContextPath()+"/"))) {
            User user=(User) session.getAttribute("user");
            if(user.getIsAdmin()==1 || user.getIsAdmin()==2){
                res.sendRedirect(req.getContextPath() +"/admin/index.xhtml");
            }else{
                res.sendRedirect(req.getContextPath() +"/public/index.xhtml");
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
    
}
