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

public class Tallyrequest_TransportJV {
	

	public String CreateRequest(String date,String voucherno,String tds,double tdsamount,String roundoff,double roundoffamount,String transportername,double amount,boolean tdsstatus,boolean roundoffstatus,String transportationcharges,double transportationamount,boolean rounddrcr,String remarks,String referanceno)
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
	                + "<VOUCHER REMOTEID=\""+guid+"\"  VCHTYPE=\"TRANSPORTATION\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">\n"  
	                + "<DATE>"+date+"</DATE>\n"  
			        + "<VOUCHERTYPENAME>TRANSPORTATION</VOUCHERTYPENAME>\n"  
	                + "<NARRATION>"+remarks+"</NARRATION>\n"
			        + "<VOUCHERNUMBER>"+voucherno+"</VOUCHERNUMBER>\n" 
			        + "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>\n"
			        + "<VCHGSTCLASS/>\n"
				    + "<VCHENTRYMODE>As Voucher</VCHENTRYMODE>\n"
				    + "<DIFFACTUALQTY>No</DIFFACTUALQTY>\n"
				    + "<ISMSTFROMSYNC>No</ISMSTFROMSYNC>\n"
				    + "<ISDELETED>No</ISDELETED>\n"
				    + "<ISSECURITYONWHENENTERED>No</ISSECURITYONWHENENTERED>\n"
				    + "<ASORIGINAL>No</ASORIGINAL>\n"
				    + "<AUDITED>No</AUDITED>\n"
				    + "<FORJOBCOSTING>No</FORJOBCOSTING>\n"
				    + "<ISOPTIONAL>No</ISOPTIONAL>\n"
				    + "<EFFECTIVEDATE>"+date+"</EFFECTIVEDATE>\n"
	                + "<DIFFACTUALQTY>No</DIFFACTUALQTY>\n"
	                + "<ISMSTFROMSYNC>No</ISMSTFROMSYNC>\n"
                    + "<ISDELETED>No</ISDELETED>\n"
                    + "<ISSECURITYONWHENENTERED>No</ISSECURITYONWHENENTERED>\n"
				    + "<ASORIGINAL>No</ASORIGINAL>\n"
			        + "<AUDITED>No</AUDITED>\n"
			        + "<FORJOBCOSTING>No</FORJOBCOSTING>\n"
				    + "<ISOPTIONAL>No</ISOPTIONAL>\n"
				    + "<EFFECTIVEDATE>"+date+"</EFFECTIVEDATE>\n"
				    + "<USEFOREXCISE>No</USEFOREXCISE>\n"
				    + "<ISFORJOBWORKIN>No</ISFORJOBWORKIN>\n"
				    + "<ALLOWCONSUMPTION>No</ALLOWCONSUMPTION>\n"
				    + "<USEFORINTEREST>No</USEFORINTEREST>\n"
				    + "<USEFORGAINLOSS>No</USEFORGAINLOSS>\n"
				    + "<USEFORGODOWNTRANSFER>No</USEFORGODOWNTRANSFER>\n"
				    + "<USEFORCOMPOUND>No</USEFORCOMPOUND>\n"
				    + "<USEFORSERVICETAX>No</USEFORSERVICETAX>\n"
				    + "<ISONHOLD>No</ISONHOLD>\n"
				    + "<ISBOENOTAPPLICABLE>No</ISBOENOTAPPLICABLE>\n"
				    + "<ISGSTSECSEVENAPPLICABLE>No</ISGSTSECSEVENAPPLICABLE>\n"
				    + "<ISEXCISEVOUCHER>No</ISEXCISEVOUCHER>\n"
				    + "<EXCISETAXOVERRIDE>No</EXCISETAXOVERRIDE>\n"
				    + "<USEFORTAXUNITTRANSFER>No</USEFORTAXUNITTRANSFER>\n"
				    + "<IGNOREPOSVALIDATION>No</IGNOREPOSVALIDATION>\n"
				    + "<EXCISEOPENING>No</EXCISEOPENING>\n"
				    + "<USEFORFINALPRODUCTION>No</USEFORFINALPRODUCTION>\n"
				    + "<ISTDSOVERRIDDEN>No</ISTDSOVERRIDDEN>\n"
				    + "<ISTCSOVERRIDDEN>No</ISTCSOVERRIDDEN>\n"
				    + "<ISTDSTCSCASHVCH>No</ISTDSTCSCASHVCH>\n"
				    + "<INCLUDEADVPYMTVCH>No</INCLUDEADVPYMTVCH>\n"
				    + "<ISSUBWORKSCONTRACT>No</ISSUBWORKSCONTRACT>\n"
				    + "<ISVATOVERRIDDEN>No</ISVATOVERRIDDEN>\n"
				    + "<IGNOREORIGVCHDATE>No</IGNOREORIGVCHDATE>\n"
				    + "<ISVATPAIDATCUSTOMS>No</ISVATPAIDATCUSTOMS>\n"
				    + "<ISDECLAREDTOCUSTOMS>No</ISDECLAREDTOCUSTOMS>\n"
				    + "<ISSERVICETAXOVERRIDDEN>No</ISSERVICETAXOVERRIDDEN>\n"
				    + "<ISISDVOUCHER>No</ISISDVOUCHER>\n"
				    + "<ISEXCISEOVERRIDDEN>No</ISEXCISEOVERRIDDEN>\n"
				    + "<ISEXCISESUPPLYVCH>No</ISEXCISESUPPLYVCH>\n"
				    + "<ISGSTOVERRIDDEN>No</ISGSTOVERRIDDEN>\n"
				    + "<GSTNOTEXPORTED>No</GSTNOTEXPORTED>\n"
				    + "<IGNOREGSTINVALIDATION>No</IGNOREGSTINVALIDATION>\n"
				    + "<ISGSTREFUND>No</ISGSTREFUND>\n"
				    + "<OVRDNEWAYBILLAPPLICABILITY>No</OVRDNEWAYBILLAPPLICABILITY>\n"
				    + "<ISVATPRINCIPALACCOUNT>No</ISVATPRINCIPALACCOUNT>\n"
				    + "<IGNOREEINVVALIDATION>No</IGNOREEINVVALIDATION>\n"
				    + "<IRNJSONEXPORTED>No</IRNJSONEXPORTED>\n"
				    + "<IRNCANCELLED>No</IRNCANCELLED>\n"
				    + "<ISSHIPPINGWITHINSTATE>No</ISSHIPPINGWITHINSTATE>\n"
				    + "<ISOVERSEASTOURISTTRANS>No</ISOVERSEASTOURISTTRANS>\n"
				    + "<ISDESIGNATEDZONEPARTY>No</ISDESIGNATEDZONEPARTY>\n"
				    + "<ISCANCELLED>No</ISCANCELLED>\n"
				    + "<HASCASHFLOW>No</HASCASHFLOW>\n"
				    + "<ISPOSTDATED>No</ISPOSTDATED>\n"
				    + "<USETRACKINGNUMBER>No</USETRACKINGNUMBER>\n"
				    + "<ISINVOICE>No</ISINVOICE>\n"
				    + "<MFGJOURNAL>No</MFGJOURNAL>\n"
				    + "<HASDISCOUNTS>No</HASDISCOUNTS>\n"
				    + "<ASPAYSLIP>No</ASPAYSLIP>\n"
				    + "<ISCOSTCENTRE>No</ISCOSTCENTRE>\n"
				    + "<ISSTXNONREALIZEDVCH>No</ISSTXNONREALIZEDVCH>\n"
				    + "<ISEXCISEMANUFACTURERON>No</ISEXCISEMANUFACTURERON>\n"
				    + "<ISBLANKCHEQUE>No</ISBLANKCHEQUE>\n"
				    + "<ISVOID>No</ISVOID>\n"
				    + "<ORDERLINESTATUS>No</ORDERLINESTATUS>\n"
				    + "<VATISAGNSTCANCSALES>No</VATISAGNSTCANCSALES>\n"
				    + "<VATISPURCEXEMPTED>No</VATISPURCEXEMPTED>\n"
				    + "<ISVATRESTAXINVOICE>No</ISVATRESTAXINVOICE>\n"
				    + "<VATISASSESABLECALCVCH>No</VATISASSESABLECALCVCH>\n"
				    + "<ISVATDUTYPAID>Yes</ISVATDUTYPAID>\n"
				    + "<ISDELIVERYSAMEASCONSIGNEE>No</ISDELIVERYSAMEASCONSIGNEE>\n"
				    + "<ISDISPATCHSAMEASCONSIGNOR>No</ISDISPATCHSAMEASCONSIGNOR>\n"
				    + "<ISDELETEDVCHRETAINED>No</ISDELETEDVCHRETAINED>\n"
				    + "<CHANGEVCHMODE>No</CHANGEVCHMODE>\n"
				    + "<RESETIRNQRCODE>No</RESETIRNQRCODE>\n"
				    
				  /*  + "<ALTERID> 41677</ALTERID>\n"
				    + "<MASTERID> 29105</MASTERID>\n"
				    + "<VOUCHERKEY>193363722634120</VOUCHERKEY>\n"
				   */ 
				    + "<EWAYBILLDETAILS.LIST>      </EWAYBILLDETAILS.LIST>\n"
				    + "<EXCLUDEDTAXATIONS.LIST>      </EXCLUDEDTAXATIONS.LIST>\n"
				    + "<OLDAUDITENTRIES.LIST>      </OLDAUDITENTRIES.LIST>\n"
				    + "<ACCOUNTAUDITENTRIES.LIST>      </ACCOUNTAUDITENTRIES.LIST>\n"
				    + "<AUDITENTRIES.LIST>      </AUDITENTRIES.LIST>\n"
				    + "<DUTYHEADDETAILS.LIST>      </DUTYHEADDETAILS.LIST>\n"
				    + "<SUPPLEMENTARYDUTYHEADDETAILS.LIST>      </SUPPLEMENTARYDUTYHEADDETAILS.LIST>\n"
				    + "<EWAYBILLERRORLIST.LIST>      </EWAYBILLERRORLIST.LIST>\n"
				    + "<IRNERRORLIST.LIST>      </IRNERRORLIST.LIST>\n"
				    + "<INVOICEDELNOTES.LIST>      </INVOICEDELNOTES.LIST>\n"
				    + "<INVOICEORDERLIST.LIST>      </INVOICEORDERLIST.LIST>\n"
				    + "<INVOICEINDENTLIST.LIST>      </INVOICEINDENTLIST.LIST>\n"
				    + "<ATTENDANCEENTRIES.LIST>      </ATTENDANCEENTRIES.LIST>\n"
				    + "<ORIGINVOICEDETAILS.LIST>      </ORIGINVOICEDETAILS.LIST>\n"
				    + "<INVOICEEXPORTLIST.LIST>      </INVOICEEXPORTLIST.LIST>\n"
	               
				    +"<ALLLEDGERENTRIES.LIST>\n"
				    + "<LEDGERNAME>"+transportationcharges+"</LEDGERNAME>\n"
				    + "<GSTCLASS/>\n"
				    + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
				    + "<LEDGERFROMITEM>No</LEDGERFROMITEM>\n"
				    + "<REMOVEZEROENTRIES>No</REMOVEZEROENTRIES>\n"
				    + "<ISPARTYLEDGER>Yes</ISPARTYLEDGER>\n"
				    + "<ISLASTDEEMEDPOSITIVE>Yes</ISLASTDEEMEDPOSITIVE>\n"
				    + "<ISCAPVATTAXALTERED>No</ISCAPVATTAXALTERED>\n"
				    + "<ISCAPVATNOTCLAIMED>No</ISCAPVATNOTCLAIMED>\n"
				    + "<AMOUNT>-"+transportationamount+"</AMOUNT>\n"
				    + "<VATEXPAMOUNT>-"+transportationamount+"</VATEXPAMOUNT>\n"
				    + "<SERVICETAXDETAILS.LIST>       </SERVICETAXDETAILS.LIST>\n"
				    + "</ALLLEDGERENTRIES.LIST>\n";
   
				   
				    if(tdsstatus==true) 
				    {
				    	TXML+= "<ALLLEDGERENTRIES.LIST>\n"
								    + "<LEDGERNAME>"+tds+"</LEDGERNAME>\n"
								    + "<GSTCLASS/>\n"
								    + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
								    + "<LEDGERFROMITEM>No</LEDGERFROMITEM>\n"
								    + "<REMOVEZEROENTRIES>No</REMOVEZEROENTRIES>\n"
								    + "<ISPARTYLEDGER>Yes</ISPARTYLEDGER>\n"
								    + "<ISLASTDEEMEDPOSITIVE>No</ISLASTDEEMEDPOSITIVE>\n"
								    + "<ISCAPVATTAXALTERED>No</ISCAPVATTAXALTERED>\n"
								    + "<ISCAPVATNOTCLAIMED>No</ISCAPVATNOTCLAIMED>\n"
								    + "<AMOUNT>"+tdsamount+"</AMOUNT>\n"
								    + "<VATEXPAMOUNT>"+tdsamount+"</VATEXPAMOUNT>\n"
								    + "<SERVICETAXDETAILS.LIST>       </SERVICETAXDETAILS.LIST>\n"
								   
								    + "</ALLLEDGERENTRIES.LIST>\n";
				    }
	        
			        if(roundoffstatus==true) 
			        {
			        	 if(rounddrcr==true) 
		 				 {
			        		 TXML+= "<ALLLEDGERENTRIES.LIST>\n"
			 				     + "<LEDGERNAME>ROUNDING OFF (ADD/LESS)</LEDGERNAME>\n"
			 				     + "<GSTCLASS/>\n"
			 				   
			 				     + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
			 				   
			 				     + "<LEDGERFROMITEM>No</LEDGERFROMITEM>\n"
			 				     + "<REMOVEZEROENTRIES>No</REMOVEZEROENTRIES>\n"
			 				     + "<ISPARTYLEDGER>No</ISPARTYLEDGER>\n"
			 				     + "<ISLASTDEEMEDPOSITIVE>No</ISLASTDEEMEDPOSITIVE>\n"
			 				     + "<ISCAPVATTAXALTERED>No</ISCAPVATTAXALTERED>\n"
			 				     + "<ISCAPVATNOTCLAIMED>No</ISCAPVATNOTCLAIMED>\n"
			 				     + "<AMOUNT>"+Math.abs(roundoffamount) +"</AMOUNT>\n"
			 				     + "<VATEXPAMOUNT>"+Math.abs(roundoffamount) +"</VATEXPAMOUNT>\n"
			 				     + "<SERVICETAXDETAILS.LIST>       </SERVICETAXDETAILS.LIST>\n"
			 				   
			 				     + "</ALLLEDGERENTRIES.LIST>\n";
		 				 } 
			        	 else 
			        	 {
			        		 TXML+= "<ALLLEDGERENTRIES.LIST>\n"
				 				     + "<LEDGERNAME>ROUNDING OFF (ADD/LESS)</LEDGERNAME>\n"
				 				     + "<GSTCLASS/>\n"
				 				   
				 				     + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>\n"
				 				   
				 				     + "<LEDGERFROMITEM>No</LEDGERFROMITEM>\n"
				 				     + "<REMOVEZEROENTRIES>No</REMOVEZEROENTRIES>\n"
				 				     + "<ISPARTYLEDGER>No</ISPARTYLEDGER>\n"
				 				     + "<ISLASTDEEMEDPOSITIVE>Yes</ISLASTDEEMEDPOSITIVE>\n"
				 				     + "<ISCAPVATTAXALTERED>No</ISCAPVATTAXALTERED>\n"
				 				     + "<ISCAPVATNOTCLAIMED>No</ISCAPVATNOTCLAIMED>\n"
				 				     + "<AMOUNT>-"+roundoffamount+"</AMOUNT>\n"
				 				     + "<VATEXPAMOUNT>-"+roundoffamount+"</VATEXPAMOUNT>\n"
				 				     + "<SERVICETAXDETAILS.LIST>       </SERVICETAXDETAILS.LIST>\n"
				 				   
				 				     + "</ALLLEDGERENTRIES.LIST>\n";
			        	 }
			        	
			        	
			        	//rounddrcr = true means credit 
			        }
	        
			       
	        
			        TXML+= "<ALLLEDGERENTRIES.LIST>\n"
				    + "<LEDGERNAME>"+transportername+"</LEDGERNAME>\n"
				    + "<GSTCLASS/>\n"
				    + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>\n"
				    + "<LEDGERFROMITEM>No</LEDGERFROMITEM>\n"
				    + "<REMOVEZEROENTRIES>No</REMOVEZEROENTRIES>\n"
				    + "<ISPARTYLEDGER>Yes</ISPARTYLEDGER>\n"
				    + "<ISLASTDEEMEDPOSITIVE>No</ISLASTDEEMEDPOSITIVE>\n"
				    + "<ISCAPVATTAXALTERED>No</ISCAPVATTAXALTERED>\n"
				    + "<ISCAPVATNOTCLAIMED>No</ISCAPVATNOTCLAIMED>\n"
				    + "<AMOUNT>"+amount+"</AMOUNT>\n"
				    + "<VATEXPAMOUNT>"+amount+"</VATEXPAMOUNT>\n"
				    + "<SERVICETAXDETAILS.LIST>       </SERVICETAXDETAILS.LIST>\n"
				    + "<BILLALLOCATIONS.LIST>\n"
				    + "<NAME>"+referanceno+"</NAME>\n"
			        + "<BILLTYPE>Agst Ref</BILLTYPE>\n"
			        + "<TDSDEDUCTEEISSPECIALRATE>No</TDSDEDUCTEEISSPECIALRATE>\n"
			        + "<AMOUNT>"+amount+"</AMOUNT>\n"
			        + "<INTERESTCOLLECTION.LIST>        </INTERESTCOLLECTION.LIST>\n"
			        + "<STBILLCATEGORIES.LIST>        </STBILLCATEGORIES.LIST>\n"
			        + "</BILLALLOCATIONS.LIST>\n"
				    + "</ALLLEDGERENTRIES.LIST>\n"
			        
				    
			        + "</VOUCHER>\n"
			        + "</TALLYMESSAGE>\n"
			        + "</REQUESTDATA>\n"
			        + "</IMPORTDATA>\n"
			        + "</BODY>\n"
			        + "</ENVELOPE>\n";
	        		
	       
	        System.out.println("TXML "+ TXML);
	        
	        return TXML;
	    }
	  
	   
	    public String SendToTally(String date,String voucherno,String tds,double tdsamount,String roundoff,double roundoffamount,String transportername,double amount,boolean tdsstatus,boolean roundoffstatus,String transportationcharges,double transportationamount,boolean rounddrcr,String remarks,String referanceno) throws Exception{
	    	
	    	
	        //String Url = "http://192.168.10.100:9000/";  
	    	
	    	String Url = "http://192.168.10.201:9000/";
	   
	        String SOAPAction = "";
	       
	        String Voucher = this.CreateRequest(date,voucherno,tds,tdsamount,roundoff,roundoffamount,transportername,amount,tdsstatus,roundoffstatus,transportationcharges,transportationamount,rounddrcr,remarks,referanceno);

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
