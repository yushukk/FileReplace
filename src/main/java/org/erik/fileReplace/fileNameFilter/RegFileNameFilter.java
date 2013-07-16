/**
 * Yolema.com Inc.
 * Copyright (c) 2011-2013 All Rights Reserved.
 */
package org.erik.fileReplace.fileNameFilter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author caiwd
 * @version $Id: RegFileNameFilter.java, v 0.1 2013-3-31 下午7:52:43 caiwd Exp $
 */
public class RegFileNameFilter implements FilenameFilter{

    private String reg ;
    
    
    
    /**
     * @param reg
     */
    public RegFileNameFilter(String reg) {
        super();
        this.reg = reg;
    }



    /**
     * @author caiwd
     * @param dir
     * @param name
     * @return
     */
    public boolean accept(File dir, String name) {
        
        
        Pattern pattern = Pattern.compile(reg);
        
        Matcher matcher = pattern.matcher(name);
        
        return matcher.find();
    }

    
    
    

}
