package com.AnkitIndia.Exporttotally;

public class backupitemxml {


    /*           String TXML = null;

				if(no_kg == true) 
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
					        "     <SVCURRENTCOMPANY>AAPL</SVCURRENTCOMPANY>\n" +
					        "    </STATICVARIABLES>\n" +
					        "   </REQUESTDESC>\n" +
					        "   <REQUESTDATA>\n" +
					        "    <TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
					        "    <STOCKITEM NAME=\""+itemname+"\" "+"RESERVEDNAME=\"\" Action = \"Create\">\n"+
					        "    <PARENT>"+itemcategory+"</PARENT>\n"+
					    "    <CATEGORY/>\n"+
					        "    <GSTAPPLICABLE>"+"Not Applicable"+"</GSTAPPLICABLE>\n"+
					        "    <BASEUNITS>"+stockkeeping+"</BASEUNITS>\n"+
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

					
					TXML = "<ENVELOPE>\n" +
					        " <HEADER>\n" +
					        "  <TALLYREQUEST>Import Data</TALLYREQUEST>\n" +
					        " </HEADER>\n" +
					        " <BODY>\n" +
					        "  <IMPORTDATA>\n" +
					        "   <REQUESTDESC>\n" +
					        "    <REPORTNAME>All Masters</REPORTNAME>\n" +
					        "    <STATICVARIABLES>\n" +
					        "     <SVCURRENTCOMPANY>AAPL</SVCURRENTCOMPANY>\n" +
					        "    </STATICVARIABLES>\n" +
					        "   </REQUESTDESC>\n" +
					        "   <REQUESTDATA>\n" +
					        "    <TALLYMESSAGE xmlns:UDF=\"TallyUDF\">\n" +
					        "    <STOCKITEM NAME=\""+itemname+"\" "+"RESERVEDNAME=\"\" Action = \"Create\">\n"+
					        "    <PARENT>"+itemcategory+"</PARENT>\n"+
					          "<CATEGORY/>\n";
					//System.out.println("TXML1 "+TXML );
					//System.out.println("comp "+hsncode.compareToIgnoreCase("0"));
					    String TXML3 = null;				    
					    if(hsncode.compareToIgnoreCase("0")== 0 || hsncode.compareToIgnoreCase("null")== 0) 
					        {
					    	//System.out.println("TXML22v "+TXML);
							    	TXML3 ="<GSTAPPLICABLE>"+"Not Applicable"+"</GSTAPPLICABLE>\n";
					        }
					        else 
					        {
					        	//System.out.println("TXML3 "+TXML);
					        	TXML3 ="  <GSTAPPLICABLE>&#4; Applicable</GSTAPPLICABLE>\n";        	
					        }
					    
					    TXML=TXML+ TXML3;     
					    
					   // System.out.println("TXML2 "+TXML);
					         
	String TXML2 = null;     
					        if(generalstatus == true) 
					        {
					        	TXML2="<BASEUNITS>"+stockkeeping+"</BASEUNITS>\n"+//stockkeeping=QTYS
						      			"<ADDITIONALUNITS>BAGS</ADDITIONALUNITS>\n"+//pcs
						      			"<GSTREPUOM>"+stockkeeping+"-QUINTAL</GSTREPUOM>\n"+
									    "<DENOMINATOR> 1</DENOMINATOR>\n"+
						        		"<CONVERSION>"+conversion+"</CONVERSION>\n";
							}
							else 
							{
															
								TXML2="<BASEUNITS>BAGS</BASEUNITS>\n"+
									    "<ADDITIONALUNITS>"+stockkeeping+"</ADDITIONALUNITS>\n"+
									    "<GSTREPUOM>BAG-BAGS</GSTREPUOM>\n"+
						      			"<DENOMINATOR> 1</DENOMINATOR>\n"+
						        		"<CONVERSION> "+conversion+"</CONVERSION>\n";
				      		}
					        TXML = TXML+TXML2;
					        
					        
					        if(taxstatus == true) 
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
								         "<GSTRATE>"+tax_rate/2+"</GSTRATE>\n"+
								        "</RATEDETAILS.LIST>\n"+
								        "<RATEDETAILS.LIST>\n"+
								         "<GSTRATEDUTYHEAD>State Tax</GSTRATEDUTYHEAD>\n"+
								         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
								         "<GSTRATE> "+tax_rate/2+"</GSTRATE>\n"+
								        "</RATEDETAILS.LIST>\n"+
								        "<RATEDETAILS.LIST>\n"+
								         "<GSTRATEDUTYHEAD>Integrated Tax</GSTRATEDUTYHEAD>\n"+
								         "<GSTRATEVALUATIONTYPE>Based on Value</GSTRATEVALUATIONTYPE>\n"+
								         "<GSTRATE> "+tax_rate+"</GSTRATE>\n"+
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
					        
					        
					        
				}       
			
			
			
				
			// System.out.println("TXML"+TXML);
			  return TXML;
		*/

}
