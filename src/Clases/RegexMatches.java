package Clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    public static void main( String args[] ){

      String line = "-BC";
      String pattern = "^[A-Z0-9]+[A-Z0-9]+[A-Z0-9]$";

      Pattern r = Pattern.compile(pattern);

      Matcher m = r.matcher(line);
      
      if (m.matches()) {
         System.out.println("Sirve");
      } else {
         System.out.println("NO MATCH");
      }
   }
}
