package de.saremox.util;

public class HtmlTableFormatter
{
    public static String htmlTable(String[][] data)
    {
        String html = "";
        
        html += createTableHeader();
        for(String[] row : data)
        {
            html += createTableRowHead();
            for(String cell : row)
            {
                html += createTableCell(cell); 
            }
            html += createTableRowFoot();
        }
        html += createTableFooter();
        return html;
    }

    public static String createTableCell(String content)
    {
        return "<td>"+content+"</td>";
    }

    public static String createTableRowHead()
    {
        return "<tr>";
    }

    public static String createTableRowFoot()
    {
        return "</tr>";
    }

    public static String createTableHeader()
    {
        return "<table>";   
    }

    public static String createTableFooter()
    {
        return "</table>";
    }
}
