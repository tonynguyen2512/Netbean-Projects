/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.function;

/**
 *
 * @author bas
 */
public class AddFunction {
  private static final String IMAGE_PATH = "model-images/";
  
  public static String getImageFileName(String productLine, String productcode) {
    int cutPos = productLine.indexOf(" ");
    String productLinePath = cutPos > 0 ? productLine.substring(0, cutPos) : productLine;
    return String.format("%s%s/%s.jpg", IMAGE_PATH, productLinePath.toLowerCase(), productcode);
  }
          
  public static int addMethod(String x, String y) {
    try {
      return Integer.valueOf(x) + Integer.valueOf(y);
    } catch (NumberFormatException ne) {
      return Integer.MIN_VALUE;
    }
  }
  
}
