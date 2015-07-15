package com.xpath;

import java.io.File;
import org.jdom.JDOMException;

/**
 *
 * @author ZedroS
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JDOMException {
        File entryFile = new File("hopital.xml");
        ExempleXPath exemple = new ExempleXPath();
        exemple.parse(entryFile);
    }
    
}
