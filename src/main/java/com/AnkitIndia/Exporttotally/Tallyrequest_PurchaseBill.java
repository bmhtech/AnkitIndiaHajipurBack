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

public class Tallyrequest_PurchaseBill {
	
	  public String CreateRequest(String pur_bill_no, String bill_date,String supplier,String net_payable_party,String roundoff_amt,String roundoff_gl_ac,String item_name[],String item_amount[],String item_rate[],String item_passeditemqty[],String item_qty[],String charge_name[],String charge_amount[],String charge_add_less[],Double item_total,String truckno,String itemSubGroupName,int rounfoffdrcr,boolean roundoffstatus,boolean includecharge_matrix,String item_name_ledger[],String date,boolean addplus,boolean addminus,String add_plus_ledgername,String add_minus_ledgername,double add_plus_amount,double add_minus_amount,String supplieraddress,String supp_ref_doc_date,String supp_ref_docno,String purchaseinvoiceno,String supplierstate,
			  String discountamount[],String discountledger[],boolean discountstat,boolean gst_valid,boolean gststatus,String cgstledger,String sgstledger,String igstledger,double cgstamount,double sgstamount,double igstamount,String pincode,String gstinno)
	    {             
	    
//roundoffstatus,includecharge_matrix

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
	                + "<VOUCHER REMOTEID=\""+guid+"\" VCHTYPE=\"New Purchase\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">\n"   
	                
	                + "<ADDRESS.LIST>\n"   
	                + "<ADDRESS>"+supplieraddress+"</ADDRESS>\n"   
				    + "</ADDRESS.LIST>\n"   
	                
	           // + "<DATE>20220402</DATE>\n"
	                + "<DATE>"+date+"</DATE>\n"
	            //   +"<REFERENCEDATE>20220402</REFERENCEDATE>\n"
	                +"<REFERENCEDATE>"+supp_ref_doc_date+"</REFERENCEDATE>\n"//supp_ref_doc_date
	                 
		        
		             
		           +"<STATENAME>"+supplierstate+"</STATENAME>\n"
		             
		             //+"<VOUCHERTYPENAME>Purchase</VOUCHERTYPENAME>\n"
		             +"<VOUCHERTYPENAME>New Purchase</VOUCHERTYPENAME>\n"
		             +"<COUNTRYOFRESIDENCE>India</COUNTRYOFRESIDENCE>\n"
		             +" <PARTYGSTIN>"+gstinno+"</PARTYGSTIN>\n"
	                 +" <PARTYNAME>"+supplier+"</PARTYNAME>\n"
	                 +" <PARTYLEDGERNAME>"+supplier+"</PARTYLEDGERNAME>\n"
	                 +"<REFERENCE>"+supp_ref_docno+"</REFERENCE>\n"
	                 +"<PARTYMAILINGNAME>"+supplier+"</PARTYMAILINGNAME>\n"
		             +" <PARTYPINCODE>"+pincode+"</PARTYPINCODE>\n"
		             +" <CONSIGNEEMAILINGNAME>ANKIT INDIA LIMITED</CONSIGNEEMAILINGNAME>\n"
		             +"<CONSIGNEEPINCODE>844125</CONSIGNEEPINCODE>\n"
		             +"<CONSIGNEESTATENAME>Bihar</CONSIGNEESTATENAME>\n"
	        
	               // + "<VOUCHERNUMBER>"+"2"+"</VOUCHERNUMBER>\n"
	                  +"<VOUCHERNUMBER>"+purchaseinvoiceno+"</VOUCHERNUMBER>\n"
	              
	                 + "<PARTYLEDGERNAME>"+supplier+"</PARTYLEDGERNAME>\n"
	                 + "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>\n" 
	                 +"<DIFFACTUALQTY>Yes</DIFFACTUALQTY>\n"
	                 + "<BASICSHIPVESSELNO>"+truckno+"</BASICSHIPVESSELNO>\n"
	                 + "<ALLLEDGERENTRIES.LIST>\n"
	                 + "<LEDGERNAME>"+supplier+"</LEDGERNAME>\n"
	                 + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
	                 + "<AMOUNT>"+net_payable_party+"</AMOUNT>\n"   
	                 + "<BILLALLOCATIONS.LIST>\n"
	              
	             
					 + "<NAME>"+supp_ref_docno+"</NAME>"//supp_ref_docno
					 + "<BILLTYPE>New Ref</BILLTYPE>"
					 + "<TDSDEDUCTEEISSPECIALRATE>No</TDSDEDUCTEEISSPECIALRATE>"
					 + "<AMOUNT>"+net_payable_party+"</AMOUNT>"
	              
	              
	              //  + "<AMOUNT>625563.00</AMOUNT>\n"
	                + "<INTERESTCOLLECTION.LIST></INTERESTCOLLECTION.LIST>\n"
	                + "<STBILLCATEGORIES.LIST></STBILLCATEGORIES.LIST>\n"
	                + "</BILLALLOCATIONS.LIST>\n"
	                + "</ALLLEDGERENTRIES.LIST>\n";
	                
	              
	        String TXML2 = "";   
	                for(int i=0;i<item_name.length;i++)
	                {
	                	
	               // TXML2+="<STOCKITEMNAME>"+item_name[i]+"</STOCKITEMNAME>\n"
        	  TXML2 += "<ALLLEDGERENTRIES.LIST>\n"
  	                + "<LEDGERNAME>"+item_name_ledger[i]+"</LEDGERNAME>\n"
  	                + "<GSTCLASS/>\n"
  	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
  	                + "<AMOUNT>"+item_amount[i]+"</AMOUNT>\n"
  	                + "<VATEXPAMOUNT>"+item_amount[i]+"</VATEXPAMOUNT>\n"
  	                + "<INVENTORYALLOCATIONS.LIST>\n"
  	                + "<STOCKITEMNAME>"+item_name[i]+"</STOCKITEMNAME>\n"
	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	                + "<RATE>"+item_rate[i]+"/"+item_qty[i]+"</RATE>\n"
	                
	                + "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"
	                +" <ACTUALQTY>"+item_passeditemqty[i]+item_qty[i]+"</ACTUALQTY>\n"
	                + "<BILLEDQTY>"+item_passeditemqty[i]+item_qty[i]+"</BILLEDQTY>\n"
	                
	                + "<BATCHALLOCATIONS.LIST>\n"
	             	+ "<AMOUNT>-"+item_amount[i]+"</AMOUNT>\n"
	             	+" <ACTUALQTY>"+item_passeditemqty[i]+item_qty[i]+"</ACTUALQTY>\n"
	                + "<BILLEDQTY>"+item_passeditemqty[i]+item_qty[i]+"</BILLEDQTY>\n"
	                + "</BATCHALLOCATIONS.LIST>\n"
        	       
	                + "</INVENTORYALLOCATIONS.LIST>\n"
	                +"</ALLLEDGERENTRIES.LIST>\n";
	                }
	                
	                TXML=TXML+ TXML2 ;
	                
	                
	             //here new 
	                
	                String TXML7 = "";
	                //gst_valid if false then whole gst wont show 
	                //if    gststatus true  then cgst and sgst will show  if not then only igst will show
	               
	                if(gst_valid==true) 
	                {
	                	
	                	if(gststatus == true) 
	    	                {
	    	                	 TXML7= "<ALLLEDGERENTRIES.LIST>\n"
	    	         	                + "<LEDGERNAME>"+cgstledger+"</LEDGERNAME>\n"
	    	         	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	    	         	                + "<AMOUNT>-"+cgstamount+"</AMOUNT>\n"
	    	         	                + "</ALLLEDGERENTRIES.LIST>\n"
	    	         	                
	    	         	                + "<ALLLEDGERENTRIES.LIST>\n"
	    	         	                + "<LEDGERNAME>"+sgstledger+"</LEDGERNAME>\n"
	    	         	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	    	         	                + "<AMOUNT>-"+sgstamount+"</AMOUNT>\n"
	    	         	                + "</ALLLEDGERENTRIES.LIST>\n";
	    	                	 TXML=TXML+TXML7;
	    	                }
	    	                else 
	    	                {
	    	                	 TXML7= "<ALLLEDGERENTRIES.LIST>\n"
	    	         	                + "<LEDGERNAME>"+igstledger+"</LEDGERNAME>\n"
	    	         	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
	    	         	                + "<AMOUNT>-"+igstamount+"</AMOUNT>\n"
	    	         	                + "</ALLLEDGERENTRIES.LIST>\n";
	    	                	 TXML=TXML+TXML7;
	    	                }
	                }
	                String TXML8 = "";
	                if(discountstat==true) 
	                {
	                	for(int i=0;i<discountledger.length;i++) 
	                	{
	                		TXML8+= "<ALLLEDGERENTRIES.LIST>\n"
	                                + "<LEDGERNAME>"+discountledger[i]+"</LEDGERNAME>\n"
	          	                    + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
	          	                    + "<AMOUNT>"+discountamount[i]+"</AMOUNT>\n"
	          	                    + "</ALLLEDGERENTRIES.LIST>\n";
	                		
	                	}
	                	
	                	TXML=TXML+TXML8;
	                	
	                }
	                
	                //here new ends
	                
	               
	                
	              if(includecharge_matrix == true)  
	              {
	            	  
	            	  String TXML3= "";   
		                for(int k=0;k<charge_name.length;k++)
		                {
		                	
		                	if(charge_add_less[k].compareToIgnoreCase("less")==0) 
		                	{
		                		  TXML3+= "<ALLLEDGERENTRIES.LIST>\n"
		          		                + "<LEDGERNAME>"+charge_name[k]+"</LEDGERNAME>\n"
		          		                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
		          		                + "<AMOUNT>"+charge_amount[k]+"</AMOUNT>\n"          
		          		                + "</ALLLEDGERENTRIES.LIST>\n";
		                	}
		                	else 
		                	{
		                		  TXML3+= "<ALLLEDGERENTRIES.LIST>\n"
		          		                + "<LEDGERNAME>"+charge_name[k]+"</LEDGERNAME>\n"
		          		                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
		          		                + "<AMOUNT>-"+charge_amount[k]+"</AMOUNT>\n"          
		          		                + "</ALLLEDGERENTRIES.LIST>\n";
		                	}
		              
		                
		                }
		                
		                
		                TXML=TXML+ TXML3;	
	              }
	               //,boolean addplus,boolean addminus,String add_plus_ledgername,
	              //String add_minus_ledgername,double add_plus_amount,double add_minus_amount
	              
	              if(addplus == true) 
	              {
	            	  TXML=TXML + "<ALLLEDGERENTRIES.LIST>\n"
	                            + "<LEDGERNAME>"+add_plus_ledgername+"</LEDGERNAME>\n"
       	                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
       	                + "<AMOUNT>-"+add_plus_amount+"</AMOUNT>\n"          
       	                + "</ALLLEDGERENTRIES.LIST>\n"  ;
	              }
	              if(addminus == true) 
	              {
	            	  TXML=TXML + "<ALLLEDGERENTRIES.LIST>\n"
	           	    			+ "<LEDGERNAME>"+add_minus_ledgername+"</LEDGERNAME>\n"
	           	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
	           	                + "<AMOUNT>"+add_minus_amount+"</AMOUNT>\n"          
	           	                + "</ALLLEDGERENTRIES.LIST>\n";
	            	 
	              }
		         
	                if(roundoffstatus == true) 
		           {
		        	   //1 means dr and 0 emans cr
				        	   if(rounfoffdrcr==1) 
			 	                {
			           	    	TXML=TXML + "<ALLLEDGERENTRIES.LIST>\n"
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
				           	    else if(rounfoffdrcr==0) 
				           	    {
				           	    	TXML=TXML + "<ALLLEDGERENTRIES.LIST>\n"
				           	    			+ "<LEDGERNAME>"+roundoff_gl_ac+"</LEDGERNAME>\n"
				           	                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
				           	                + "<AMOUNT>"+roundoff_amt.substring(1, roundoff_amt.length())+"</AMOUNT>\n"          
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
				           	    	TXML=TXML  + "</VOUCHER>\n"
				           	                + "</TALLYMESSAGE>\n"
				           	                + "</REQUESTDATA>\n"
				           	                + "</IMPORTDATA>\n"
				           	                + "</BODY>\n"
				           	                + "</ENVELOPE>";
				           	        
				           	    }
		                		 
		           }
	                else 
	                {
	                	TXML=TXML  + "</VOUCHER>\n"
	           	                + "</TALLYMESSAGE>\n"
	           	                + "</REQUESTDATA>\n"
	           	                + "</IMPORTDATA>\n"
	           	                + "</BODY>\n"
	           	                + "</ENVELOPE>";
	                	
	                }
            	  
	            
	              System.out.println("TXML "+ TXML);
	        
	        return TXML;
	    }
	  
	   
	    public String SendToTally(String pur_bill_no, String bill_date,String supplier,String net_payable_party,String roundoff_amt,String roundoff_gl_ac,String item_name[],String item_amount[],String item_rate[],String item_passeditemqty[],String item_qty[],String charge_name[],String charge_amount[],String charge_add_less[],Double item_total,String truckno,String itemSubGroupName,int rounfoffdrcr,boolean roundoffstatus,boolean includecharge_matrix,String item_name_ledger[],String date,boolean addplus,boolean addminus,String add_plus_ledgername,String add_minus_ledgername,double add_plus_amount,double add_minus_amount,String supplieraddress,String supp_ref_doc_date,String supp_ref_docno,String purchaseinvoiceno,String supplierstate,String discountamount[],String discountledger[],boolean discountstat,boolean gst_valid,boolean gststatus,String cgstledger,String sgstledger,String igstledger,double cgstamount,double sgstamount,double igstamount,String pincode,String gstinno) throws Exception{
	    	
	    	//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
	       
	       //String Url = "http://192.168.10.100:9000/";  
	    	String Url = "http://192.168.10.201:9000/";
	    //	String Url = "http://localhost:9000/";
	  //  String Url = "http://192.168.1.213:9000/";
	        //String Url = prop.getProperty("tallyurl"); 
	        String SOAPAction = "";
	       
	        String Voucher = this.CreateRequest(pur_bill_no,bill_date,supplier,net_payable_party,roundoff_amt,roundoff_gl_ac, item_name,item_amount,item_rate,item_passeditemqty ,item_qty, charge_name,charge_amount,charge_add_less,item_total,truckno,itemSubGroupName,rounfoffdrcr,roundoffstatus,includecharge_matrix,item_name_ledger,date,addplus,addminus,add_plus_ledgername,add_minus_ledgername,add_plus_amount,add_minus_amount,supplieraddress,supp_ref_doc_date,supp_ref_docno,purchaseinvoiceno,supplierstate,discountamount,discountledger,discountstat,gst_valid,gststatus,cgstledger,sgstledger,igstledger,cgstamount,sgstamount,igstamount,pincode,gstinno);
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
System.out.println(" b :: "+String.valueOf(b.length));
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
	        System.out.println("input :: "+in.readLine());

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
