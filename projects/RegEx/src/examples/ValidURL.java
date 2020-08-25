package examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidURL {

    public static void main(String[] args) {
        String regex = "^((http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?$";
        String text = "https://stackoverflow.com/questions/26078106/system-console-gives-nullpointerexception-in-netbeans/";
        System.out.println(text.matches(regex));
        Pattern pat = Pattern.compile(regex);
        Matcher matcher = pat.matcher(text);
        System.out.println(matcher.matches());
    }
    
}