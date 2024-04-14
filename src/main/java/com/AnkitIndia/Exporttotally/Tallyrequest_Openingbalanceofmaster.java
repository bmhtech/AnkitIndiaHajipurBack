package com.AnkitIndia.Exporttotally;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Tallyrequest_Openingbalanceofmaster {
	
	public String CreateRequest(String name)
    {             
		
           String TXML = null;
			TXML = "<ENVELOPE>\r\n" + 
					"    <HEADER>\r\n" + 
					"        <VERSION>1</VERSION>\r\n" + 
					"        <TALLYREQUEST>EXPORT</TALLYREQUEST>\r\n" + 
					"        <TYPE>OBJECT</TYPE>\r\n" + 
					"        <SUBTYPE>Ledger</SUBTYPE>\r\n" + 
					"        <ID TYPE=\"Name\">"+name+"</ID></HEADER>\r\n" + 
					"    <BODY>\r\n" + 
					"        <DESC>\r\n" + 
					"            <STATICVARIABLES>\r\n" + 
					"                <SVCURRENTCOMPANY>ANKIT INDIA LIMITED</SVCURRENTCOMPANY>\r\n" + 
					"                <SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT>\r\n" + 
					"            </STATICVARIABLES>\r\n" + 
					"            <FETCHLIST>\r\n" + 
					"                <FETCH>OPENINGBALANCE</FETCH>\r\n" + 
					"                <FETCH>CLOSINGBALANCE</FETCH>\r\n" + 
					"            </FETCHLIST>\r\n" + 
					"        </DESC>\r\n" + 
					"    </BODY>\r\n" + 
					"</ENVELOPE>";
			System.out.println("TXML "+ TXML);
			 return TXML;
    }
	
	
public String SendToTally(String name) throws Exception{
    
       
       String Url = "http://192.168.10.100:9000/";     
    	 
    
        String SOAPAction = "";
       
        String Voucher = this.CreateRequest(name);

        // Create the connection where we're going to send the file.
        URL url = new URL(Url);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) connection;

       
        ByteArrayInputStream bin = new ByteArrayInputStream(Voucher.getBytes());
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        // Copy the SOAP file to the open connection.
       
        copy(bin, bout);     

        byte[] b = bout.toByteArray();

        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        // Everything's set up; send the XML that was read in to b.
        OutputStream out = httpConn.getOutputStream();
        out.write(b);
        out.close();

        // Read the response and write it to standard out.

        InputStreamReader isr =
                new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);

        String inputLine;
        String x="";


        while ((inputLine = in.readLine()) != null) {
        	 x=x+inputLine;
            System.out.println("here :: "+inputLine);
        }

        in.close();
        
        String y=getvalue(x,"OPENINGBALANCE");
        //String z=getvalue(x,"ALTERED");
        
        System.out.println("Print Value= "+y);
        
        return y;
    }

public String getvalue(String buffer, String tagname) 
{   
    String startTag, endTag,elementdata = null;
    int startposition,endposition;
    try 
    {
        startTag     = "<"+ "OPENINGBALANCE TYPE=\"Amount\"" + ">";
        endTag       = "</"+ tagname + ">";
        startposition         = buffer.indexOf(startTag);       
        startposition         = startposition + startTag.length();
        endposition      = buffer.indexOf(endTag); 
        elementdata =  buffer.substring(startposition, endposition);
    }
    catch(Exception e){}
    System.out.println("elementdata: "+elementdata);
    return elementdata;
}

public static void copy(InputStream in, OutputStream out)
        throws IOException {

//do not allow other threads to read from the
//input or write to the output while copying is
//taking place

    synchronized (in) {
        synchronized (out) {

            byte[] buffer = new byte[256];
            while (true) {
                int bytesRead = in.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}



}
