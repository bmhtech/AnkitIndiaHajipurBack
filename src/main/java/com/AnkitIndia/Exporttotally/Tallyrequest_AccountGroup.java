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

public class Tallyrequest_AccountGroup 
{


	
	public String CreateRequest(String groupname,String type)
    {             
    
			String TXML = null;
			
			TXML = "<ENVELOPE>\n" +
					"<HEADER>\n" +
					"<TALLYREQUEST>Import Data</TALLYREQUEST>\n" +
					"</HEADER>\n" +
					"<BODY>\n" +
					"<IMPORTDATA>\n" +
					"<REQUESTDESC>\n" +
					"<REPORTNAME>All Masters</REPORTNAME>\n" +
					"    <STATICVARIABLES>\n" +
		              "     <SVCURRENTCOMPANY>ANKIT INDIA LIMITED</SVCURRENTCOMPANY>\n" +
		              "    </STATICVARIABLES>\n" +
					"</REQUESTDESC>\n" +
					"<REQUESTDATA>\n" +
					"<TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
				"<LEDGER ACTION=\"Create\">\n" +
		             "<LEDGER NAME=\""+groupname+"\""+" Action = \"Alter\">\n"+
					"<NAME.LIST>\n" +
					"<NAME>"+groupname+"</NAME>\n" +
					
					/*"</NAME.LIST>\n" +
					"<ADDRESS.LIST>\n"+
					"<ADDRESS>"+address+"</ADDRESS>\n"+
					"</ADDRESS.LIST>\n"+
					
					
                     "<MAILINGNAME.LIST>\n"+
				     "<MAILINGNAME>"+printtoname+"</MAILINGNAME>\n"+
				     "</MAILINGNAME.LIST>\n"+
					
					"<PRIORSTATENAME>"+statename+"</PRIORSTATENAME>\n"+
					"<PINCODE>"+pincode+"</PINCODE>\n"+
					"<INCOMETAXNUMBER>"+panno+"</INCOMETAXNUMBER>\n"+
					"<COUNTRYNAME>"+"India"+"</COUNTRYNAME>\n"+
					"<GSTREGISTRATIONTYPE>"+registered+"</GSTREGISTRATIONTYPE>\n"+
					"<PARTYGSTIN>"+gstno+"</PARTYGSTIN>\n"+
					*/
					"<PARENT>"+type+"</PARENT>\n" +
					
					
					"<COUNTRYOFRESIDENCE>"+"India"+"</COUNTRYOFRESIDENCE>\n"+
				 /*	
					"<LEDGERMOBILE>"+mobileno+"</LEDGERMOBILE>\n"+
					"<LEDSTATENAME>"+statename+"</LEDSTATENAME>\n" +
					"<ISBILLWISEON>Yes</ISBILLWISEON>\n" +
					"<OPENINGBALANCE>"+"0.00"+"</OPENINGBALANCE>\n" +
					"<PAYMENTDETAILS.LIST>\n"+
					"<IFSCODE>"+ifsccode+"</IFSCODE>\n"+
					"<BANKNAME>"+bankname+"</BANKNAME>\n"+
					"<ACCOUNTNUMBER>"+accountno+"</ACCOUNTNUMBER>\n"+
					*/
					"<PAYMENTFAVOURING>"+groupname+"</PAYMENTFAVOURING>\n"+
					"<TRANSACTIONNAME>"+"Primary"+"</TRANSACTIONNAME>\n"+
					"<SETASDEFAULT>"+"No"+"</SETASDEFAULT>\n"+
				//	"<DEFAULTTRANSACTIONTYPE>"+"Inter Bank Transfer"+"</DEFAULTTRANSACTIONTYPE>\n"+
					"<BENEFICIARYCODEDETAILS.LIST></BENEFICIARYCODEDETAILS.LIST>\n"+
					"</PAYMENTDETAILS.LIST>\n"+
					"</LEDGER>\n" +
					"</TALLYMESSAGE>\n" +
					"</REQUESTDATA>\n" +
					"</IMPORTDATA>\n" +
					"</BODY>\n" +
					"</ENVELOPE>";
			
			 System.out.println(TXML);
			 return TXML;
    }
   
    public String SendToTally(String groupname,String type) throws Exception{
    	
    	
       
       String Url = "http://192.168.10.100:9000/";     
    	
        String SOAPAction = "";
       
        String Voucher = this.CreateRequest(groupname,type);

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
        
        String y=getvalue(x,"CREATED");
        String z=getvalue(x,"ALTERED");
        
        return x+"||"+y+"||"+z;
    }
    
    public String getvalue(String buffer, String tagname) 
    {   
        String startTag, endTag,elementdata = null;
        int startposition,endposition;
        try 
        {
            startTag     = "<"+ tagname + ">";
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

// do not allow other threads to read from the
// input or write to the output while copying is
// taking place

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
    
    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
           fis = new FileInputStream(fileName);
           prop = new Properties();
           prop.load(fis);
        } catch(FileNotFoundException fnfe) {
           fnfe.printStackTrace();
        } catch(IOException ioe) {
           ioe.printStackTrace();
        } finally {
           fis.close();
        }
        return prop;
     }
}
