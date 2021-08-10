/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.tag.TransformTag;

import java.io.StringWriter;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author bas
 */
public class TranformTag extends SimpleTagSupport {

  private String color;
  private String mode;

  /**
   * Called by the container to invoke this tag. The implementation of this
   * method is provided by the tag library developer, and handles all tag
   * processing, body iteration, etc.
   */
  @Override
  public void doTag() throws JspException {
    JspWriter out = getJspContext().getOut();
    
    try {
      JspFragment f = getJspBody();
      StringWriter oldBody = new StringWriter();
      String newBody;
      f.invoke(oldBody);
      if (mode.equalsIgnoreCase("upper")) {
        newBody = oldBody.toString().toUpperCase();
      } else if (mode.equalsIgnoreCase("hide")) {
        newBody = "";
      } else {
        newBody = oldBody.toString();
      }
      out.println("<p style='color:" + color + "'>");
      out.println(newBody);
      out.println("</p >");
    } catch (java.io.IOException ex) {
      throw new JspException("Error in TranformTag tag", ex);
    }
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }
  
}
