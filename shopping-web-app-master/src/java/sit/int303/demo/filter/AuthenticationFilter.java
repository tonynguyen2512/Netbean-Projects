/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bas
 */
public class AuthenticationFilter implements Filter {

  private FilterConfig config;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.config = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    
    if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession(false).getAttribute("user") != null) {
      chain.doFilter(request, response);
    } else {
      String target = httpServletRequest.getRequestURI(); // เอาตั้งแต่ชื่อแอพ /demo/page..
      target = target.substring(target.indexOf("/", 2));
      config.getServletContext().getRequestDispatcher("/Login?targetUrl=" + target).forward(request, response);
    }
  }

  @Override
  public void destroy() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
