package de.agent94ger.minecraft.LogFormatter;

public class LogPage
{
    private int _pageNo;
    private String _htmlBegin;
    private String _htmlContent;
    private String _htmlEnd;
    private String _htmlLinkBegin;
    private String _htmlPrevEnd;
    private String _htmlNextEnd;
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
        System.out.println(_htmlBegin);
        System.out.println(_htmlContent);
        if (_hasPrev)
        {
            System.out.print(_htmlLinkBegin);
            System.out.print(_pageNo-1);
            System.out.print(_htmlPrevEnd);
        }
        if (_hasPrev && _hasNext)
        {
            System.out.print(" - ");
        }
        if (_hasNext)
        {
            System.out.print(_htmlLinkBegin);
            System.out.print(_pageNo+1);
            System.out.print(_htmlNextEnd);
        }
        if (_hasPrev || _hasNext)
        {
            System.out.println();
        }
        System.out.println(_htmlEnd);
    }
}
