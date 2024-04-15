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
import java.util.UUID;

public class Tallyrequest_ProductionReg {

	
	public String CreateRequest(String date,String item_name_ledger[],String item_name[],String item_rate[],String item_uom[],
			 String item_passeditemqty[],String packing_uom[],String packing_qty[],double item_total,double totalqty,double totalbags) 
	{
		  String TXML = null;
          UUID uuid = UUID.randomUUID();
          String guid = uuid.toString();
          
          
          TXML ="<ENVELOPE>\n"
        		 +"<HEADER><TALLYREQUEST>Import Data</TALLYREQUEST></HEADER>\n"
        		 + "<BODY>\n"
        		 + "<IMPORTDATA>\n"
        		 + "<REQUESTDESC><REPORTNAME>Vouchers</REPORTNAME><STATICVARIABLES><SVCURRENTCOMPANY>ANKIT INDIA LIMITED</SVCURRENTCOMPANY></STATICVARIABLES></REQUESTDESC>\n"
        		 + "<REQUESTDATA>\n"
        		 + "<TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n"
                 + "<VOUCHER REMOTEID=\""+guid+"\" VCHTYPE=\"FINISHED GOODS PRODUCTION\" ACTION=\"Create\" OBJVIEW=\"Consumption Voucher View\">\n"
                 + "<DATE>"+date+"</DATE>\n"
                 + "<VOUCHERTYPENAME>FINISHED GOODS PRODUCTION</VOUCHERTYPENAME>\n"
                 
                 + "<VOUCHERNUMBER></VOUCHERNUMBER>\n"
                 
                 + "<CSTFORMISSUETYPE/><CSTFORMRECVTYPE/>\n"
                 + "<FBTPAYMENTTYPE>Default</FBTPAYMENTTYPE>\n"
                 + "<PERSISTEDVIEW>Consumption Voucher View</PERSISTEDVIEW>\n"
			     + "<VCHENTRYMODE>Use for Stock Journal</VCHENTRYMODE>\n"
			     + "<DESTINATIONGODOWN>Main Location</DESTINATIONGODOWN>\n"
			     + "<EFFECTIVEDATE>"+date+"</EFFECTIVEDATE>\n"
			     + "<ISVATDUTYPAID>Yes</ISVATDUTYPAID>\n";
		   
			     String TXML2 = "";
			     for(int i=0;i<item_name.length;i++)
		         {
			    	 if(Double.parseDouble(item_passeditemqty[i])>0) 
			    	 {
			    		 double amount=Double.parseDouble(item_rate[i]) * Double.parseDouble(item_passeditemqty[i]);
					     TXML2 += "<INVENTORYENTRIESIN.LIST>\n"
					     + "<STOCKITEMNAME>"+item_name[i]+"</STOCKITEMNAME>\n"
					     + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
					     + "<ISLASTDEEMEDPOSITIVE>Yes</ISLASTDEEMEDPOSITIVE>\n"
					     + "<ISAUTONEGATE>No</ISAUTONEGATE>\n"
					     + "<ISCUSTOMSCLEARANCE>No</ISCUSTOMSCLEARANCE>\n"
					     + "<ISTRACKCOMPONENT>No</ISTRACKCOMPONENT>\n"
					     + "<ISTRACKPRODUCTION>No</ISTRACKPRODUCTION>\n"
					     + "<ISPRIMARYITEM>No</ISPRIMARYITEM>\n"
					     + "<ISSCRAP>No</ISSCRAP>\n"
					     + "<RATE>"+item_rate[i]+"/"+item_uom[i]+"</RATE>\n"
					     + "<AMOUNT>-"+amount+"</AMOUNT>\n"
					     + "<ACTUALQTY> "+item_passeditemqty[i]+" "+item_uom[i]+" =  "+packing_qty[i]+" "+packing_uom[i]+"</ACTUALQTY>\n"
					     + "<BILLEDQTY> "+item_passeditemqty[i]+" "+item_uom[i]+" =  "+packing_qty[i]+" "+packing_uom[i]+"</BILLEDQTY>\n"
					     + "<BATCHALLOCATIONS.LIST>\n"
					     + "<GODOWNNAME>Main Location</GODOWNNAME>\n"
					     + "<BATCHNAME>Primary Batch</BATCHNAME>\n"
					     + "<INDENTNO/>\n"
					     + "<ORDERNO/>\n"
					     + "<TRACKINGNUMBER/>\n"
					     + "<DYNAMICCSTISCLEARED>No</DYNAMICCSTISCLEARED>\n"
					    
					     + "<AMOUNT>-"+amount+"</AMOUNT>\n"
					   
					     + "<ACTUALQTY> "+item_passeditemqty[i]+" "+item_uom[i]+" =  "+packing_qty[i]+" "+packing_uom[i]+"</ACTUALQTY>\n"
					     + "<BILLEDQTY> "+item_passeditemqty[i]+" "+item_uom[i]+" =  "+packing_qty[i]+" "+packing_uom[i]+"</BILLEDQTY>\n"
					     
					     + "<ADDITIONALDETAILS.LIST>        </ADDITIONALDETAILS.LIST>\n"
					     + "<VOUCHERCOMPONENTLIST.LIST>        </VOUCHERCOMPONENTLIST.LIST>\n"
					     + "</BATCHALLOCATIONS.LIST>\n"
					     + "<DUTYHEADDETAILS.LIST>       </DUTYHEADDETAILS.LIST>\n"
					     + "<SUPPLEMENTARYDUTYHEADDETAILS.LIST>       </SUPPLEMENTARYDUTYHEADDETAILS.LIST>\n"
					     + "<TAXOBJECTALLOCATIONS.LIST>       </TAXOBJECTALLOCATIONS.LIST>\n"
					     + "<COSTTRACKALLOCATIONS.LIST>       </COSTTRACKALLOCATIONS.LIST>\n"
					     + "<REFVOUCHERDETAILS.LIST>       </REFVOUCHERDETAILS.LIST>\n"
					     + "<EXCISEALLOCATIONS.LIST>       </EXCISEALLOCATIONS.LIST>\n"
					     + "<EXPENSEALLOCATIONS.LIST>       </EXPENSEALLOCATIONS.LIST>\n"
					     + "</INVENTORYENTRIESIN.LIST>\n";
			    	 }
				    
		         };
		         String TXML3 = "";
		        
		        	 
		        		 double amount=item_total;
					     TXML3 += "<INVENTORYENTRIESIN.LIST>\n"
					     + "<STOCKITEMNAME>WHEAT IN BINS</STOCKITEMNAME>\n"
					     + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
					     + "<ISLASTDEEMEDPOSITIVE>No</ISLASTDEEMEDPOSITIVE>\n"
					     + "<ISAUTONEGATE>No</ISAUTONEGATE>\n"
					     + "<ISCUSTOMSCLEARANCE>No</ISCUSTOMSCLEARANCE>\n"
					     + "<ISTRACKCOMPONENT>No</ISTRACKCOMPONENT>\n"
					     + "<ISTRACKPRODUCTION>No</ISTRACKPRODUCTION>\n"
					     + "<ISPRIMARYITEM>No</ISPRIMARYITEM>\n"
					     + "<ISSCRAP>No</ISSCRAP>\n"
					     + "<RATE>2500.0/"+item_uom[0]+"</RATE>\n"
					     + "<AMOUNT>"+amount+"</AMOUNT>\n"
					     + "<ACTUALQTY> "+totalqty+" "+item_uom[0]+" </ACTUALQTY>\n"
					     + "<BILLEDQTY> "+totalqty+" "+item_uom[0]+"</BILLEDQTY>\n"
					     + "<BATCHALLOCATIONS.LIST>\n"
					     + "<GODOWNNAME>Main Location</GODOWNNAME>\n"
					     + "<BATCHNAME>Primary Batch</BATCHNAME>\n"
					     + "<INDENTNO/>\n"
					     + "<ORDERNO/>\n"
					     + "<TRACKINGNUMBER/>\n"
					     + "<DYNAMICCSTISCLEARED>No</DYNAMICCSTISCLEARED>\n"
					     + "<AMOUNT>"+amount+"</AMOUNT>\n"
					     + "<ACTUALQTY> "+totalqty+" "+item_uom[0]+"</ACTUALQTY>\n"
					     + "<BILLEDQTY> "+totalqty+" "+item_uom[0]+"</BILLEDQTY>\n"
					     + "<ADDITIONALDETAILS.LIST>        </ADDITIONALDETAILS.LIST>\n"
					     + "<VOUCHERCOMPONENTLIST.LIST>        </VOUCHERCOMPONENTLIST.LIST>\n"
					     + "</BATCHALLOCATIONS.LIST>\n"
					     + "<DUTYHEADDETAILS.LIST>       </DUTYHEADDETAILS.LIST>\n"
					     + "<SUPPLEMENTARYDUTYHEADDETAILS.LIST>       </SUPPLEMENTARYDUTYHEADDETAILS.LIST>\n"
					     + "<TAXOBJECTALLOCATIONS.LIST>       </TAXOBJECTALLOCATIONS.LIST>\n"
					     + "<COSTTRACKALLOCATIONS.LIST>       </COSTTRACKALLOCATIONS.LIST>\n"
					     + "<REFVOUCHERDETAILS.LIST>       </REFVOUCHERDETAILS.LIST>\n"
					     + "<EXCISEALLOCATIONS.LIST>       </EXCISEALLOCATIONS.LIST>\n"
					     + "<EXPENSEALLOCATIONS.LIST>       </EXPENSEALLOCATIONS.LIST>\n"
					     + "</INVENTORYENTRIESIN.LIST>\n";
			    	
				    
		        
		         TXML=TXML+TXML2+TXML3;
		         TXML+= "<PAYROLLMODEOFPAYMENT.LIST>      </PAYROLLMODEOFPAYMENT.LIST>\n"
				 + "<ATTDRECORDS.LIST>      </ATTDRECORDS.LIST>\n"
			     + "<GSTEWAYCONSIGNORADDRESS.LIST>      </GSTEWAYCONSIGNORADDRESS.LIST>\n"
			     + "<GSTEWAYCONSIGNEEADDRESS.LIST>      </GSTEWAYCONSIGNEEADDRESS.LIST>\n"
			     + "<TEMPGSTRATEDETAILS.LIST>      </TEMPGSTRATEDETAILS.LIST>\n"
			     + "</VOUCHER>\n"
			     + "</TALLYMESSAGE>\n"
			     + "</REQUESTDATA>\n"
			     + "</IMPORTDATA>\n"
			     + "</BODY>\n"
			     + "</ENVELOPE>\n";
        
		System.out.println(TXML);
		return TXML;
	}
	
	 public String SendToTally(String date,String item_name_ledger[],String item_name[],String item_rate[],String item_uom[],
			 String item_passeditemqty[],String packing_uom[],String packing_qty[],double item_total,double totalqty,double totalbags) throws Exception{
	    	
	    	//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
	       
	        //String Url = "http://192.168.10.100:9000/";
		 	String Url = "http://192.168.10.201:9000/";
	    
	        String SOAPAction = "";
	       
	        String Voucher = this.CreateRequest(date,item_name_ledger,item_name,item_rate,item_uom,
					item_passeditemqty,packing_uom,packing_qty,item_total,totalqty,totalbags);

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
	        	System.out.println("check "+inputLine);
	        }

	        in.close();
	        
	        String y=getvalue(x,"CREATED");
	        
	        return x+"||"+y;
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
