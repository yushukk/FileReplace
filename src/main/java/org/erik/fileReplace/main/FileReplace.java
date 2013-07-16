/**
 * Yolema.com Inc.
 * Copyright (c) 2011-2013 All Rights Reserved.
 */
package org.erik.fileReplace.main;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.erik.fileReplace.action.ContentAction;
import org.erik.fileReplace.exception.GlobelException;
import org.erik.fileReplace.file.FileOperate;
import org.erik.fileReplace.fileNameFilter.RegFileNameFilter;

/**
 * 
 * @author caiwd
 * @version $Id: FileReplace.java, v 0.1 2013-3-31 下午7:15:50 caiwd Exp $
 */
public class FileReplace {

    /** 文件查找路径 */
    public String        path;

    /** 文件名过滤 */
    public String        fileNameReg;

    /** 内容处理器 */
    public ContentAction contentAction;
    
    private Long doMun = 0L;

    
    public void execute(){
        System.out.println("开始执行文件替换…………");
        try {
            deal(path);
        } catch (GlobelException e) {
            e.printStackTrace();
        }
        System.out.println("文件替换结束。共替换"+doMun+"个文件。");
    }
    
    
    
    
    private void deal(String path) throws GlobelException{
        
        File dir = new File(path);
        
        if(!dir.exists()){
            throw new GlobelException("操作路径<"+path+">不存在或无权限访问！");
        }
        
        if(!dir.isDirectory()){
            throw new GlobelException("操作路径<"+path+">不是文件夹！");
        }
        
        File[] files = dir.listFiles(new FileFilter(){
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        
        for(File file : files){
            deal(file.getPath());
        }
        
        String[] filePaths = dir.list(new RegFileNameFilter(fileNameReg));
        
        for(String filePath:filePaths){
            String realPath = null;
            
            String content = null;
            
            try {
                realPath = dir+File.separator+filePath;
                content = FileOperate.read(realPath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new GlobelException("读取文件<"+realPath+">失败！");
            }
            
            try {
                
                String tempContent = content;
                
                content = contentAction.change(content);
                FileOperate.write(realPath,content);
                
                if(!tempContent.equals(content)){
                    System.out.println("文件：<"+realPath+"> 替换完成！");
                    doMun++;
                }
                
            } catch (IOException e) {
                e.printStackTrace();
                throw new GlobelException("写入文件<"+realPath+">失败！");
            }
        }
    }
    
    /**
     * Getter method for property <tt>path</tt>.
     * 
     * @return property value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter method for property <tt>path</tt>.
     * 
     * @param path value to be assigned to property path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter method for property <tt>fileNameReg</tt>.
     * 
     * @return property value of fileNameReg
     */
    public String getFileNameReg() {
        return fileNameReg;
    }

    /**
     * Setter method for property <tt>fileNameReg</tt>.
     * 
     * @param fileNameReg value to be assigned to property fileNameReg
     */
    public void setFileNameReg(String fileNameReg) {
        this.fileNameReg = fileNameReg;
    }

    /**
     * Getter method for property <tt>contentAction</tt>.
     * 
     * @return property value of contentAction
     */
    public ContentAction getContentAction() {
        return contentAction;
    }

    /**
     * Setter method for property <tt>contentAction</tt>.
     * 
     * @param contentAction value to be assigned to property contentAction
     */
    public void setContentAction(ContentAction contentAction) {
        this.contentAction = contentAction;
    }

}
