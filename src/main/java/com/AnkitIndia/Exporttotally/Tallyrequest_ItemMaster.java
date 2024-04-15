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

public class Tallyrequest_ItemMaster {
	
	


	
	
    public String CreateRequest(String itemname,String itemcategory,String masterstock,boolean taxinclude,String packingmasterstock,String hsncode,double conversion,double tax,boolean pakcingstatus)
    {             
    
    	String TXML = null;

        if(pakcingstatus == true) 
        {
        	TXML = "<ENVELOPE>\n" +
			        " <HEADER>\n" +
			        "  <TALLYREQUEST>Import Data</TALLYREQUEST>\n" +
			        " </HEADER>\n" +
			        " <BODY>\n" +
			        "  <IMPORTDATA>\n" +
			        "   <REQUESTDESC>\n" +
			        "    <REPORTNAME>All Masters</REPORTNAME>\n" +
			        "    <STATICVARIABLES>\n" +
			        "     <SVCURRENTCOMPANY>ANKIT INDIA LIMITED</SVCURRENTCOMPANY>\n" +
			        "    </STATICVARIABLES>\n" +
			        "   </REQUESTDESC>\n" +
			        "   <REQUESTDATA>\n" +
			        "    <TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
			        "    <STOCKITEM NAME=\""+itemname+"\" "+"RESERVEDNAME=\"\" Action = \"Create\">\n"+
			        "    <PARENT>"+itemcategory+"</PARENT>\n"+
			    "    <CATEGORY/>\n";
			        
 String TXML2 = null;				    
    		    if(hsncode.compareToIgnoreCase("0")== 0 || hsncode.compareToIgnoreCase("null")== 0) 
    		        {
    		    	
    		    	    TXML2 ="<GSTAPPLICABLE>"+"Not Applicable"+"</GSTAPPLICABLE>\n";
    		        }
    		        else 
    		        {
    		        	
    		        	TXML2 ="  <GSTAPPLICABLE>&#4; Applicable</GSTAPPLICABLE>\n";        	
    		        }
    		    
    		    TXML=TXML+ TXML2;     
    	       
/*
            	TXML+="<BASEUNITS>"+masterstock+"</BASEUNITS>\n"+
    	      			"<ADDITIONALUNITS>"+masterstock+"</ADDITIONALUNITS>\n"+
    	      			"<GSTREPUOM>"+masterstock+"-"+masterstock+"</GSTREPUOM>\n"+
    				    "<DENOMINATOR>1</DENOMINATOR>\n"+
    	        		"<CONVERSION>1</CONVERSION>\n";
    		
*/    		    
    		    TXML+="<BASEUNITS>"+masterstock+"</BASEUNITS>\n";
    		    
    	        if(taxinclude == true) 
    	        {
    	        	TXML+=	"<GSTDETAILS.LIST>\n"+
    		        		"<APPLICABLEFROM>20220401</APPLICABLEFROM>\n"+
    		                "<CALCULATIONTYPE>On Value</CALCULATIONTYPE>\n"+
    						"<HSNCODE>"+hsncode+"</HSNCODE>\n"+
    						"<HSN>"+itemname+"</HSN>\n"+
    						"<TAXABILITY>Taxable</TAXABILITY>\n"+
    				        "<STATEWISEDETAILS.LIST>\n"+
    				        "<STATENAME>&#4; Any</STATENAME>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Central Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         "<GSTRATE>"+tax/2+"</GSTRATE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         "<GSTRATE> "+tax/2+"</GSTRATE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Integrated Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         "<GSTRATE> "+tax+"</GSTRATE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess on Qty</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Quantity</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<GSTSLABRATES.LIST>        </GSTSLABRATES.LIST>\n"+
    				       "</STATEWISEDETAILS.LIST>\n"+
    				        
    		        
    						"    </GSTDETAILS.LIST>\n"+        
    				        "    <LANGUAGENAME.LIST>\n"+
    				        "    <NAME.LIST TYPE=\"String\">\n"+
    				        "    <NAME>"+itemname+"</NAME>\n"+
    				        "    </NAME.LIST>\n"+
    				        "    </LANGUAGENAME.LIST>\n"+
    				        "    </STOCKITEM>\n"+
    				        "    </TALLYMESSAGE>\n" +
    				        "   </REQUESTDATA>\n" +
    				        "  </IMPORTDATA>\n" +
    				        " </BODY>\n" +
    				        "</ENVELOPE>";

    	        }
    	        else 
    	        {
    	        	TXML+=	"<GSTDETAILS.LIST>\n"+
    		        		"<APPLICABLEFROM>20220401</APPLICABLEFROM>\n"+
    		                "<CALCULATIONTYPE>On Value</CALCULATIONTYPE>\n"+
    						"<HSNCODE>"+hsncode+"</HSNCODE>\n"+
    						"<HSN>"+itemname+"</HSN>\n"+
    						"<TAXABILITY>Exempt</TAXABILITY>\n"+
    				        "<STATEWISEDETAILS.LIST>\n"+
    				        "<STATENAME>Any</STATENAME>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Central Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         //<GSTRATE> 2.50</GSTRATE>
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         //<GSTRATE> 2.50</GSTRATE>
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Integrated Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         //<GSTRATE> 5</GSTRATE>
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess on Qty</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Quantity</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<GSTSLABRATES.LIST>        </GSTSLABRATES.LIST>\n"+
    				       "</STATEWISEDETAILS.LIST>\n"+
    				        
    		        
    						"    </GSTDETAILS.LIST>\n"+        
    				        "    <LANGUAGENAME.LIST>\n"+
    				        "    <NAME.LIST TYPE=\"String\">\n"+
    				        "    <NAME>"+itemname+"</NAME>\n"+
    				        "    </NAME.LIST>\n"+
    				        "    </LANGUAGENAME.LIST>\n"+
    				        "    </STOCKITEM>\n"+
    				        "    </TALLYMESSAGE>\n" +
    				        "   </REQUESTDATA>\n" +
    				        "  </IMPORTDATA>\n" +
    				        " </BODY>\n" +
    				        "</ENVELOPE>";

    	        	
    	        }
System.out.println("TXML  :: " + TXML);
    		  return TXML;

        }
        else 
        {
        	TXML = "<ENVELOPE>\n" +
    		        " <HEADER>\n" +
    		        "  <TALLYREQUEST>Import Data</TALLYREQUEST>\n" +
    		        " </HEADER>\n" +
    		        " <BODY>\n" +
    		        "  <IMPORTDATA>\n" +
    		        "   <REQUESTDESC>\n" +
    		        "    <REPORTNAME>All Masters</REPORTNAME>\n" +
    		        "    <STATICVARIABLES>\n" +
    		        "     <SVCURRENTCOMPANY>ANKIT INDIA LIMITED</SVCURRENTCOMPANY>\n" +
    		        "    </STATICVARIABLES>\n" +
    		        "   </REQUESTDESC>\n" +
    		        "   <REQUESTDATA>\n" +
    		        "    <TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
    		        "    <STOCKITEM NAME=\""+itemname+"\" "+"RESERVEDNAME=\"\" Action = \"Create\">\n"+
    		        "    <PARENT>"+itemcategory+"</PARENT>\n"+
    		        "    <CATEGORY/>\n";
    		        
    		    String TXML2 = null;				    
    		    if(hsncode.compareToIgnoreCase("0")== 0 || hsncode.compareToIgnoreCase("null")== 0) 
    		        {
    		    	
    		    	    TXML2 ="<GSTAPPLICABLE>"+"Not Applicable"+"</GSTAPPLICABLE>\n";
    		        }
    		        else 
    		        {
    		        	
    		        	TXML2 ="  <GSTAPPLICABLE>&#4; Applicable</GSTAPPLICABLE>\n";        	
    		        }
    		    
    		    TXML=TXML+ TXML2;     
    	        
    		    

            	TXML+="<BASEUNITS>"+masterstock+"</BASEUNITS>\n"+
    	      			"<ADDITIONALUNITS>"+packingmasterstock+"</ADDITIONALUNITS>\n"+
    	      			"<GSTREPUOM>"+masterstock+"-"+masterstock+"</GSTREPUOM>\n"+
    				    "<DENOMINATOR>"+conversion+" </DENOMINATOR>\n"+
    	        		"<CONVERSION>1</CONVERSION>\n";
    		
    		    
    		    
    	        if(taxinclude == true) 
    	        {
    	        	TXML+=	"<GSTDETAILS.LIST>\n"+
    		        		"<APPLICABLEFROM>20220401</APPLICABLEFROM>\n"+
    		                "<CALCULATIONTYPE>On Value</CALCULATIONTYPE>\n"+
    						"<HSNCODE>"+hsncode+"</HSNCODE>\n"+
    						"<HSN>"+itemname+"</HSN>\n"+
    						"<TAXABILITY>Taxable</TAXABILITY>\n"+
    				        "<STATEWISEDETAILS.LIST>\n"+
    				        "<STATENAME>&#4; Any</STATENAME>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Central Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         "<GSTRATE>"+tax/2+"</GSTRATE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         "<GSTRATE> "+tax/2+"</GSTRATE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Integrated Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         "<GSTRATE> "+tax+"</GSTRATE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess on Qty</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Quantity</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<GSTSLABRATES.LIST>        </GSTSLABRATES.LIST>\n"+
    				       "</STATEWISEDETAILS.LIST>\n"+
    				        
    		        
    						"    </GSTDETAILS.LIST>\n"+        
    				        "    <LANGUAGENAME.LIST>\n"+
    				        "    <NAME.LIST TYPE=\"String\">\n"+
    				        "    <NAME>"+itemname+"</NAME>\n"+
    				        "    </NAME.LIST>\n"+
    				        "    </LANGUAGENAME.LIST>\n"+
    				        "    </STOCKITEM>\n"+
    				        "    </TALLYMESSAGE>\n" +
    				        "   </REQUESTDATA>\n" +
    				        "  </IMPORTDATA>\n" +
    				        " </BODY>\n" +
    				        "</ENVELOPE>";

    	        }
    	        else 
    	        {
    	        	TXML+=	"<GSTDETAILS.LIST>\n"+
    		        		"<APPLICABLEFROM>20220401</APPLICABLEFROM>\n"+
    		                "<CALCULATIONTYPE>On Value</CALCULATIONTYPE>\n"+
    						"<HSNCODE>"+hsncode+"</HSNCODE>\n"+
    						"<HSN>"+itemname+"</HSN>\n"+
    						"<TAXABILITY>Exempt</TAXABILITY>\n"+
    				        "<STATEWISEDETAILS.LIST>\n"+
    				        "<STATENAME>Any</STATENAME>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Central Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         //<GSTRATE> 2.50</GSTRATE>
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         //<GSTRATE> 2.50</GSTRATE>
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Integrated Tax</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				         //<GSTRATE> 5</GSTRATE>
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>Cess on Qty</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Quantity</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<RATEDETAILS.LIST>\n"+
    				         "<GSTRATEDUTYHEAD>State Cess</GSTRATEDUTYHEAD>\n"+
    				         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
    				        "</RATEDETAILS.LIST>\n"+
    				        "<GSTSLABRATES.LIST>        </GSTSLABRATES.LIST>\n"+
    				       "</STATEWISEDETAILS.LIST>\n"+
    				        
    		        
    						"    </GSTDETAILS.LIST>\n"+        
    				        "    <LANGUAGENAME.LIST>\n"+
    				        "    <NAME.LIST TYPE=\"String\">\n"+
    				        "    <NAME>"+itemname+"</NAME>\n"+
    				        "    </NAME.LIST>\n"+
    				        "    </LANGUAGENAME.LIST>\n"+
    				        "    </STOCKITEM>\n"+
    				        "    </TALLYMESSAGE>\n" +
    				        "   </REQUESTDATA>\n" +
    				        "  </IMPORTDATA>\n" +
    				        " </BODY>\n" +
    				        "</ENVELOPE>";

    	        	
    	        }
System.out.println("TXML  :: " + TXML);
    		  return TXML;

        }
		
    }
   
    public String SendToTally(String itemname,String itemcategory,String masterstock,boolean taxinclude,String packingmasterstock,String hsncode,double conversion,double tax,boolean pakcingstatus) throws Exception{
    	
    	//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
       
        //String Url = "http://192.168.10.100:9000/";
    	String Url = "http://192.168.10.201:9000/";
    	// String Url = "http://localhost:9000/";
        System.out.println("hello  " + Url );
    	//String Url = "http://192.168.10.105:9000/";     
     //  String Url = prop.getProperty("tallyurl"); 
        String SOAPAction = "";
       
        String Voucher = this.CreateRequest(itemname,itemcategory,masterstock,taxinclude,packingmasterstock,hsncode,conversion,tax,pakcingstatus);
System.out.println("Voucher :: "+Voucher);
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
        String y="";
        	y=getvalue(x,"CREATED");
        	
        
       
        String z=getvalue(x,"ALTERED");
        return x+"||"+y+"||"+z;
    }
    
    public String getvalue(String buffer, String tagname) 
    {   
        String startTag, endTag,elementdata = null;
        int startposition,endposition;
        try 
        {
        	System.out.println("tagname :: "+tagname);
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
