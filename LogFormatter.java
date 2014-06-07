package de.agent94ger.minecraft.LogFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple cli created to compile Minecraft-serverlogs into HTML-files.
 * 
 * @author agent94ger
 * @version Beta-1.0
 */
public class LogFormatter
{
    private static String     _file;
    
    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            _file = args[0];
        }
        else
        {
            System.out.print("Enter file location:" + System.lineSeparator());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                _file = br.readLine();
            }
            catch (IOException ioe)
            {
                System.out.println("IO error trying to read the filename");
                System.exit(1);
            }
        }
        System.out.println(_file);
    }
}
