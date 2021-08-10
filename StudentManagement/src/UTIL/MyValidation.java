
package UTIL;

public class MyValidation {
    public static boolean checkString(String s, String pattern) {
        if (s.matches(pattern)) return true;
        return false;
    }
    
    public static boolean checkNumber(int number, int min, int max) {
        if (number >= min && number <= max) return true;
        return false;
    }
}
