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

public class Tallyrequest_Suppliermaster {
	
	
	

	
    public String CreateRequest(String suppliername, String alliasname,String address,String statename,String pincode,String panno,String registered,String type,String mobileno,String gstno,String ifsccode,String accountno,String bankname,String website,String email,String phone,String fax,String contactperson,String openingbal)
    {             
    	alliasname="";


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
				
					"<LEDGER NAME=\""+suppliername+"\""+" Action = \"Alter\">\n"+
					"<NAME.LIST>\n" +
					"<NAME>"+suppliername+"</NAME>\n" +
				
					"</NAME.LIST>\n" +
					"<ADDRESS.LIST>\n"+
					"<ADDRESS>"+address+"</ADDRESS>\n"+
					"</ADDRESS.LIST>\n"+
					"<PRIORSTATENAME>"+statename+"</PRIORSTATENAME>\n"+
					"<PINCODE>"+pincode+"</PINCODE>\n"+
			        "<EMAIL>"+email+"</EMAIL>\n"+
			        "<WEBSITE>"+website+"</WEBSITE>\n"+
					"<INCOMETAXNUMBER>"+panno+"</INCOMETAXNUMBER>\n"+
					"<COUNTRYNAME>"+"India"+"</COUNTRYNAME>\n"+
					"<GSTREGISTRATIONTYPE>"+registered+"</GSTREGISTRATIONTYPE>\n"+
					"<PARTYGSTIN>"+gstno+"</PARTYGSTIN>\n"+
					"<PARENT>"+type+"</PARENT>\n" +
					"<COUNTRYOFRESIDENCE>"+"India"+"</COUNTRYOFRESIDENCE>\n"+
				    "<LEDGERPHONE>"+phone+"</LEDGERPHONE>\n" +
			        "<LEDGERFAX>"+fax+"</LEDGERFAX>\n" +
			        "<LEDGERCONTACT>"+contactperson+"</LEDGERCONTACT>\n" +
			
			        "<LEDGERMOBILE>"+mobileno+"</LEDGERMOBILE>\n"+
					"<LEDSTATENAME>"+statename+"</LEDSTATENAME>\n" +
					"<ISBILLWISEON>Yes</ISBILLWISEON>\n" +
					"<OPENINGBALANCE>"+openingbal+"</OPENINGBALANCE>\n" +
					"<PAYMENTDETAILS.LIST>\n"+
					"<IFSCODE>"+ifsccode+"</IFSCODE>\n"+
					"<BANKNAME>"+bankname+"</BANKNAME>\n"+
					"<ACCOUNTNUMBER>"+accountno+"</ACCOUNTNUMBER>\n"+
					"<PAYMENTFAVOURING>"+suppliername+"</PAYMENTFAVOURING>\n"+
					"<TRANSACTIONNAME>"+"Primary"+"</TRANSACTIONNAME>\n"+
					"<SETASDEFAULT>"+"No"+"</SETASDEFAULT>\n"+
					"<DEFAULTTRANSACTIONTYPE>"+"Inter Bank Transfer"+"</DEFAULTTRANSACTIONTYPE>\n"+
					"<BENEFICIARYCODEDETAILS.LIST></BENEFICIARYCODEDETAILS.LIST>\n"+
					"</PAYMENTDETAILS.LIST>\n"+
					"</LEDGER>\n" +
					"</TALLYMESSAGE>\n" +
					"</REQUESTDATA>\n" +
					"</IMPORTDATA>\n" +
					"</BODY>\n" +
					"</ENVELOPE>";
			
			System.out.println("txml : " + TXML);
			 return TXML;
    }
   
    public String SendToTally(String suppliername, String alliasname,String address,String statename,String pincode,String panno,String registered,String type,String mobileno,String gstno,String ifsccode,String accountno,String bankname,String website,String email,String phone,String fax,String contactperson,String openingbal) throws Exception{
    	
    	//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
       
       String Url = "http://192.168.10.100:9000/";
      // String Url = "http://localhost:9000/";
    	//String Url = "http://192.168.1.254:9000/";
        //String Url = prop.getProperty("tallyurl"); 
        String SOAPAction = "";
       
        String Voucher = this.CreateRequest(suppliername,alliasname,address,statename,pincode,panno,registered,type,mobileno,gstno,ifsccode,accountno,bankname,website,email,phone,fax,contactperson,openingbal);

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
            System.out.println(inputLine);
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