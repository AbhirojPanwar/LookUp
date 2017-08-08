package io.github.abhiroj.lookup.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abhiroj on 31/7/17.
 */

public class Utility {

    public static boolean isWord(String text)
    {
        // TODO: Check if its a word

        char c[]=text.toCharArray();

        for (int i=0;i<c.length;i++)
        {
            if(getASCII(c[i])<97 || getASCII(c[i])>=122)
                return false;
        }

        return true;
    }

    private static int getASCII(char c) {

        return (int) c;

    }

}
