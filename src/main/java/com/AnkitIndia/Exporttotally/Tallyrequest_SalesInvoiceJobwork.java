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

public class Tallyrequest_SalesInvoiceJobwork {


	  public String CreateRequest(String partyname,String trucknumber,String creditnotedate,String statename,double partyamount,String item_name_ledger,
			  String item_name,String item_amount,String item_rate,String item_passeditemqty,String item_qty,
			  Double item_total,double roundoff_amt,String roundoff_gl_ac,int rounfoffdrcr,boolean gst_valid,boolean gststatus,
			  String cgstledger,String sgstledger,String igstledger,double cgstamount,double sgstamount,double igstamount,String date,
			  String invoicenumber,String discountamount,String discountledger,boolean discountstat,String broker,String address,String deliverynotedate,
			  String deliverynoteno,String packing_qty,String price_based_on,String packing_uom,String supplier_address,String print_to_name,
			  boolean addplus,boolean addminus,String add_plus_ledgername,String add_minus_ledgername,double add_plus_amount,double add_minus_amount,boolean einvoice,
			  boolean remarkstat,String remarks,String customerpincode,String gstinno,String panno,String ackno,String ackdate,String signedQRCode,
			  String irnno,String waybill,boolean waybillwoincoice,boolean waybillcheck,String waybilldate)
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
	                + "<VOUCHER REMOTEID=\""+guid+"\" VCHTYPE=\"Sales\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">\n"      
	               
	                  
	                + "<ADDRESS.LIST TYPE=\"String\">\n"    
	                + "<ADDRESS>"+address+"</ADDRESS>\n"
					+ "</ADDRESS.LIST>\n"
					+ "<DISPATCHFROMADDRESS.LIST TYPE=\"String\">\n"
					+ "<DISPATCHFROMADDRESS>802, MAHUA ROAD, BELKUNDA BHOJPATTI</DISPATCHFROMADDRESS>\n"
					+ "<DISPATCHFROMADDRESS>VAISHALI - 844125</DISPATCHFROMADDRESS>\n"
					+ "<DISPATCHFROMADDRESS>GST : 10AADCA2518H1ZD</DISPATCHFROMADDRESS>\n"
					+ "<DISPATCHFROMADDRESS>State Name : Bihar Code : 10</DISPATCHFROMADDRESS>\n"
					+ "<DISPATCHFROMADDRESS>Fssai Lic No:10423110000162</DISPATCHFROMADDRESS>\n"
					+ "</DISPATCHFROMADDRESS.LIST>\n"
					+ "<BASICBUYERADDRESS.LIST TYPE=\"String\">\n"
					+ "<BASICBUYERADDRESS>"+address+"</BASICBUYERADDRESS>\n"
					+ "</BASICBUYERADDRESS.LIST>\n"
					
	                
	                + "<DATE>"+date+"</DATE>\n"
	                + "<BILLOFLADINGDATE>"+date+"</BILLOFLADINGDATE>\n";
			        if(einvoice == true)
		     		{
			        	//TXML+= "<GSTREGISTRATIONTYPE>"+"Registered"+"</GSTREGISTRATIONTYPE>\n"
			        	TXML+="<IRNACKDATE>"+ackdate+"</IRNACKDATE>\n"
			        		+"<GSTREGISTRATIONTYPE>"+"Registered"+"</GSTREGISTRATIONTYPE>\n"
			        		+"<IRN>"+irnno+"</IRN>\n"
			        		+"<IRNACKNO>"+ackno+"</IRNACKNO>\n"
			        		+"<IRNQRCODE>"+signedQRCode+"</IRNQRCODE>\n";
		     		}
					else 
					{
						TXML+="<GSTREGISTRATIONTYPE>"+"Unregistered"+"</GSTREGISTRATIONTYPE>\n";
					}
			        
			        TXML+="<VATDEALERTYPE>"+"Regular"+"</VATDEALERTYPE>\n"
	                + "<STATENAME>"+statename+"</STATENAME>\n";
	        
	        		/*if(einvoice == false)
	        		{
		              TXML+="<NARRATION>"+"Notes: We hereby declare that though our aggregate turnover in any preceding financial year from 2017-18 onwards is more than the aggregate turnover notified under sub-rule (4) of rule 48, we are not required to prepare an invoice in terms of the provisions of the said sub-rule."+"</NARRATION>\n";
	        		};*/
	        
	              
	                //here ends  einvoicenarration 
	                
	                TXML+= "<VOUCHERTYPENAME>Sales</VOUCHERTYPENAME>\n"
	                + "<COUNTRYOFRESIDENCE>India</COUNTRYOFRESIDENCE>\n"
					+ "<PARTYGSTIN>"+gstinno+"</PARTYGSTIN>\n"
	                + "<PLACEOFSUPPLY>"+statename+"</PLACEOFSUPPLY>\n"
	                + "<PARTYNAME>"+partyname+"</PARTYNAME>\n"
	                + "<PARTYLEDGERNAME>"+partyname+"</PARTYLEDGERNAME>\n"
	                + "<PARTYMAILINGNAME>"+print_to_name+"</PARTYMAILINGNAME>\n"
	                + "<PARTYPINCODE>"+customerpincode+"</PARTYPINCODE>\n"
					+ "<BILLTOPLACE>"+address+"</BILLTOPLACE>\n"
					+ "<DISPATCHFROMNAME>ANKIT INDIA LIMITED</DISPATCHFROMNAME>\n"
					+ "<DISPATCHFROMSTATENAME>Bihar</DISPATCHFROMSTATENAME>\n"
					+ "<DISPATCHFROMPINCODE>844125</DISPATCHFROMPINCODE>\n"
					+ "<DISPATCHFROMPLACE>Hajipur</DISPATCHFROMPLACE>\n"
					+ "<SHIPTOPLACE>"+address+"</SHIPTOPLACE>\n"
					+ "<CONSIGNEEGSTIN>"+gstinno+"</CONSIGNEEGSTIN>\n"
	                + "<CONSIGNEEMAILINGNAME>"+partyname+"</CONSIGNEEMAILINGNAME>\n"
	                + "<CONSIGNEESTATENAME>"+statename+"</CONSIGNEESTATENAME>\n"
	                + "<CONSIGNEEPINCODE>"+customerpincode+"</CONSIGNEEPINCODE>\n"
	                + "<VOUCHERNUMBER>"+invoicenumber+"</VOUCHERNUMBER>\n"
	                + "<BASICBASEPARTYNAME>"+partyname+"</BASICBASEPARTYNAME>\n"
	                + "<FBTPAYMENTTYPE>Default</FBTPAYMENTTYPE>\n"
	                + "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>\n"
	                + "<BASICSHIPPEDBY>"+broker+"</BASICSHIPPEDBY>\n"       
	                + "<BASICBUYERNAME>"+partyname+"</BASICBUYERNAME>\n"
	                + "<BASICFINALDESTINATION>"+supplier_address+"</BASICFINALDESTINATION>\n"
	                + "<BASICSHIPVESSELNO>"+trucknumber+"</BASICSHIPVESSELNO>\n"
	                + "<CONSIGNEECOUNTRYNAME>India</CONSIGNEECOUNTRYNAME>\n"
                  + "<BUYERPINNUMBER>"+panno+"</BUYERPINNUMBER>\n"
	                + "<CONSIGNEEPINNUMBER>"+panno+"</CONSIGNEEPINNUMBER>\n"
	                + "<VCHENTRYMODE>As Voucher</VCHENTRYMODE>\n"
	                + "<EFFECTIVEDATE>"+date+"</EFFECTIVEDATE>\n"
	                + "<ISINVOICE>Yes</ISINVOICE>\n"
					+ "<EWAYBILLDETAILS.LIST>\n"
					+ "<CONSIGNORADDRESS.LIST TYPE=\"String\">\n"
					+ "<CONSIGNORADDRESS>802, MAHUA ROAD, BELKUNDA BHOJPATTI, VAISHALI - 844125, GST : 10AADCA2518H1ZD, State Name : Bihar Code : 10, Fssai Lic</CONSIGNORADDRESS>\n"
					+ "<CONSIGNORADDRESS>No:10423110000162</CONSIGNORADDRESS>\n"
					+ "</CONSIGNORADDRESS.LIST>\n"
					+ "<CONSIGNEEADDRESS.LIST TYPE=\"String\">\n"
					+ "<CONSIGNEEADDRESS>"+address+"</CONSIGNEEADDRESS>\n"
					+ "</CONSIGNEEADDRESS.LIST>\n"
					+ "<DOCUMENTTYPE>Tax Invoice</DOCUMENTTYPE>\n"
					+ "<CONSIGNEEPINCODE>"+customerpincode+"</CONSIGNEEPINCODE>\n"
					+ "<SUBTYPE>Supply</SUBTYPE>\n"
					+ "<CONSIGNORPLACE>Hajipur</CONSIGNORPLACE>\n"
					+ "<CONSIGNORPINCODE>844125</CONSIGNORPINCODE>\n"
					+ "<CONSIGNEEPLACE>"+address+"</CONSIGNEEPLACE>\n"
					+ "<SHIPPEDFROMSTATE>Bihar</SHIPPEDFROMSTATE>\n"
					+ "<SHIPPEDTOSTATE>"+supplier_address+"</SHIPPEDTOSTATE>\n"
					+ "<IGNOREGSTINVALIDATION>No</IGNOREGSTINVALIDATION>\n"
					+ "<ISCANCELLED>No</ISCANCELLED>\n"
					+ "<ISCANCELPENDING>No</ISCANCELPENDING>\n"
					+ "<IGNOREGENERATIONVALIDATION>No</IGNOREGENERATIONVALIDATION>\n"
					+ "<ISEXPORTEDFORGENERATION>No</ISEXPORTEDFORGENERATION>\n"
					+ "<TRANSPORTDETAILS.LIST>\n"
					+ "<TRANSPORTMODE>1 - Road</TRANSPORTMODE>\n"
					+ "<VEHICLENUMBER>"+trucknumber+"</VEHICLENUMBER>\n"
					+ "<VEHICLETYPE>R - Regular</VEHICLETYPE>\n"
					+ "<IGNOREVEHICLENOVALIDATION>No</IGNOREVEHICLENOVALIDATION>\n"
					+ "<ISTRANSIDPENDING>No</ISTRANSIDPENDING>\n"
					+ "<ISTRANSIDUPDATED>No</ISTRANSIDUPDATED>\n"
					+ "<IGNORETRANSIDVALIDATION>No</IGNORETRANSIDVALIDATION>\n"
					+ "<ISEXPORTEDFORTRANSPORTERID>No</ISEXPORTEDFORTRANSPORTERID>\n"
					+ "<ISPARTBPENDING>No</ISPARTBPENDING>\n"
					+ "<ISPARTBUPDATED>No</ISPARTBUPDATED>\n"
					+ "<IGNOREPARTBVALIDATION>No</IGNOREPARTBVALIDATION>\n"
					+ "<ISEXPORTEDFORPARTB>No</ISEXPORTEDFORPARTB>\n"
					//+ "<DISTANCE> 140</DISTANCE>\n"
					+ "</TRANSPORTDETAILS.LIST>\n"
					+ "<EXTENSIONDETAILS.LIST> </EXTENSIONDETAILS.LIST>\n"
					+ "</EWAYBILLDETAILS.LIST>\n"
			
				    + "<INVOICEDELNOTES.LIST>\n"
				    + "<BASICSHIPPINGDATE>"+deliverynotedate+"</BASICSHIPPINGDATE>\n"
				    + "<BASICSHIPDELIVERYNOTE>"+deliverynoteno+"</BASICSHIPDELIVERYNOTE>\n"
				    + "</INVOICEDELNOTES.LIST>\n"   
	                + "<ALLLEDGERENTRIES.LIST>\n"
	                + "<LEDGERNAME>"+partyname+"</LEDGERNAME>\n"
	                + "<GSTCLASS/>\n"
	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	                + "<ISPARTYLEDGER>Yes</ISPARTYLEDGER>\n"
	                + "<ISLASTDEEMEDPOSITIVE>Yes</ISLASTDEEMEDPOSITIVE>\n"
	                + "<AMOUNT>-"+partyamount+"</AMOUNT>\n"
	                + "<VATEXPAMOUNT>-"+partyamount+"</VATEXPAMOUNT>\n"
	                + "<BILLALLOCATIONS.LIST>\n"
	                + "<NAME>"+invoicenumber+"</NAME>"
	                + "<BILLTYPE>New Ref</BILLTYPE>"
	                + "<TDSDEDUCTEEISSPECIALRATE>No</TDSDEDUCTEEISSPECIALRATE>"
	                + "<AMOUNT>-"+partyamount+"</AMOUNT>"
	                + "</BILLALLOCATIONS.LIST>"
	                + "</ALLLEDGERENTRIES.LIST>\n";
	              
			        String TXML2 = "";
		           
		            	if(price_based_on.compareToIgnoreCase("Packing")==0) 
		            	{
		            		 TXML2 += "<ALLLEDGERENTRIES.LIST>\n"
		         	                + "<LEDGERNAME>"+item_name_ledger+"</LEDGERNAME>\n"
		         	                + "<GSTCLASS/>\n"
		         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
		         	                + "<AMOUNT>"+item_amount+"</AMOUNT>\n"
		         	                + "<VATEXPAMOUNT>"+item_amount+"</VATEXPAMOUNT>\n"
		         	                + "<INVENTORYALLOCATIONS.LIST>\n"
		         	                + "<STOCKITEMNAME>"+item_name+"</STOCKITEMNAME>\n"
		         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
		         	                + "<RATE>"+item_rate+"/ "+packing_uom+"</RATE>\n"
		        	                + "<AMOUNT>"+item_amount+"</AMOUNT>\n"
		        	                + "<ACTUALQTY>"+packing_qty+" "+packing_uom+" </ACTUALQTY>\n"
		        	                + "<BILLEDQTY>"+packing_qty+" "+packing_uom+" </BILLEDQTY>\n"
		        	                + "<BATCHALLOCATIONS.LIST>\n"
		         	                + "<GODOWNNAME>Main Location</GODOWNNAME>\n"
		         	                + "<BATCHNAME>Primary Batch</BATCHNAME>\n"
		         	                + "<DYNAMICCSTISCLEARED>No</DYNAMICCSTISCLEARED>\n"
		         	                + "<AMOUNT>"+item_amount+"</AMOUNT>\n"
		         	                + "<ACTUALQTY>"+packing_qty+item_qty+"</ACTUALQTY>\n"
		         	                + "<BILLEDQTY>"+packing_qty+item_qty+"</BILLEDQTY>\n"
		         	                + "</BATCHALLOCATIONS.LIST>\n"
		         	                + "</INVENTORYALLOCATIONS.LIST>"
		         	                + "</ALLLEDGERENTRIES.LIST>\n";
		            	}
		            	else 
		            	{
		            		 TXML2 += "<ALLLEDGERENTRIES.LIST>\n"
		         	                + "<LEDGERNAME>"+item_name_ledger+"</LEDGERNAME>\n"
		         	                + "<GSTCLASS/>\n"
		         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
		         	                + "<AMOUNT>"+item_amount+"</AMOUNT>\n"
		         	                + "<VATEXPAMOUNT>"+item_amount+"</VATEXPAMOUNT>\n"
		         	                + "<INVENTORYALLOCATIONS.LIST>\n"
		         	                + "<STOCKITEMNAME>"+item_name+"</STOCKITEMNAME>\n"
		         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
		         	                + "<RATE>"+item_rate+"/"+item_qty+"</RATE>\n"
		         	                + "<AMOUNT>"+item_amount+"</AMOUNT>\n"
		         	                + "<ACTUALQTY>"+item_passeditemqty+item_qty+"</ACTUALQTY>\n"
		         	                + "<BILLEDQTY>"+item_passeditemqty+item_qty+"</BILLEDQTY>\n"
		         	                + "<BATCHALLOCATIONS.LIST>\n"
		         	                + "<GODOWNNAME>Main Location</GODOWNNAME>\n"
		         	                + "<BATCHNAME>Primary Batch</BATCHNAME>\n"
		         	                + "<DYNAMICCSTISCLEARED>No</DYNAMICCSTISCLEARED>\n"
		         	                + "<AMOUNT>"+item_amount+"</AMOUNT>\n"
		         	                + "<ACTUALQTY>"+item_passeditemqty+item_qty+"</ACTUALQTY>\n"
		         	                + "<BILLEDQTY>"+item_passeditemqty+item_qty+"</BILLEDQTY>\n"
		         	                + "</BATCHALLOCATIONS.LIST>\n"
		         	                + "</INVENTORYALLOCATIONS.LIST>"
		         	                + "</ALLLEDGERENTRIES.LIST>\n";
		            	}
             
	                
                  
		            String TXML3 = "";
		            if(gst_valid==true) 
		            {
		            	if(gststatus == true) 
			                {
			                	 TXML3= "<ALLLEDGERENTRIES.LIST>\n"
			         	                + "<LEDGERNAME>"+cgstledger+"</LEDGERNAME>\n"
			         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
			         	                + "<AMOUNT>"+cgstamount+"</AMOUNT>\n"
			         	                + "</ALLLEDGERENTRIES.LIST>\n"
			         	                
			         	                + "<ALLLEDGERENTRIES.LIST>\n"
			         	                + "<LEDGERNAME>"+sgstledger+"</LEDGERNAME>\n"
			         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
			         	                + "<AMOUNT>"+sgstamount+"</AMOUNT>\n"
			         	                + "</ALLLEDGERENTRIES.LIST>\n";
			                }
			                else 
			                {
			                	 TXML3= "<ALLLEDGERENTRIES.LIST>\n"
			         	                + "<LEDGERNAME>"+igstledger+"</LEDGERNAME>\n"
			         	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
			         	                + "<AMOUNT>"+igstamount+"</AMOUNT>\n"
			         	                + "</ALLLEDGERENTRIES.LIST>\n";
			                }
                  }
		            String TXML4 = "";
		           
		            
		            if(addplus == true) 
		            {
				          	 TXML=TXML + "<ALLLEDGERENTRIES.LIST>\n"
				  	    			+ "<LEDGERNAME>"+add_plus_ledgername+"</LEDGERNAME>\n"
				  	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
				  	                + "<AMOUNT>"+add_plus_amount+"</AMOUNT>\n"          
				  	                + "</ALLLEDGERENTRIES.LIST>\n";
		            }
		            if(addminus == true) 
		            {
				          	  TXML=TXML + "<ALLLEDGERENTRIES.LIST>\n"
				                      + "<LEDGERNAME>"+add_minus_ledgername+"</LEDGERNAME>\n"
					                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
					                + "<AMOUNT>-"+add_minus_amount+"</AMOUNT>\n"          
					                + "</ALLLEDGERENTRIES.LIST>\n"  ;
		          	 
		            }
		            
		            String TXML5 = "";
		            if(rounfoffdrcr<0) //dr
		              {
		            	
		            	TXML5= "<ALLLEDGERENTRIES.LIST>\n"
		                        + "<LEDGERNAME>"+roundoff_gl_ac+"</LEDGERNAME>\n"
		    	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
		    	                + "<AMOUNT>-"+roundoff_amt+"</AMOUNT>\n"  
		    	                + "</ALLLEDGERENTRIES.LIST>\n" 
		    	                + "</VOUCHER>\n"
		    	                + "</TALLYMESSAGE>\n"
		    	                + "</REQUESTDATA>\n"
		    	                + "</IMPORTDATA>\n"
		    	                + "</BODY>\n"
		    	                + "</ENVELOPE>";
		              }
		    	    else if(rounfoffdrcr>0) //cr
		    	    {
		    	    	System.out.println("cr");
		    	    	TXML5= "<ALLLEDGERENTRIES.LIST>\n"
		    	    			+ "<LEDGERNAME>"+roundoff_gl_ac+"</LEDGERNAME>\n"
		    	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
		    	                + "<AMOUNT>"+roundoff_amt+"</AMOUNT>\n"
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
	        System.out.println("hello "+TXML);
	        return TXML;
	    }
	  
	   
	    public String SendToTally(String partyname,String trucknumber,String creditnotedate,String statename,double partyamount,String item_name_ledger,String item_name,
	    		String item_amount,String item_rate,String item_passeditemqty,String item_qty, Double item_total,double roundoff_amt,String roundoff_gl_ac,int rounfoffdrcr,
	    		boolean gst_valid,boolean gststatus,String cgstledger,String sgstledger,String igstledger,double cgstamount,double sgstamount,double igstamount,String date,String invoicenumber,
	    		String discountamount,String discountledger,boolean discountstat,String broker,String address,String deliverynotedate,String deliverynoteno,String packing_qty,
	    		String price_based_on,String packing_uom,String supplier_address,String print_to_name,boolean addplus,boolean addminus,String add_plus_ledgername,String add_minus_ledgername,
	    		double add_plus_amount,double add_minus_amount,boolean einvoice,boolean remarkstat,String remarks,String customerpincode,String gstinno,String panno,
	    		String ackno,String ackdate,String signedQRCode,String irnno,String waybill,boolean waybillwoincoice,boolean waybillcheck,String waybilldate) throws Exception{
	    	
	    	//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
	       
	       String Url = "http://192.168.10.100:9000/";
	      // String Url = "http://localhost:9000/";
	    	// Url = "http://192.168.1.203:9000/";
	       // String Url = prop.getProperty("tallyurl"); 
	        String SOAPAction = "";
	       
	        String Voucher = this.CreateRequest(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,item_name,item_amount,item_rate,
	        		item_passeditemqty,item_qty,item_total,roundoff_amt,roundoff_gl_ac,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,cgstamount,
	        		sgstamount,igstamount,date,invoicenumber,discountamount,discountledger,discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,
	        		price_based_on,packing_uom,supplier_address,print_to_name,addplus,addminus,add_plus_ledgername,add_minus_ledgername,add_plus_amount,add_minus_amount,
	        		einvoice,remarkstat,remarks,customerpincode,gstinno,panno,ackno,ackdate,signedQRCode,irnno,waybill,waybillwoincoice,waybillcheck,waybilldate);
//System.out.println(" Voucher : "+Voucher);
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
