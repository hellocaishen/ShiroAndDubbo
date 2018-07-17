package com.wsbg.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FtpClient {
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        if(!createDir(path, ftp)){
	        	throw new RuntimeException("创建目录失败:"+path); 
	        }
	        ftp.changeWorkingDirectory(path);  
	        ftp.storeFile(filename, input);           
	          
	        input.close();  
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	} 
	
	 /** 
     * 创建目录(有则切换目录，没有则创建目录) 
     * @param dir 
     * @return 
     */  
    public static boolean createDir(String dir,FTPClient ftp){  
        if(StringExtend.isNullOrEmpty(dir))  
            return true;  
        String d;  
        try {  
            //目录编码，解决中文路径问题  
            d = new String(dir.toString().getBytes("GBK"),"iso-8859-1");  
            //尝试切入目录  
            if(ftp.changeWorkingDirectory(d))  
                return true;  
            dir = StringExtend.trimStart(dir, "/");  
            dir = StringExtend.trimEnd(dir, "/");  
            String[] arr =  dir.split("/");  
            StringBuffer sbfDir=new StringBuffer();  
            //循环生成子目录  
            for(String s : arr){  
                sbfDir.append("/");  
                sbfDir.append(s);  
                //目录编码，解决中文路径问题  
                d = new String(sbfDir.toString().getBytes("GBK"),"iso-8859-1");  
                //尝试切入目录  
                if(ftp.changeWorkingDirectory(d))  
                    continue;  
                if(!ftp.makeDirectory(d)){  
                    System.out.println("[失败]ftp创建目录："+sbfDir.toString());  
                    return false;  
                }  
                System.out.println("[成功]创建ftp目录："+sbfDir.toString());  
            }  
            //将目录切换至指定路径  
            return ftp.changeWorkingDirectory(d);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
}
