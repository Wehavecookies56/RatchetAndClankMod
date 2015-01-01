package org.jglrxavpok.glutils;

public class MtlMaterialLib
{

    public static final String COMMENT = "#";
    public static final String NEW_MATERIAL = "newmtl ";
    
    public void parse(String content)
    {
        String[] lines = content.split("\n");
        for(int i = 0;i<lines.length;i++)
        {
            String line = lines[i].trim();
            if(line.startsWith(COMMENT))
            {
                ;
            }
            else if(line.startsWith(NEW_MATERIAL))
            {
                
            }
        }        
    }

}
