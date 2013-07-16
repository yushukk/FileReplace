/**
 * Yolema.com Inc.
 * Copyright (c) 2011-2013 All Rights Reserved.
 */
package org.erik.fileReplace.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author caiwd
 * @version $Id: FileOperate.java, v 0.1 2013-3-31 下午8:00:32 caiwd Exp $
 */
public class FileOperate {

    public static String read(String path) throws IOException {

        File file = new File(path);
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br.readLine();
        while (temp != null) {
            sb.append(temp + "\r\n");
            temp = br.readLine();
        }
        return sb.toString();
    }

    public static void write(String path, String content) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file, false);
        out.write(content.getBytes("utf-8"));
        out.close();
    }

}
