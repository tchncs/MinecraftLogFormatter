package de.agent94ger.minecraft.LogFormatter;

import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.FileWriter;
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
    
    // private static FileReader _input;
    // private static FileWriter _output;
    
    public static void main(String[] args) throws IOException
    {
        if (args.length > 0)
        {
            _file = args[0];
        }
        else
        {
            System.out.print("Enter file location:" + System.lineSeparator());
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    System.in));
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
    
    private static void parseLog(String filename) throws IOException
    {
        Path path = Paths.get(filename);
        try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name()))
        {
            while (scanner.hasNextLine())
            {
                // process each line in some way
                System.out.println(scanner.nextLine());
            }
        }
        catch (Exception e) {
            System.out.println("Error: File not found, please use absolute path");
            System.exit(1);
        }
    }
}
