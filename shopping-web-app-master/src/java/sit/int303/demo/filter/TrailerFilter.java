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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author bas
 */
public class TrailerFilter implements Filter {

  private String message;
  private FilterConfig config;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.message = filterConfig.getInitParameter("message");
    this.config = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    MyResponse tempResponse = new MyResponse((HttpServletResponse) response);
    chain.doFilter(request, tempResponse);
    
    PrintWriter out = response.getWriter();
    
    String responseString = tempResponse.toString();
    int index = responseString.indexOf("</body>");
    if (index > -1) {
      StringBuilder finalString = new StringBuilder(responseString.substring(0, index));
      finalString.append("<hr />");
      finalString.append("<p>");
      finalString.append(message);
      finalString.append("</p>");
      finalString.append("</body>");
      finalString.append("</html>");
      
      out.write(finalString.toString());
    } else {
      out.write(responseString);
    }
    out.close();
  }

  @Override
  public void destroy() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}

class MyResponse extends HttpServletResponseWrapper {
  
  StringWriter response;
  public MyResponse(HttpServletResponse response) {
    super(response);
    this.response = new StringWriter();
  }

  @Override
  public String toString() {
    return response.toString();
  }
  
  public PrintWriter getWriter() {
    return new PrintWriter(response);
  }
  
}