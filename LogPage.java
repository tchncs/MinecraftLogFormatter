package de.agent94ger.minecraft.LogFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogPage
{
    private int     _pageNo;
    private String  _htmlBegin;
    private String  _htmlContent;
    private String  _htmlEnd;
    private String  _htmlLinkBegin;
    private String  _htmlPrevEnd;
    private String  _htmlNextEnd;
    private boolean _hasPrev;
    private boolean _hasNext;
    
    public LogPage(int pageNo, String lines)
    {
        _pageNo = pageNo;
        
        _htmlContent = lines;
        
        _htmlBegin  = "<html>" + System.lineSeparator();
        _htmlBegin += "<head>" + System.lineSeparator();
        _htmlBegin += "<title>Minecraft log</title>" + System.lineSeparator();
        _htmlBegin += "</head>" + System.lineSeparator();
        _htmlBegin += "<body bgcolor=#000000>" + System.lineSeparator();
        _htmlBegin += "<style type=\"text/css\">" + System.lineSeparator();
        _htmlBegin += "a:link { color:#FFFFFF; }" + System.lineSeparator();
        _htmlBegin += "a:visited { color:#FFFFFF; }" + System.lineSeparator();
        _htmlBegin += "a:hover { color:#FFFFFF; }" + System.lineSeparator();
        _htmlBegin += "a:active { color:#FFFFFF; }" + System.lineSeparator();
        _htmlBegin += "a:focus { color:#FFFFFF; }" + System.lineSeparator();
        _htmlBegin += "</style>" + System.lineSeparator();
        _htmlBegin += "<p style=\"color:#FFFFFF\">Formatted log</p>" + System.lineSeparator();

        _htmlEnd  = "</body>" + System.lineSeparator();
        _htmlEnd += "</html>" + System.lineSeparator();
        
        _htmlLinkBegin  = "<a href=./page";
        
        _htmlPrevEnd  = ".html>Previous page</a>";
        
        _htmlNextEnd  = ".html>Next page</a>";

        _hasPrev = true;
        _hasNext = true;
    }
    
    public void setAsFirstPage()
    {
        _hasPrev = false;
    }
    
    public void setAsLastPage()
    {
        _hasNext = false;
    }
    
    public void print()
    {
        String fileContent;
        
        fileContent = _htmlBegin;
        fileContent += _htmlContent;
        if (_hasPrev || _hasNext)
        {
            fileContent += "<br />" + System.lineSeparator();
        }
        if (_hasPrev)
        {
            fileContent += _htmlLinkBegin;
            fileContent += _pageNo - 1;
            fileContent += _htmlPrevEnd;
        }
        if (_hasPrev && _hasNext)
        {
            fileContent += " - ";
        }
        if (_hasNext)
        {
            fileContent += _htmlLinkBegin;
            fileContent += _pageNo + 1;
            fileContent += _htmlNextEnd;
        }
        if (_hasPrev || _hasNext)
        {
            fileContent += System.lineSeparator();
        }
        fileContent += _htmlEnd;
        
        System.out.println("Writing file number " + (_pageNo + 1) + "...");
        
        Path filepath = Paths.get("./page" + _pageNo + ".html");
        
        try
        {
            Files.write(filepath, fileContent.getBytes());
        }
        catch (IOException e)
        {
            System.out.println("Error writing file.");
        }
    }
}
