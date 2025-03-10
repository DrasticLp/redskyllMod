package com.drastic.redskyll.util.helpers;

import java.io.IOException;
import java.io.Reader;

public class IOHelper
{
    public static String readAll(Reader rd) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1)
        {
            sb.append((char)cp);
        }
        return sb.toString();
    }
}
