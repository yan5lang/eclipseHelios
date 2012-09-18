/**
 * %document warehouse%
 * %2.0%
 */
package com.morningstar.documentwarehouse.api;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;

public class Util {
    
    public static String GenerateSHA1HashCode(File file) throws Exception {
   	
		  MessageDigest md = MessageDigest.getInstance("SHA"); // SHA or MD5
		  String hash = "";
		
		  byte[] data = new byte[(int)file.length()];
		  FileInputStream fis = new FileInputStream(file);
		  fis.read(data);
		  fis.close();
		
		  md.update(data); // Reads it all at one go. Might be better to chunk it.
		
		  byte[] digest = md.digest();
		
		  for (int i = 0; i < digest.length; i++)
		  {
		    String hex = Integer.toHexString(digest[i]);
		    if (hex.length() == 1) hex = "0" + hex;
		    hex = hex.substring(hex.length() - 2);
		    hash += hex;
		  }
		
		  return hash; 
    } 
    
    public static String GenerateSHA1HashCode(String filePath) throws Exception 
    {
    	File file = new File(filePath);    	
    	return GenerateSHA1HashCode(file); 
    }    
    
    public static byte[] readBytesFromFile(String filePath) throws Exception {
    	
    	File file = new File(filePath);
    	if(!file.exists()){
    		throw new FileNotFoundException();
    	}
	
		  byte[] data = new byte[(int)file.length()];
		  FileInputStream fis = new FileInputStream(file);
		  fis.read(data);
		  fis.close();
		  return data; 
    }
    
    public static byte[] readBytesFromFile(File file, int offset, int length) throws Exception {    	

		  byte[] data = new byte[length];
		  FileInputStream fis = new FileInputStream(file);
		  fis.read(data, offset, length);
		  fis.close();
		  return data; 
    }
    
	public static void main(String[] args)
	{
		try {
			File file = new File("D:\\YourFolder\\YourTestFile.pdf");
			System.out.println(Util.GenerateSHA1HashCode(file));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
    
}
