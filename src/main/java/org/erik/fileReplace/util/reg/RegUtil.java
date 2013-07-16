/**
 * Yolema.com Inc.
 * Copyright (c) 2011-2013 All Rights Reserved.
 */
package org.erik.fileReplace.util.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author caiwd
 * @version $Id: RegUtil.java, v 0.1 2013-4-1 上午11:34:16 caiwd Exp $
 */
public class RegUtil {

    /**
     * @author caiwd
     * @param string
     * @param content 
     * @param regIterator
     */
    public static String iterating(String reg, String content, RegIterator regIterator) {
        
        Pattern pattern = Pattern.compile(reg);
        
        StringBuffer fiterString = new StringBuffer();
        
        Matcher m = pattern.matcher(content);
        int i = 0;
        while (m.find()) {
            String match = m.group();
            String result = regIterator.doOne(match);
            if(!match.equals(result)){
                i++;
            }
            result = result.replaceAll("\\$", "\\\\\\$");
            m.appendReplacement(fiterString,result);
            
        }
        m.appendTail(fiterString);
        if(i>0){
            System.out.println("     ┌──── 该文件替换了"+i+"处。");
        }
        return fiterString.toString();
    }
}
