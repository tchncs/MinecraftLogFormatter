package de.agent94ger.minecraft.LogFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A simple cli created to compile Minecraft-serverlogs into HTML-files.
 * 
 * @author agent94ger
 * @version Beta-1.0
 */
public class LogFormatter
{
    private static String _file;
    
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
        parseLog(_file);
    }
    
    /**
     * Reads the log line by line and calls the line parser
     * 
     * @param filename The complete path to the wanted log
     */
    private static void parseLog(String filename)
    {
        Path path = Paths.get(filename);
        try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name()))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                line = parseLine(line);
                System.out.println(line);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: File not found, please use absolute path");
            System.exit(1);
        }
    }
    
    /**
     * Parses the given line
     * 
     * @param line The line to parse
     */
    private static String parseLine(String line)
    {
        // Replace special characters
        line = line.replace("&", "&amp;");
        line = line.replace("<", "&lt;");
        line = line.replace(">", "&gt;");
        line = line.replace("ä", "&auml;");
        line = line.replace("Ä", "&Auml;");
        line = line.replace("ö", "&ouml;");
        line = line.replace("Ö", "&Ouml;");
        line = line.replace("ü", "&uuml;");
        line = line.replace("Ü", "&Uuml;");
        line = line.replace("ß", "&szlig;");
        
        // TODO: Add colors
        
        return line;
    }
}
