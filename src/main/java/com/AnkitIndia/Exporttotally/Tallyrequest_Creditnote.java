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

public class Tallyrequest_Creditnote {
	
	  public String CreateRequest(String partyname,String trucknumber,String creditnotedate,String statename,double partyamount,String item_name_ledger[],
			  String item_name[],String item_amount[],String item_rate[],String item_passeditemqty[],String item_qty[],
			  Double item_total,String roundoff_amt,String roundoff_gl_ac,int rounfoffdrcr,boolean gst_valid,boolean gststatus,
			  String cgstledger,String sgstledger,String igstledger,double cgstamount,double sgstamount,double igstamount
			  ,String date,
			  String invoicenumber,String discountamount[],String discountledger[],boolean discountstat,String broker,String address,String deliverynotedate,String deliverynoteno,String packing_qty[],String price_based_on[],String packing_uom[],String supplier_address,String saleinvoiceno,String saleinvoicedate)
	    {             
	    


		  String TXML = null;
	        UUID uuid = UUID.randomUUID();
	        String guid = uuid.toString();
	        
	    
	        TXML = "<ENVELOPE>\n"
	                + "<HEADER><TALLYREQUEST>Import Data</TALLYREQUEST></HEADER>\n"
	                + "<BODY>\n"
	                + "<IMPORTDATA>\n"
	                + "<REQUESTDESC><REPORTNAME>All Masters</REPORTNAME><STATICVARIABLES><SVCURRENTCOMPANY>ANKIT INDIA LIMITED</SVCURRENTCOMPANY></STATICVARIABLES></REQUESTDESC>\n"
	                + "<REQUESTDATA>\n"
	                + "<TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n"
	                + "<VOUCHER REMOTEID=\""+guid+"\" VCHTYPE=\"Credit Note\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">\n"      
	             // + "<DATE>20220402</DATE>\n"
	                + "<DATE>"+date+"</DATE>\n"
	                
	     + "<REFERENCEDATE>"+saleinvoicedate+"</REFERENCEDATE>\n"
	      //      + "<BILLOFLADINGDATE>20220402</BILLOFLADINGDATE>\n"
	                + "<BILLOFLADINGDATE>"+date+"</BILLOFLADINGDATE>\n"
	             //  + "<BILLOFLADINGDATE>20220402</BILLOFLADINGDATE>\n"
	            
	                 + "<GSTREGISTRATIONTYPE>Regular</GSTREGISTRATIONTYPE>\n"
	                + "<STATENAME>"+statename+"</STATENAME>\n"
	                + "<VOUCHERTYPENAME>Credit Note</VOUCHERTYPENAME>\n"
	                + "<COUNTRYOFRESIDENCE>India</COUNTRYOFRESIDENCE>\n"
	                + "<PLACEOFSUPPLY>"+statename+"</PLACEOFSUPPLY>\n"           
	                
	                
	                
	                + "<PARTYNAME>"+partyname+"</PARTYNAME>\n"
	                + "<PARTYLEDGERNAME>"+partyname+"</PARTYLEDGERNAME>\n"
 + "<REFERENCE>"+saleinvoiceno+"</REFERENCE>\n"
	                + "<PARTYMAILINGNAME>"+partyname+"</PARTYMAILINGNAME>\n"
	                + "<CONSIGNEEMAILINGNAME>"+partyname+"</CONSIGNEEMAILINGNAME>\n"
	                + "<CONSIGNEESTATENAME>"+statename+"</CONSIGNEESTATENAME>\n"
	                
	                
	                
	                
	                //+ "<VOUCHERNUMBER>1</VOUCHERNUMBER>\n"
	                 + "<VOUCHERNUMBER>"+invoicenumber+"</VOUCHERNUMBER>\n"
	                 
	                 //
	                   + "<BASICBASEPARTYNAME>"+partyname+"</BASICBASEPARTYNAME>\n"
	                 //
	                   
	                + "<FBTPAYMENTTYPE>Default</FBTPAYMENTTYPE>\n"
	                + "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>\n"
	   + "<BASICSHIPPEDBY>"+broker+"</BASICSHIPPEDBY>\n"             
	                + "<BASICBUYERNAME>"+partyname+"</BASICBUYERNAME>\n"
	  + "<BASICFINALDESTINATION>"+supplier_address+"</BASICFINALDESTINATION>\n"         //new aadded on 12/11      
	                
	  // + "<BASICFINALDESTINATION>"+address+"</BASICFINALDESTINATION>\n"
	                + "<BASICSHIPVESSELNO>"+trucknumber+"</BASICSHIPVESSELNO>\n"
	                + "<CONSIGNEECOUNTRYNAME>India</CONSIGNEECOUNTRYNAME>\n"
	                + "<VCHENTRYMODE>As Voucher</VCHENTRYMODE>\n"
	// + "<EFFECTIVEDATE>20220402</EFFECTIVEDATE>\n"
	    + "<EFFECTIVEDATE>"+date+"</EFFECTIVEDATE>\n"  
	    
	                + "<ALLLEDGERENTRIES.LIST>\n"
	                + "<LEDGERNAME>"+partyname+"</LEDGERNAME>\n"
	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
	                + "<ISPARTYLEDGER>Yes</ISPARTYLEDGER>\n"
	                + "<AMOUNT>"+partyamount+"</AMOUNT>\n"   
	                + "<VATEXPAMOUNT>"+partyamount+"</VATEXPAMOUNT>\n"
	                + "<BILLALLOCATIONS.LIST>\n"
	               // + "<NAME>2</NAME>\n"
	                
 + "<NAME>"+invoicenumber+"</NAME>"
 
	                + "<BILLTYPE>Agst Ref</BILLTYPE>\n"
	                + "<TDSDEDUCTEEISSPECIALRATE>No</TDSDEDUCTEEISSPECIALRATE>\n"
	                + "<AMOUNT>"+partyamount+"</AMOUNT>\n"
	                + "</BILLALLOCATIONS.LIST>\n"
	                + "</ALLLEDGERENTRIES.LIST>\n";
	     
	                
	                String TXML2 = "";
	                for(int i=0;i<item_name.length;i++)
	                {
	                	if(price_based_on[i].compareToIgnoreCase("Packing")==0) 
	                	{
	                	
				                TXML2 += "<ALLLEDGERENTRIES.LIST>\n"
				                + "<LEDGERNAME>"+item_name_ledger[i]+"</LEDGERNAME>\n"
				                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
				                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"        
				                + "<INVENTORYALLOCATIONS.LIST>\n"
				                + "<STOCKITEMNAME>"+item_name[i]+"</STOCKITEMNAME>\n"
				                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
				                + "<RATE>"+item_rate[i]+"/"+packing_uom[i]+"</RATE>\n"             
				                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"
			            		+ "<ACTUALQTY>"+packing_qty[i]+" "+packing_uom[i]+" </ACTUALQTY>\n"
			    	            + "<BILLEDQTY>"+packing_qty[i]+" "+packing_uom[i]+" </BILLEDQTY>\n"
				                + "<BATCHALLOCATIONS.LIST>\n"
				                + "<GODOWNNAME>Main Location</GODOWNNAME>\n"
				                + "<BATCHNAME>Primary Batch</BATCHNAME>\n"
				                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"
				                + "<ACTUALQTY>"+packing_qty[i]+item_qty[i]+"</ACTUALQTY>\n"
				                + "<BILLEDQTY>"+packing_qty[i]+item_qty[i]+"</BILLEDQTY>\n"
				                + "</BATCHALLOCATIONS.LIST>\n"
				                + "</INVENTORYALLOCATIONS.LIST>\n"
				                + "</ALLLEDGERENTRIES.LIST>\n";
	                	}
	                	else 
	                	{
	                		  TXML2 += "<ALLLEDGERENTRIES.LIST>\n"
	  				                + "<LEDGERNAME>"+item_name_ledger[i]+"</LEDGERNAME>\n"
	  				                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	  				                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"        
	  				                + "<INVENTORYALLOCATIONS.LIST>\n"
	  				                + "<STOCKITEMNAME>"+item_name[i]+"</STOCKITEMNAME>\n"
	  				                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	  				                + "<RATE>"+item_rate[i]+"/"+item_qty[i]+"</RATE>\n"             
	  				                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"
	  				                + "<ACTUALQTY>"+item_passeditemqty[i]+item_qty[i]+"</ACTUALQTY>\n"
	  				                + "<BILLEDQTY>"+item_passeditemqty[i]+item_qty[i]+"</BILLEDQTY>\n"
	  				                + "<BATCHALLOCATIONS.LIST>\n"
	  				                + "<GODOWNNAME>Main Location</GODOWNNAME>\n"
	  				                + "<BATCHNAME>Primary Batch</BATCHNAME>\n"
	  				                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"
	  				                + "<ACTUALQTY>"+item_passeditemqty[i]+item_qty[i]+"</ACTUALQTY>\n"
	  				                + "<BILLEDQTY>"+item_passeditemqty[i]+item_qty[i]+"</BILLEDQTY>\n"
	  				                + "</BATCHALLOCATIONS.LIST>\n"
	  				                + "</INVENTORYALLOCATIONS.LIST>\n"
	  				                + "</ALLLEDGERENTRIES.LIST>\n";
	                	}
	                }
	                
	                String TXML3 = "";
	                //gst_valid if false then whole gst wont show 
	                //if    gststatus true  then cgst and sgst will show  if not then only igst will show
	                if(gst_valid==true) 
	                {
	                	
	                	if(gststatus == true) 
	   	                {
	   	                	 TXML3= "<ALLLEDGERENTRIES.LIST>\n"
	   	         	                + "<LEDGERNAME>"+cgstledger+"</LEDGERNAME>\n"
	   	         	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	   	         	                + "<AMOUNT>-"+cgstamount+"</AMOUNT>\n"
	   	         	                + "</ALLLEDGERENTRIES.LIST>\n"
	   	         	                
	   	         	                + "<ALLLEDGERENTRIES.LIST>\n"
	   	         	                + "<LEDGERNAME>"+sgstledger+"</LEDGERNAME>\n"
	   	         	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	   	         	                + "<AMOUNT>-"+sgstamount+"</AMOUNT>\n"
	   	         	                + "</ALLLEDGERENTRIES.LIST>\n";
	   	                }
	   	                else 
	   	                {
	   	                	 TXML3= "<ALLLEDGERENTRIES.LIST>\n"
	   	         	                + "<LEDGERNAME>"+igstledger+"</LEDGERNAME>\n"
	   	         	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	   	         	                + "<AMOUNT>-"+igstamount+"</AMOUNT>\n"
	   	         	                + "</ALLLEDGERENTRIES.LIST>\n";
	   	                }
	                }
	                String TXML4 = "";
	                if(discountstat==true) 
	                {
	                	for(int i=0;i<discountledger.length;i++) 
	                	{
	                		TXML4+= "<ALLLEDGERENTRIES.LIST>\n"
	                                + "<LEDGERNAME>"+discountledger[i]+"</LEDGERNAME>\n"
	          	                    + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	          	                    + "<AMOUNT>-"+discountamount[i]+"</AMOUNT>\n"
	          	                    + "</ALLLEDGERENTRIES.LIST>\n"; 
	          	        	
	                	}
	                	
	                }
	                
	                String TXML5 = "";
	                if(rounfoffdrcr>0) 
  	                {
	                	TXML5= "<ALLLEDGERENTRIES.LIST>\n"
  	                            + "<LEDGERNAME>"+roundoff_gl_ac+"</LEDGERNAME>\n"
            	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
            	                + "<AMOUNT>-"+roundoff_amt+"</AMOUNT>\n"    
            	               // + "<AMOUNT>-0.02</AMOUNT>\n" 
            	                + "</ALLLEDGERENTRIES.LIST>\n" 
            	                + "</VOUCHER>\n"
            	                + "</TALLYMESSAGE>\n"
            	                + "</REQUESTDATA>\n"
            	                + "</IMPORTDATA>\n"
            	                + "</BODY>\n"
            	                + "</ENVELOPE>";
  	                }
            	    else if(rounfoffdrcr<0) 
            	    {
            	    	TXML5= "<ALLLEDGERENTRIES.LIST>\n"
            	    			+ "<LEDGERNAME>"+roundoff_gl_ac+"</LEDGERNAME>\n"
            	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
            	           //     + "<AMOUNT>-"+roundoff_amt+"</AMOUNT>\n"//change 12 11 
            	                
            	                + "<AMOUNT>"+roundoff_amt.substring(1, roundoff_amt.length())+"</AMOUNT>\n"
            	                
            	              // + "<AMOUNT>-0.02</AMOUNT>\n"   
            	                + "</ALLLEDGERENTRIES.LIST>\n" 
            	                + "</VOUCHER>\n"
            	                + "</TALLYMESSAGE>\n"
            	                + "</REQUESTDATA>\n"
            	                + "</IMPORTDATA>\n"
            	                + "</BODY>\n"
            	                + "</ENVELOPE>";
            	    }
            	    else 
            	    {
            	    	 TXML5= "</VOUCHER>\n"
        		                + "</TALLYMESSAGE>\n"
        		                + "</REQUESTDATA>\n"
        		                + "</IMPORTDATA>\n"
        		                + "</BODY>\n"
        		                + "</ENVELOPE>";
            	    }
	                
	                
	               
	                if(gst_valid==true) 
	                {
	                	 TXML=TXML+TXML2+TXML3+TXML4+TXML5;
	                }
	                else 
	                {
	                	 TXML=TXML+TXML2+TXML4+TXML5;
	                }
	               
	                
	        return TXML;
	    }
	  
	   
	    public String SendToTally(String partyname,String trucknumber,String creditnotedate,String statename,double partyamount,String item_name_ledger[],String item_name[],String item_amount[],String item_rate[],String item_passeditemqty[],String item_qty[], Double item_total,String roundoff_amt,String roundoff_gl_ac,int rounfoffdrcr,boolean gst_valid,boolean gststatus,String cgstledger,String sgstledger,String igstledger,double cgstamount,double sgstamount,double igstamount,String date,String invoicenumber,String discountamount[],String discountledger[],boolean discountstat,String broker,String address,String deliverynotedate,String deliverynoteno,String packing_qty[],String price_based_on[],String packing_uom[],String supplier_address,String saleinvoiceno,String saleinvoicedate) throws Exception{
	    	
	    	//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
	       
	        String Url = "http://192.168.10.100:9000/";
	    	//String Url = "http://localhost:9000/";
	    	// String Url = "http://192.168.1.254:9000/";
	      //  String Url = prop.getProperty("tallyurl"); 
	        String SOAPAction = "";
	       
	        String Voucher = this.CreateRequest(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,item_name,item_amount,item_rate,item_passeditemqty,item_qty,item_total,roundoff_amt,roundoff_gl_ac,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,cgstamount,sgstamount,igstamount,date,invoicenumber,discountamount,discountledger,discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,price_based_on,packing_uom,supplier_address,saleinvoiceno,saleinvoicedate);
System.out.println(" Voucher : "+Voucher);
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
