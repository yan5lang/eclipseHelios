package com.morningstar.documentwarehouse.api;
import java.net.*;
import java.io.*;

public class CookieManagement {
	
	//cookie string sample: Set-Cookie: cookie-value; expires=date; path=path; domain=domain-name; secure
	//MorningstarDocumentWarehouseCookie=D421ACB10AB50E794D71163BEE; expires=Sat, 06-Feb-2010 08:58:42 GMT; path=/; HttpOnly, SessionHeader=9a5ec75e0d46a33a69388c95416382d; expires=Mon, 20-Jan-2020 10:18:42 GMT; path=/
    private static final String SET_COOKIE = "Set-Cookie";    

    private URLConnection conn = null;

    public CookieManagement(URLConnection conn) {
    	this.conn = conn;
    }
    
    public String[] getCookieStrings()
    {
    	String cookieString="";
    	String headerName = null;
		for (int i=1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {			
		    if (headerName.equalsIgnoreCase(SET_COOKIE)) {		    	
		    	cookieString += conn.getHeaderField(i) + "~~~";		    	
		    }
		}
		if(cookieString.length()>0)
		{
			return cookieString.split("~~~");			
		}
		else
		{
			return null;
		}		
    }

    
    public static void main(String[] args)
    { 
		try {
			String loginUrl = "http://dev.datamanager.morningstar.com/DocumentWarehouse/login.aspx";
			java.net.URLConnection conn = new java.net.URL(loginUrl).openConnection();
			HttpURLConnection httpConn = (HttpURLConnection)conn;
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setInstanceFollowRedirects(false);
			
			byte[] inputByte = "email=MagicDoor@morningstar.com&password=It'sM@g!c".getBytes("utf-8");
	        httpConn.addRequestProperty("content-length", String.valueOf(inputByte.length));
			OutputStream outputStream = httpConn.getOutputStream();		
			outputStream.write(inputByte);			
			//get response string
			InputStream stream = httpConn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			/*
			String line;
			while((line = reader.readLine())!=null)
			{				
	            //System.out.println(line);
			}
			*/
			
			CookieManagement cm = new CookieManagement(httpConn);
			String[] cookieStrings = cm.getCookieStrings();
			if(cookieStrings!=null)
			for(int i = 0; i< cookieStrings.length; i++)
			{
				System.out.println(cookieStrings[i]);
			}
			
			/*
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			Iterator it = headers.entrySet().iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
			
	    	CookieManagement cm = new CookieManagement(httpConn);
			Map cookies = cm.getCookies();
			cm.setCookies(cookies);
			*/
	
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		}
    }
}
