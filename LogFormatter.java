package de.agent94ger.minecraft.LogFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple cli created to compile Minecraft-serverlogs into HTML-files.
 * 
 * @author agent94ger
 * @version Beta-1.1
 */
public class LogFormatter
{
    private static String _file;
    private static ArrayList<LogPage> _pages;
    
    public static void main(String[] args)
    {
        _pages = new ArrayList<LogPage>();
        
        // Get file location via args or input
        _file = initFile(args);
        
        // Parse the log
        parseLog(_file);
        
        _pages.get(0).setAsFirstPage();
        
        _pages.get(_pages.size()-1).setAsLastPage();
        
        for (LogPage page : _pages)
        {
            page.print();
        }
        
        System.out.println("Done.");
    }
    
    /**
     * Initializes file location
     * 
     * @param args Array of args
     * @return File location
     */
    private static String initFile(String[] args)
    {
        String file = "";
        
        if (args.length > 0)
        {
            file = args[0];
        }
        else
        {
            System.out.print("Enter file location:" + System.lineSeparator());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                file = br.readLine();
            }
            catch (IOException ioe)
            {
                System.out.println("IO error trying to read the filename");
                System.exit(1);
            }
        }
        
        return file;
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
            String output = "";
            int linesPerPageInit = 100;
            int linesPerPage = linesPerPageInit;
            int lines = 0;
            int pageNo = 0;
            
            while (scanner.hasNextLine())
            {
                if (lines < linesPerPage)
                {
                    lines++;
                }
                else
                {
                    _pages.add(new LogPage(pageNo, output));
                    pageNo++;
                    output = "";
                    lines = 0;
                    linesPerPage = linesPerPageInit-1;
                }
                
                String line = scanner.nextLine();
                line = parseLine(line);
                output += line + System.lineSeparator();
            }
            
            if (!output.equals(""))
            {
                _pages.add(new LogPage(pageNo, output));                
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
        
        // Add colors and new lines
        if (line.contains("WARN"))
        {
            line = "<span style=\"color:#FF0000\">" + line + "</span><br />";
        }
        else if (line.contains("INFO"))
        {
            line = "<span style=\"color:#FFFFFF\">" + line + "</span><br />";
        }
        else
        {
            line = "<span style=\"color:#FF0000\">" + line + "</span><br />";
        }
        
        return line;
    }
}
