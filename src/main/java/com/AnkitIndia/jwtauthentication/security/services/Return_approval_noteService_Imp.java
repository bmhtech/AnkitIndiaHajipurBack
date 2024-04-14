package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Return_approval_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_docs;
import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_party_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_trans_info;
import com.AnkitIndia.jwtauthentication.model.Return_approval_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challanRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_Service_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_item_dtls_for_jobworkRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_item_dtls_for_jobwork_priceRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_party_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_shipment_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_weight_dtlRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;

@Service
@Repository
public class Return_approval_noteService_Imp implements Return_approval_noteService{
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Return_approval_broker_dtlsRepository return_approval_broker_dtlsRepository;
	
	@Autowired
	Return_approval_docsRepository return_approval_docsRepository;
	
	@Autowired
	Return_approval_item_dtlsRepository return_approval_item_dtlsRepository;
	
	@Autowired
	Return_approval_party_dtlsRepository return_approval_party_dtlsRepository;
	
	@Autowired
	Return_approval_shipment_dtlsRepository return_approval_shipment_dtlsRepository;
	
	@Autowired
	Return_approval_trans_infoRepository  return_approval_trans_infoRepository;
	
	@Autowired
	Return_approval_weight_dtlRepository return_approval_weight_dtlRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Delivery_challanRepository dChallanRepository;
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	Sales_InvoiceRepository sales_InvoiceRepository;
	
	@Autowired
	Sales_credit_noteRepository sales_credit_noteRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	Return_approval_item_dtls_for_jobworkRepository return_approval_item_dtls_for_jobworkRepository;
	
	@Autowired
	Return_approval_item_dtls_for_jobwork_priceRepository return_approval_item_dtls_for_jobwork_priceRepository;
	
	@Autowired
	Sales_return_noteRepository sales_return_noteRepository;
	
	@Autowired
	Item_Service_masterRepository item_Service_masterRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	public SalesSequenceIdDTO getRANSequenceId(String fin_year,String return_type)
	{
		int slno=0;String prefix="RA";
		String sno=return_approval_noteRepository.countReturnNote(fin_year,return_type);
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String invoice_prefix="";
		if(return_type.compareToIgnoreCase("Goods Return Due To Rejection")==0)//Goods Return Due To Rejection
		{
			invoice_prefix="G";
		}
		else 
		{
			invoice_prefix="A";
		}
		
		
	
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		//prefix=prefix+invoice_prefix+"-"+fin_year+"-";
		prefix=prefix+invoice_prefix+"-"+final_fyear+"-";
		
		//String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Return_approval_note save(Return_approval_note ran)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;String prefix="RAN";
		if(return_approval_noteRepository.countId(prefix).isPresent())
		{
			slno=Long.parseLong(return_approval_noteRepository.countId(prefix).get());
		}
 		String gen_sno=UniqueID.uniqueid(prefix, slno);
 		
 		ran.setReapp_note_status(0);
 		ran.setWeighment_status(0);
 		ran.setSrn_status("Not Done");
 		ran.setUnload_status("Not Done");
 		
 		
 		
 		/*Start checking transaction no for unique */
		System.err.println("First:>>"+ran.getSalesreturnno());
		long nslno=0;String tprefix="RA";
		String tsno=return_approval_noteRepository.countReturnNote(ran.getFin_year(),ran.getSalesreturntype());
				
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String invoice_prefix="";
		if(ran.getSalesreturntype().compareToIgnoreCase("Goods Return Due To Rejection")==0)//Goods Return Due To Rejection
		{
			invoice_prefix="G";
		}
		else 
		{
			invoice_prefix="A";
		}
		
		String fin_yearspit[]=ran.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		
		//tprefix=tprefix+invoice_prefix+"-"+ran.getFin_year()+"-";
		tprefix=tprefix+invoice_prefix+"-"+final_fyear+"-";
		
		//String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		String gen_tslno=UniqueIDTransaction.uniqueId6(tprefix,nslno);
		
		ran.setSalesreturnno(gen_tslno);
		System.err.println("Last:>>>"+ran.getSalesreturnno());
		/*End checking transaction no for unique */
		
 		ran.setSalesreturnid(gen_sno);
 		ran.setModified_type("INSERTED");
 		ran.setInserted_by(userRepository.getUserDetails(ran.getUsername()).getName());
 		ran.setInserted_on(ldt);
 		ran.setUpdated_by("NA");
 		ran.setUpdated_on(ldt);
 		ran.setDeleted_by("NA");
 		ran.setDeleted_on(ldt);
 		ran.setCredit_note_status("NO");
        
 		String salesorder="";
 	/*	
 		if(ran.getReturnbasedon().compareToIgnoreCase("Delivery Challan")==0) 
 		{
 			dChallanRepository.updatedeliverychallan_salesreturn(ran.getReferance_id(),ran.getSalesreturnid());
 			
 			Delivery_challan deliverychallanstatus =dChallanRepository.getDeliveryChallanDtls(ran.getReferance_id());
 			
 			if(deliverychallanstatus.getChallan_status().compareToIgnoreCase("Multiple") == 0) 
 			{
 				
 				//
 				Sales_Invoice salesinvoice_multiplechallan=sales_InvoiceRepository.getSalesInvDetails(deliverychallanstatus.getSales_invoice_id());
 				
 				String multipledeliverychallan[]=salesinvoice_multiplechallan.getReference_id().split(",");
 				
 			    boolean ifdeliveryhasno=false; 
 				
 				for(int i=0;i<multipledeliverychallan.length;i++) 
 				{
 					if(dChallanRepository.checkDelivery_challanRETURN(multipledeliverychallan[i])) 
 					{
 						ifdeliveryhasno=true;
 					}
 				}
 				if(ifdeliveryhasno == true) 
 				{	
 				}
 				else 
 				{
 					sales_InvoiceRepository.updateInvoiceStausonmultiple(deliverychallanstatus.getSales_invoice_id(),ran.getSalesreturnid());
 				}	
 			}
 			else 
 			{
 				if(sales_InvoiceRepository.checkmultipledileverychallanininvoice(ran.getReferance_id())) //if more than 1 then dnt block 
 				{
 	 				System.out.println("1");
 				}
 				else //block 
 				{
 					System.out.println("2");
 					sales_InvoiceRepository.updateInvoiceStaus(ran.getReferance_id(),ran.getSalesreturnid());
 				}
 			}
 			//checking more than one sales order
 			
 			Delivery_challan delv_challan=dChallanRepository.getDeliveryChallanDtls(ran.getReferance_id());//get loading_id from delv_challan 
 			
 			Wm_loading_advice retunnote = wm_loading_adviceRepository.getLoadingDetails(delv_challan.getReferance_id()); //get sales_order from loading advice
 			
 			
 			List<Wm_loading_advice> sales_order = new ArrayList<Wm_loading_advice>();
 			
 	 		sales_order.addAll(wm_loading_adviceRepository.loadingAdviceDetails(retunnote.getReferance_id()));
 			
 			//check salesorder size() if 1 then make changes or else check in delivery challan for more 
 			
 			if(sales_order.size() ==1 )//here only one loading advise found for 1 sale order
 			{
 				System.out.println("3");
 				
 				salesorder=retunnote.getReferance_id();
 				
 				//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 			}
 			else
 			{
 				boolean delv_challan_status = false;
 				for(int k=0;k<sales_order.size();k++) // checking loading_id in delv_challan with NO
 				{
 					if(dChallanRepository.checkmultipledelvChallan(sales_order.get(k).getAdvice_id())) //if more than 1 then block
 					{
 						System.out.println("4");
 					}
 					else //block
 					{
 						System.out.println("5");
 						delv_challan_status =true;
 					}
 				}
 				if(delv_challan_status == true)
 				{
 					System.out.println("6");
 					salesorder=retunnote.getReferance_id();
 					//ran.setOrder_id(retunnote.getReferance_id());
 					//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 				}
 			}
 			
 			
 			
 		
 		}
 		if(ran.getReturnbasedon().compareToIgnoreCase("Sales Order")==0) 
 		{
 			//ran.setOrder_id(ran.getReferance_id());
 			salesorder=ran.getReferance_id();
 			boolean deliverystatus=false;
 			if(ran.getDiliverylist().contains(",")) 
 			{
 				String[] splitdelivery=ran.getDiliverylist().split(",");
 				
 				for(int i=0;i<splitdelivery.length;i++) 
 				{
 					dChallanRepository.updatedeliverychallan_salesreturn(splitdelivery[i],ran.getSalesreturnid());
 					
 					Delivery_challan deliverychallanstatus =dChallanRepository.getDeliveryChallanDtls(splitdelivery[i]);
 		 			
 		 			if(deliverychallanstatus.getChallan_status().compareToIgnoreCase("Multiple") == 0) 
 		 			{
 		 				
 		 				
 		 				Sales_Invoice salesinvoice_multiplechallan=sales_InvoiceRepository.getSalesInvDetails(deliverychallanstatus.getSales_invoice_id());
 		 				
 		 				String multipledeliverychallan[]=salesinvoice_multiplechallan.getReference_id().split(",");
 		 				
 		 			    boolean ifdeliveryhasno=false; 
 		 				
 		 				for(int v=0;v<multipledeliverychallan.length;v++) 
 		 				{
 		 					if(dChallanRepository.checkDelivery_challanRETURN(multipledeliverychallan[v])) 
 		 					{
 		 						ifdeliveryhasno=true;
 		 					}
 		 					
 		 					
 		 				}
 		 				if(ifdeliveryhasno == true) 
 		 				{
 		 					
 		 				}
 		 				else 
 		 				{
 		 					sales_InvoiceRepository.updateInvoiceStausonmultiple(deliverychallanstatus.getSales_invoice_id(),ran.getSalesreturnid());
 		 				}
 		 					
 		 				
 		 			}
 		 			else 
 		 			{
 		 				
 		 				if(sales_InvoiceRepository.checkmultipledileverychallanininvoice(splitdelivery[i])) //if more than 1 then dnt block 
 	 					{
 	 						
 	 					}
 	 					else //block 
 	 					{
 	 						sales_InvoiceRepository.updateInvoiceStaus(splitdelivery[i],ran.getSalesreturnid());
 	 					}
 	 					
 	 					
 	 					if(dChallanRepository.checkDelivery_challanRETURN(splitdelivery[i])) 
 	 					{
 	 						deliverystatus=true;
 	 					}
 		 				
 		 			}
 					
 					
 					
 					
 					
 					
 				}
 				
 				
 			}
 			else 
 			{
 				dChallanRepository.updatedeliverychallan_salesreturn(ran.getDiliverylist(),ran.getSalesreturnid());
 				System.out.println(" delivery "+ran.getDiliverylist());
 				System.out.println(" delivery12 "+sales_InvoiceRepository.checkmultipledileverychallanininvoice(ran.getDiliverylist()));
 				
 				
 				Delivery_challan deliverychallanstatus =dChallanRepository.getDeliveryChallanDtls(ran.getDiliverylist());
		 			
		 			if(deliverychallanstatus.getChallan_status().compareToIgnoreCase("Multiple") == 0) 
		 			{
		 				

 		 				
 		 				Sales_Invoice salesinvoice_multiplechallan=sales_InvoiceRepository.getSalesInvDetails(deliverychallanstatus.getSales_invoice_id());
 		 				
 		 				String multipledeliverychallan[]=salesinvoice_multiplechallan.getReference_id().split(",");
 		 				
 		 			    boolean ifdeliveryhasno=false; 
 		 				
 		 				for(int v=0;v<multipledeliverychallan.length;v++) 
 		 				{
 		 					if(dChallanRepository.checkDelivery_challanRETURN(multipledeliverychallan[v])) 
 		 					{
 		 						ifdeliveryhasno=true;
 		 					}
 		 					
 		 					
 		 				}
 		 				if(ifdeliveryhasno == true) 
 		 				{
 		 					
 		 				}
 		 				else 
 		 				{
 		 					sales_InvoiceRepository.updateInvoiceStaus(deliverychallanstatus.getSales_invoice_id(),ran.getSalesreturnid());
 		 				}
 		 				
 		 				
 		 				
 		 				
		 				
		 				
		 				
		 			}
		 			else 
		 			{
		 				
		 				
		 				
		 			}
 				
 				
 				    if(sales_InvoiceRepository.checkmultipledileverychallanininvoice(ran.getDiliverylist())) //if more than 1 then dnt block 
					{
						
					}
					else //block 
					{
						sales_InvoiceRepository.updateInvoiceStaus(ran.getDiliverylist(),ran.getSalesreturnid());
					}
 				if(dChallanRepository.checkDelivery_challanRETURN(ran.getDiliverylist())) 
					{
 					
						deliverystatus=true;
					}
 				
 			}
 			
 			
 			if(deliverystatus == true) //no changes
 			{
 				
 			}
 			else //block sales return 
 			{
 				//sales_OrderRepository.updatesalesorder_salesreturn(ran.getReferance_id(),ran.getSalesreturnid());
 			}
 			
 			
 			
 			
 			
 			
 		}
 		if(ran.getReturnbasedon().compareToIgnoreCase("Sales Invoice")==0) 
 		{
 			sales_InvoiceRepository.updatesales_invoice_salesreturn(ran.getReferance_id(),ran.getSalesreturnid()); 
 			
 			Sales_Invoice sales_inv = sales_InvoiceRepository.getSalesInvDetails(ran.getReferance_id()); //get sales_invoice from delivery challan
 			
 			
 					
			if(sales_inv.getChallan().compareToIgnoreCase("Multiple")==0) 
			{
				
				
				String deliverychallanidmultiple[]=sales_inv.getReference_id().split(",");
				
				for(int i=0;i<deliverychallanidmultiple.length;i++) 
				{
					
					
							dChallanRepository.updatedeliverychallan_salesreturn(deliverychallanidmultiple[i],ran.getSalesreturnid());
				 			
				 			Delivery_challan delv_challan=dChallanRepository.getDeliveryChallanDtls(deliverychallanidmultiple[i]);//get loading_id from delv_challan 
				 			
				 			Wm_loading_advice retunnote = wm_loading_adviceRepository.getLoadingDetails(delv_challan.getReferance_id()); //get sales_order from loading advice
				 			
				 			
				 			List<Wm_loading_advice> sales_order = new ArrayList<Wm_loading_advice>();
				 			
				 	 		sales_order.addAll(wm_loading_adviceRepository.loadingAdviceDetails(retunnote.getReferance_id()));
				 			
				 			//check salesorder size() if 1 then make changes or else check in delivery challan for more 
				 			
				 			if(sales_order.size() ==1) //here only one loading advise found for 1 sale order
				 			{
				 				System.out.println("3");
				 				salesorder=retunnote.getReferance_id();
				 				//ran.setOrder_id(retunnote.getReferance_id());
				 			//	sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
				 			}
				 			else
				 			{
				 				boolean delv_challan_status = false;
				 				for(int k=0;k<sales_order.size();k++) // checking loading_id in delv_challan with NO
				 				{
				 					if(dChallanRepository.checkmultipledelvChallan(sales_order.get(k).getAdvice_id())) //if more than 1 then block
				 					{
				 						
				 					}
				 					else //block
				 					{
				 						delv_challan_status =true;
				 					}
				 				}
				 				if(delv_challan_status == true)
				 				{
				 					salesorder=retunnote.getReferance_id();
				 					//ran.setOrder_id(retunnote.getReferance_id());
				 					//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
				 				}
				 			}
					
                }
				
				
			}
			else 
			{
				
				dChallanRepository.updatedeliverychallan_salesreturn(sales_inv.getReference_id(),ran.getSalesreturnid());
	 			
	 			Delivery_challan delv_challan=dChallanRepository.getDeliveryChallanDtls(sales_inv.getReference_id());//get loading_id from delv_challan 
	 			
	 			Wm_loading_advice retunnote = wm_loading_adviceRepository.getLoadingDetails(delv_challan.getReferance_id()); //get sales_order from loading advice
	 			
	 			
	 			List<Wm_loading_advice> sales_order = new ArrayList<Wm_loading_advice>();
	 			
	 	 		sales_order.addAll(wm_loading_adviceRepository.loadingAdviceDetails(retunnote.getReferance_id()));
	 			
	 			//check salesorder size() if 1 then make changes or else check in delivery challan for more 
	 			
	 			if(sales_order.size() ==1) //here only one loading advise found for 1 sale order
	 			{
	 				System.out.println("3");
	 				salesorder=retunnote.getReferance_id();
	 				//ran.setOrder_id(retunnote.getReferance_id());
	 				//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
	 			}
	 			else
	 			{
	 				boolean delv_challan_status = false;
	 				for(int k=0;k<sales_order.size();k++) // checking loading_id in delv_challan with NO
	 				{
	 					if(dChallanRepository.checkmultipledelvChallan(sales_order.get(k).getAdvice_id())) //if more than 1 then block
	 					{
	 						
	 					}
	 					else //block
	 					{
	 						delv_challan_status =true;
	 					}
	 				}
	 				if(delv_challan_status == true)
	 				{
	 					salesorder=retunnote.getReferance_id();
	 					//ran.setOrder_id(retunnote.getReferance_id());
	 					//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
	 				}
	 			}
				
			}
	
 			
 		
 			
 			
 			
 		}
 		
 		*/
 		if(Utility.isNullOrEmpty(ran.getParty())) {
 			ran.setPartyname(cust_bussiness_partnerRepository.getCustomer(ran.getParty()).getCp_name());
		}else {ran.setPartyname("None");}
 		
 		if(Utility.isNullOrEmpty(ran.getBusinessunit())) {
			ran.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ran.getBusinessunit()).getBusinessunit_name());
		}else {ran.setBusinessunitname("None");}
 		
		Set<Return_approval_broker_dtls> ran1 = new HashSet<Return_approval_broker_dtls>();
		ran1.addAll(ran.getReturn_approval_broker_dtls());
		for(Return_approval_broker_dtls rabd : ran1)
		{
			rabd.setSalesreturnid(ran.getSalesreturnid());
			rabd.setSalesreturnno(ran.getSalesreturnno());
			rabd.setCompany_id(ran.getCompany_id());
			rabd.setFin_year(ran.getFin_year());
			rabd.setModified_type(ran.getModified_type());
			rabd.setInserted_by(ran.getInserted_by());
			rabd.setInserted_on(ran.getInserted_on());
			rabd.setUpdated_by(ran.getUpdated_by());
			rabd.setUpdated_on(ran.getUpdated_on());
			rabd.setDeleted_by(ran.getDeleted_by());
			rabd.setDeleted_on(ran.getDeleted_on());
		}
		ran.setReturn_approval_broker_dtls(ran1);
 				
		Set<Return_approval_docs> ran2 = new HashSet<Return_approval_docs>();
		ran2.addAll(ran.getReturn_approval_docs());
		for(Return_approval_docs rad : ran2)
		{
			rad.setSalesreturnid(ran.getSalesreturnid());
			rad.setSalesreturnno(ran.getSalesreturnno());
			rad.setCompany_id(ran.getCompany_id());
			rad.setFin_year(ran.getFin_year());
			rad.setModified_type(ran.getModified_type());
			rad.setInserted_by(ran.getInserted_by());
			rad.setInserted_on(ran.getInserted_on());
			rad.setUpdated_by(ran.getUpdated_by());
			rad.setUpdated_on(ran.getUpdated_on());
			rad.setDeleted_by(ran.getDeleted_by());
			rad.setDeleted_on(ran.getDeleted_on());
		}
		ran.setReturn_approval_docs(ran2);
		
		if(ran.isJobwork()) 
		{
			ran.getReturn_approval_item_dtls().clear();
			
			Set<Return_approval_item_dtls_for_jobwork> jobSet = new HashSet<Return_approval_item_dtls_for_jobwork>();
			jobSet.addAll(ran.getReturn_approval_item_dtls_for_jobwork());
			for(Return_approval_item_dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setSalesreturnid(gen_sno);	
				jobDts.setSalesreturnno(ran.getSalesreturnno());
				
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(ran.getCompany_id());
				jobDts.setFin_year(ran.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(ran.getInserted_by());
				jobDts.setInserted_on(ran.getInserted_on());
				jobDts.setUpdated_by("NA");
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			ran.setReturn_approval_item_dtls_for_jobwork(jobSet);
			
			Set<Return_approval_item_dtls_for_jobwork_price> serviceItem = new HashSet<Return_approval_item_dtls_for_jobwork_price>();
			serviceItem.addAll(ran.getReturn_approval_item_dtls_for_jobwork_price());
			for(Return_approval_item_dtls_for_jobwork_price service : serviceItem)
			{
				service.setSalesreturnid(gen_sno);	
				service.setSalesreturnno(ran.getSalesreturnno());
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
				}else{service.setItem_service_name("0");};
				
				if(Utility.isNullOrEmpty(service.getTaxcode())) {
					service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
				}else{service.setTaxcode_name("0");};
				
				service.setCompany_id(ran.getCompany_id());
				service.setFin_year(ran.getFin_year());
				service.setModified_type("INSERTED");
				service.setInserted_by(userRepository.getUserDetails(ran.getUsername()).getName());
				service.setInserted_on(ran.getInserted_on());
				service.setUpdated_by("NA");
				service.setUpdated_on(ldt);
				service.setDeleted_by("NA");
				service.setDeleted_on(ldt);
			}
			ran.setReturn_approval_item_dtls_for_jobwork_price(serviceItem);
			
			
		}
		else 
		{
			ran.getReturn_approval_item_dtls_for_jobwork().clear();
			ran.getReturn_approval_item_dtls_for_jobwork_price().clear();
			
			Set<Return_approval_item_dtls> ran3 = new HashSet<Return_approval_item_dtls>();
			ran3.addAll(ran.getReturn_approval_item_dtls());
			for(Return_approval_item_dtls raid : ran3)
			{
				raid.setSalesreturnid(ran.getSalesreturnid());
				raid.setSalesreturnno(ran.getSalesreturnno());
				raid.setItemname(item_masterRepository.itemNameById(raid.getItemcode()).getItem_name());
				if(Utility.isNullOrEmpty(raid.getPacking())) {
					raid.setPackingname(item_masterRepository.itemNameById(raid.getPacking()).getItem_name());
				}else {raid.setPackingname("None");}
				if(Utility.isNullOrEmpty(raid.getTaxcode())) {
					raid.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(raid.getTaxcode()).getTax_name());
 				}else {raid.setTaxcode_name("None");}
				
				raid.setCompany_id(ran.getCompany_id());
				raid.setFin_year(ran.getFin_year());
				raid.setModified_type(ran.getModified_type());
				raid.setInserted_by(ran.getInserted_by());
				raid.setInserted_on(ran.getInserted_on());
				raid.setUpdated_by(ran.getUpdated_by());
				raid.setUpdated_on(ran.getUpdated_on());
				raid.setDeleted_by(ran.getDeleted_by());
				raid.setDeleted_on(ran.getDeleted_on());
				if(ran.getReturnbasedon().compareToIgnoreCase("Sales Order")==0) 
		 		{
					raid.setDelivery_cid(raid.getDelivery_cid());
		 		}
				
				raid.setOrder_id(salesorder);
			}
			ran.setReturn_approval_item_dtls(ran3);
		}
		
		Set<Return_approval_party_dtls> ran4 = new HashSet<Return_approval_party_dtls>();
		ran4.addAll(ran.getReturn_approval_party_dtls());
		for(Return_approval_party_dtls rapd : ran4) 				{
			rapd.setSalesreturnid(ran.getSalesreturnid());
			rapd.setSalesreturnno(ran.getSalesreturnno());
			rapd.setCompany_id(ran.getCompany_id());
			rapd.setFin_year(ran.getFin_year());
			rapd.setModified_type(ran.getModified_type());
			rapd.setInserted_by(ran.getInserted_by());
			rapd.setInserted_on(ran.getInserted_on());
			rapd.setUpdated_by(ran.getUpdated_by());
			rapd.setUpdated_on(ran.getUpdated_on());
			rapd.setDeleted_by(ran.getDeleted_by());
			rapd.setDeleted_on(ran.getDeleted_on());
		}
		ran.setReturn_approval_party_dtls(ran4);
		
		Set<Return_approval_shipment_dtls> ran5 = new HashSet<Return_approval_shipment_dtls>();
		ran5.add(ran.getReturn_approval_shipment_dtls());
		for(Return_approval_shipment_dtls rasd : ran5)
		{
			rasd.setSalesreturnid(ran.getSalesreturnid());
			rasd.setSalesreturnno(ran.getSalesreturnno());
			rasd.setCompany_id(ran.getCompany_id());
			rasd.setFin_year(ran.getFin_year());
			rasd.setModified_type(ran.getModified_type());
			rasd.setInserted_by(ran.getInserted_by());
			rasd.setInserted_on(ran.getInserted_on());
			rasd.setUpdated_by(ran.getUpdated_by());
			rasd.setUpdated_on(ran.getUpdated_on());
			rasd.setDeleted_by(ran.getDeleted_by());
			rasd.setDeleted_on(ran.getDeleted_on());
		}
		if(!ran5.isEmpty())	{
			ran.setReturn_approval_shipment_dtls(ran5.iterator().next());
		}
		
		Set<Return_approval_trans_info> ran6 = new HashSet<Return_approval_trans_info>();
		ran6.add(ran.getReturn_approval_trans_info());
		for(Return_approval_trans_info rati : ran6)
		{
			rati.setSalesreturnid(ran.getSalesreturnid());
			rati.setSalesreturnno(ran.getSalesreturnno());
			if(Utility.isNullOrEmpty(rati.getVehicleid())) {
				rati.setVehicleno(vehicleMasterRepository.getVehicleDetails(rati.getVehicleid()).getVehicle_no());
			}else {rati.setVehicleno("None");}
			rati.setCompany_id(ran.getCompany_id());
			rati.setFin_year(ran.getFin_year());
			rati.setModified_type(ran.getModified_type());
			rati.setInserted_by(ran.getInserted_by());
			rati.setInserted_on(ran.getInserted_on());
			rati.setUpdated_by(ran.getUpdated_by());
			rati.setUpdated_on(ran.getUpdated_on());
			rati.setDeleted_by(ran.getDeleted_by());
			rati.setDeleted_on(ran.getDeleted_on());
		}
		if(!ran6.isEmpty())	{
			ran.setReturn_approval_trans_info(ran6.iterator().next());
		}
		
		Set<Return_approval_weight_dtl> ran7 = new HashSet<Return_approval_weight_dtl>();
		ran7.add(ran.getReturn_approval_weight_dtl());
		for(Return_approval_weight_dtl rawd : ran7)
		{
			rawd.setSalesreturnid(ran.getSalesreturnid());
			rawd.setSalesreturnno(ran.getSalesreturnno());
			rawd.setCompany_id(ran.getCompany_id());
			rawd.setFin_year(ran.getFin_year());
			rawd.setModified_type(ran.getModified_type());
			rawd.setInserted_by(ran.getInserted_by());
			rawd.setInserted_on(ran.getInserted_on());
			rawd.setUpdated_by(ran.getUpdated_by());
			rawd.setUpdated_on(ran.getUpdated_on());
			rawd.setDeleted_by(ran.getDeleted_by());
			rawd.setDeleted_on(ran.getDeleted_on());
		}
		if(!ran7.isEmpty())	{
			ran.setReturn_approval_weight_dtl(ran7.iterator().next());
		}

		ran.setOrder_id(salesorder);
		/*double restwt=sales_OrderRepository.getrestwet_forloadingpopup(salesorder);
		if(restwt ==0) 
		{
			sales_OrderRepository.updatesalesorder_salesreturn(salesorder,ran.getSalesreturnid());
		}
		*/
		return return_approval_noteRepository.save(ran);
	}
	
	public List<Return_approval_note> getReturnApprovalNote(String company,String finyear)
	{
		return return_approval_noteRepository.getReturnApprovalNote(company,finyear);
	}
	
	public List<Map<String, Object>> getReturnApprovalNoteList(String company,String currentdate){
 		//System.out.println("bpid::"+code);
 		List<Map<String, Object>> val=return_approval_noteRepository.getReturnApprovalNoteList(company,currentdate);
		return val;
	}
	
	/*public List<Map<String, Object>> searchReturnApprovalNote(String fromdate,String todate,String party1)
	{
 		return return_approval_noteRepository.searchReturnApprovalNote(fromdate,todate,party1);
	}*/
	
	public List<Map<String, Object>> searchReturnApprovalNote(String fromdate,String todate)
	{
 		return return_approval_noteRepository.searchReturnApprovalNote(fromdate,todate);
	}
	
	public List<Return_approval_noteDTO> getReturnAppNoteThruUnAdv(String bunit,String uldate,String company,String finyear,String party)
	{
		List<Return_approval_note> appNote=new ArrayList<Return_approval_note>();
		
		appNote.addAll(return_approval_noteRepository.getReturnAppNoteThruUnAdv(bunit,uldate,company,finyear,party));
			
		Type listType=new TypeToken<List<Return_approval_noteDTO>>() {}.getType();
		
		List<Return_approval_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public List<Map<String,Object>> getReturnAppNoteThruUnAdvjobwork(String advice_date,String bunit,String party)
	{
		return return_approval_noteRepository.getReturnAppNoteThruUnAdvjobwork(advice_date,bunit,party);
	}
	
	public List<Map<String,Object>> getReturnAppNoteThruWejobwork(String date,String bunit,String party)
	{
		return return_approval_noteRepository.getReturnAppNoteThruWejobwork(date,bunit,party);
	}
	
	public List<Return_approval_noteDTO> getReturnAppNoteThruWe(String invtype,String bunit,String party,String srdate,String company,String finyear)
	{
		System.out.println(invtype + " // " + bunit + " // " + party + " // " +  srdate + " // " + company + " // " + finyear);
		List<Return_approval_note> appNote=new ArrayList<Return_approval_note>();
		
		appNote.addAll(return_approval_noteRepository.getReturnAppNoteThruWe(invtype,bunit,party,srdate,company,finyear));
			
		Type listType=new TypeToken<List<Return_approval_noteDTO>>() {}.getType();
		
		List<Return_approval_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public List<Return_approval_noteDTO> getRtnAppNoteLowRate(String bunit,String party,String invdate,String company,String finyear,String invoicetype)
	{
		List<Return_approval_note> appNote=new ArrayList<Return_approval_note>();
		appNote.addAll(return_approval_noteRepository.getRtnAppNoteLowRate(bunit,party,invdate,company,finyear,invoicetype));
		Type listType=new TypeToken<List<Return_approval_noteDTO>>() {}.getType();
		
		List<Return_approval_noteDTO> appNoteLow=new ModelMapper().map(appNote,listType);
		return appNoteLow;
	}
	
	public List<Return_approval_noteDTO> getRtnAppNoteLowRateUpdate(Long id)
	{
		Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		 
		 String []splitreturnapp=op.get().getReferance_id().split(",");
		 
		List<Return_approval_note> appNote=new ArrayList<Return_approval_note>();
		for(int i=0;i<splitreturnapp.length;i++) 
		 {
			appNote.add(return_approval_noteRepository.getRtnAppNoteLowRateUpdate(splitreturnapp[i]));
		 }
		Type listType=new TypeToken<List<Return_approval_noteDTO>>() {}.getType();
		
		List<Return_approval_noteDTO> appNoteLow=new ModelMapper().map(appNote,listType);
		return appNoteLow;
	}
	
	public Return_approval_note findOne(Long id) {
		 Optional<Return_approval_note> op=this.return_approval_noteRepository.findById(id);
		 return op.get();
	}
	
	public Return_approval_noteDTO getReturnApprovalDtls(String salesreturnid)
	{
		Return_approval_note modelList=return_approval_noteRepository.getReturnApprovalDtls(salesreturnid);
		
		Type listType=new TypeToken<Return_approval_noteDTO>() {}.getType();
		Return_approval_noteDTO appDetails=new ModelMapper().map(modelList,listType);
		
		return appDetails;
	}
	
	public List<Return_approval_broker_dtlsDTO> getReturnApprovalBD(String salesreturnid)
	{
		List<Return_approval_broker_dtls> modelList=return_approval_broker_dtlsRepository.getReturnApprovalBD(salesreturnid);
		
		Type listType=new TypeToken<List<Return_approval_broker_dtlsDTO>>() {}.getType();
		
		List<Return_approval_broker_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Return_approval_docsDTO> getReturnApprovalD(String salesreturnid)
	{
		List<Return_approval_docs> modelList=return_approval_docsRepository.getReturnApprovalD(salesreturnid);
		
		Type listType=new TypeToken<List<Return_approval_docsDTO>>() {}.getType();
		List<Return_approval_docsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	
	public List<Return_approval_item_dtlsDTO> getReturnApprovalID(String salesreturnid)
	{
		List<Return_approval_item_dtls> modelList=return_approval_item_dtlsRepository.getReturnApprovalID(salesreturnid);
		
		Type listType=new TypeToken<List<Return_approval_item_dtlsDTO>>() {}.getType();
		List<Return_approval_item_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Return_approval_party_dtlsDTO> getReturnApprovalPD(String salesreturnid)
	{
		List<Return_approval_party_dtls> modelList=return_approval_party_dtlsRepository.getReturnApprovalPD(salesreturnid);
		
		Type listType=new TypeToken<List<Return_approval_party_dtlsDTO>>() {}.getType();
		List<Return_approval_party_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	
	public Return_approval_shipment_dtlsDTO getReturnApprovalSD(String salesreturnid)
	{
		Return_approval_shipment_dtls modelList=return_approval_shipment_dtlsRepository.getReturnApprovalSD(salesreturnid);
		
		Type listType=new TypeToken<Return_approval_shipment_dtlsDTO>() {}.getType();
		Return_approval_shipment_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
		
	}
	
	public Return_approval_trans_infoDTO getReturnApprovalTI(String salesreturnid)
	{
		Return_approval_trans_info modelList=return_approval_trans_infoRepository.getReturnApprovalTI(salesreturnid);
		Type listType=new TypeToken<Return_approval_trans_infoDTO>() {}.getType();
		Return_approval_trans_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		return itemNameList;
	}
	
	public List<Invoice_Return_approval_trans_infoDTO> getReturnAppTransInfo(String salesreturnid){
		String[] arrOfStr=salesreturnid.split(",");
		  List<Invoice_Return_approval_trans_infoDTO> iTransList=new ArrayList<Invoice_Return_approval_trans_infoDTO>();
		  for(int i=0;i<arrOfStr.length;i++)
		  {
			  Return_approval_trans_info transInfo=return_approval_trans_infoRepository.getReturnApprovalTI(arrOfStr[i]);
			  
			  Return_approval_weight_dtl wtInfo=return_approval_weight_dtlRepository.getReturnApprovalWD(arrOfStr[i]);
			  //System.out.println("truck:"+transInfo.getVehicleid());
			 // Invoice_Return_approval_trans_infoDTO def=new Invoice_Return_approval_trans_infoDTO(transInfo.getTranscode(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehicleid()).getVehtype_code(),transInfo.getVehicleno(),wtInfo.getOwnpermitno());
			  Invoice_Return_approval_trans_infoDTO def =new Invoice_Return_approval_trans_infoDTO();
				def.setTransname(transInfo.getTranscode());
				//def.setVehicleno(vehicleMasterRepository.getVehicleDetailsVno(transInfo.getVehicleno()).getVehtype_code());
				//def.setVehicletype(transInfo.getVehicleno());
				def.setVehicletype(vehicleMasterRepository.getVehicleDetailsVno(transInfo.getVehicleno()).getVehtype_code());
				def.setVehicleno(vehicleMasterRepository.getVehicleDetailsVno(transInfo.getVehicleno()).getVehicle_id());
				def.setEwaybillno(wtInfo.getOwnpermitno());
			  iTransList.add(def);
		  }
		  
		  Type listType = new TypeToken<List<Invoice_Return_approval_trans_infoDTO>>() {}.getType();
		  List<Invoice_Return_approval_trans_infoDTO> invTrans= new ModelMapper().map(iTransList,listType);
		
		  return invTrans;
	}
	
	public Return_approval_weight_dtlDTO getReturnApprovalWD(String salesreturnid)
	{
		Return_approval_weight_dtl modelList=return_approval_weight_dtlRepository.getReturnApprovalWD(salesreturnid);
		Type listType=new TypeToken<Return_approval_weight_dtlDTO>() {}.getType();
		Return_approval_weight_dtlDTO itemNameList=new ModelMapper().map(modelList, listType);
		return itemNameList;
	}
	
	
	@Transactional
	public Return_approval_note update(Return_approval_note ran,long id)
	{
		Optional<Return_approval_note> op=return_approval_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		ran.setReapp_note_status(0);
 		ran.setWeighment_status(0);
 		ran.setSrn_status("Not Done");
 		ran.setUnload_status("Not Done");
		
 		ran.setModified_type("INSERTED");
 		ran.setInserted_by(op.get().getInserted_by());
 		ran.setInserted_on(op.get().getInserted_on());
 		ran.setUpdated_by(userRepository.getUserDetails(ran.getUsername()).getName());
 		ran.setUpdated_on(ldt);
 		ran.setDeleted_by("NA");
 		ran.setDeleted_on(ldt);
 		ran.setCredit_note_status("NO");
 		 String salesorder="";
 		
 		 if(Utility.isNullOrEmpty(ran.getDiliverylist())) 
 		{
 			
 		}
 		else 
 		{
 			ran.setDiliverylist(op.get().getDiliverylist());
 		}
 	/*	
 		if(Utility.isNullOrEmpty(ran.getReferance_id()) || Integer.parseInt(ran.getReferance_id())!=0) 
 		{
 			
 	 		//revert starts here 
 	 		
 	 		return_approval_noteRepository.updatedeliverychallanSalesreturnReverseNew(op.get().getSalesreturnid());
 			sales_InvoiceRepository.updateInvoiceStausReverseNew(op.get().getSalesreturnid());
 			return_approval_noteRepository.updatesalesorderSalesreturnReverseNew(op.get().getSalesreturnid());
 	 		
 	 		
 	 		//New Starts here
 	 		if(ran.getReturnbasedon().compareToIgnoreCase("Delivery Challan")==0) 
 	 		{
 	 			dChallanRepository.updatedeliverychallan_salesreturn(ran.getReferance_id(),ran.getSalesreturnid());
 	 			
 	 			Delivery_challan deliverychallanstatus =dChallanRepository.getDeliveryChallanDtls(ran.getReferance_id());
 	 			System.out.println(" challan status :: "+deliverychallanstatus.getChallan_status());
 	 			if(deliverychallanstatus.getChallan_status().compareToIgnoreCase("Multiple") == 0) 
 	 			{
 	 				
 	 				//
 	 				Sales_Invoice salesinvoice_multiplechallan=sales_InvoiceRepository.getSalesInvDetails(deliverychallanstatus.getSales_invoice_id());
 	 				
 	 				String multipledeliverychallan[]=salesinvoice_multiplechallan.getReference_id().split(",");
 	 				
 	 			    boolean ifdeliveryhasno=false; 
 	 				
 	 				for(int i=0;i<multipledeliverychallan.length;i++) 
 	 				{
 	 					System.out.println(" delivery id "+multipledeliverychallan[i]);
 	 					if(dChallanRepository.checkDelivery_challanRETURN(multipledeliverychallan[i])) 
 	 					{
 	 						ifdeliveryhasno=true;
 	 					}
 	 					System.out.println( "ifdeliveryhasno "+ ifdeliveryhasno  + " / " + i);
 	 					
 	 				}
 	 				System.out.println( "ifdeliveryhasno outside " + ifdeliveryhasno);
 	 				if(ifdeliveryhasno == true) 
 	 				{
 	 					
 	 				}
 	 				else 
 	 				{
 	 					sales_InvoiceRepository.updateInvoiceStausonmultiple(deliverychallanstatus.getSales_invoice_id(),ran.getSalesreturnid());
 	 				}	
 	 			}
 	 			else 
 	 			{
 	 				if(sales_InvoiceRepository.checkmultipledileverychallanininvoice(ran.getReferance_id())) //if more than 1 then dnt block 
 	 				{
 	 	 				System.out.println("1");
 	 				}
 	 				else //block 
 	 				{
 	 					System.out.println("2");
 	 					sales_InvoiceRepository.updateInvoiceStaus(ran.getReferance_id(),ran.getSalesreturnid());
 	 				}
 	 			}
 	 			//checking more than one sales order
 	 			
 	 			Delivery_challan delv_challan=dChallanRepository.getDeliveryChallanDtls(ran.getReferance_id());//get loading_id from delv_challan 
 	 			
 	 			Wm_loading_advice retunnote = wm_loading_adviceRepository.getLoadingDetails(delv_challan.getReferance_id()); //get sales_order from loading advice
 	 			
 	 			
 	 			List<Wm_loading_advice> sales_order = new ArrayList<Wm_loading_advice>();
 	 			
 	 	 		sales_order.addAll(wm_loading_adviceRepository.loadingAdviceDetails(retunnote.getReferance_id()));
 	 			
 	 			//check salesorder size() if 1 then make changes or else check in delivery challan for more 
 	 			
 	 			if(sales_order.size() ==1 )//here only one loading advise found for 1 sale order
 	 			{
 	 				System.out.println("3");
 	 				salesorder=retunnote.getReferance_id();
 	 				
 	 				//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 	 			}
 	 			else
 	 			{
 	 				boolean delv_challan_status = false;
 	 				for(int k=0;k<sales_order.size();k++) // checking loading_id in delv_challan with NO
 	 				{
 	 					if(dChallanRepository.checkmultipledelvChallan(sales_order.get(k).getAdvice_id())) //if more than 1 then block
 	 					{
 	 						System.out.println("4");
 	 					}
 	 					else //block
 	 					{
 	 						System.out.println("5");
 	 						delv_challan_status =true;
 	 					}
 	 				}
 	 				if(delv_challan_status == true)
 	 				{
 	 					System.out.println("6");
 	 					salesorder=retunnote.getReferance_id();
 	 					//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 	 				}
 	 			}
 	 			
 	 			
 	 			
 	 		
 	 		}
 	 		if(ran.getReturnbasedon().compareToIgnoreCase("Sales Order")==0) 
 	 		{
 	 			boolean deliverystatus=false;
 	 			salesorder=ran.getReferance_id();
 	 			if(ran.getDiliverylist().contains(",")) 
 	 			{
 	 				String[] splitdelivery=ran.getDiliverylist().split(",");
 	 				
 	 				for(int i=0;i<splitdelivery.length;i++) 
 	 				{
 	 					dChallanRepository.updatedeliverychallan_salesreturn(splitdelivery[i],ran.getSalesreturnid());
 	 					
 	 					Delivery_challan deliverychallanstatus =dChallanRepository.getDeliveryChallanDtls(splitdelivery[i]);
 	 		 			
 	 		 			if(deliverychallanstatus.getChallan_status().compareToIgnoreCase("Multiple") == 0) 
 	 		 			{
 	 		 				
 	 		 				
 	 		 				Sales_Invoice salesinvoice_multiplechallan=sales_InvoiceRepository.getSalesInvDetails(deliverychallanstatus.getSales_invoice_id());
 	 		 				
 	 		 				String multipledeliverychallan[]=salesinvoice_multiplechallan.getReference_id().split(",");
 	 		 				
 	 		 			    boolean ifdeliveryhasno=false; 
 	 		 				
 	 		 				for(int v=0;v<multipledeliverychallan.length;v++) 
 	 		 				{
 	 		 					if(dChallanRepository.checkDelivery_challanRETURN(multipledeliverychallan[v])) 
 	 		 					{
 	 		 						ifdeliveryhasno=true;
 	 		 					}
 	 		 					
 	 		 					
 	 		 				}
 	 		 				if(ifdeliveryhasno == true) 
 	 		 				{
 	 		 					
 	 		 				}
 	 		 				else 
 	 		 				{
 	 		 					sales_InvoiceRepository.updateInvoiceStausonmultiple(deliverychallanstatus.getSales_invoice_id(),ran.getSalesreturnid());
 	 		 				}
 	 		 				
 	 		 				
 	 		 			}
 	 		 			else 
 	 		 			{
 	 		 				
 	 		 				if(sales_InvoiceRepository.checkmultipledileverychallanininvoice(splitdelivery[i])) //if more than 1 then dnt block 
 	 	 					{
 	 	 						
 	 	 					}
 	 	 					else //block 
 	 	 					{
 	 	 						sales_InvoiceRepository.updateInvoiceStaus(splitdelivery[i],ran.getSalesreturnid());
 	 	 					}
 	 	 					
 	 	 					
 	 	 					if(dChallanRepository.checkDelivery_challanRETURN(splitdelivery[i])) 
 	 	 					{
 	 	 						deliverystatus=true;
 	 	 					}
 	 		 				
 	 		 			}
 	 					
 	 					
 	 				}
 	 				
 	 				
 	 			}
 	 			else 
 	 			{
 	 				dChallanRepository.updatedeliverychallan_salesreturn(ran.getDiliverylist(),ran.getSalesreturnid());
 	 				System.out.println(" delivery "+ran.getDiliverylist());
 	 				System.out.println(" delivery12 "+sales_InvoiceRepository.checkmultipledileverychallanininvoice(ran.getDiliverylist()));
 	 				
 	 				Delivery_challan deliverychallanstatus =dChallanRepository.getDeliveryChallanDtls(ran.getDiliverylist());
 			 			
 			 			if(deliverychallanstatus.getChallan_status().compareToIgnoreCase("Multiple") == 0) 
 			 			{
 	 		 				Sales_Invoice salesinvoice_multiplechallan=sales_InvoiceRepository.getSalesInvDetails(deliverychallanstatus.getSales_invoice_id());
 	 		 				
 	 		 				String multipledeliverychallan[]=salesinvoice_multiplechallan.getReference_id().split(",");
 	 		 				
 	 		 			    boolean ifdeliveryhasno=false; 
 	 		 				
 	 		 				for(int v=0;v<multipledeliverychallan.length;v++) 
 	 		 				{
 	 		 					if(dChallanRepository.checkDelivery_challanRETURN(multipledeliverychallan[v])) 
 	 		 					{
 	 		 						ifdeliveryhasno=true;
 	 		 					}
 	 		 					
 	 		 					
 	 		 				}
 	 		 				if(ifdeliveryhasno == true) 
 	 		 				{
 	 		 					
 	 		 				}
 	 		 				else 
 	 		 				{
 	 		 					sales_InvoiceRepository.updateInvoiceStaus(deliverychallanstatus.getSales_invoice_id(),ran.getSalesreturnid());
 	 		 				}
 	 		 				
 	 		 				
 			 				
 			 			}
 			 			else 
 			 			{
 			 				
 			 				
 			 				
 			 			}
 	 				
 	 				
 	 				    if(sales_InvoiceRepository.checkmultipledileverychallanininvoice(ran.getDiliverylist())) //if more than 1 then dnt block 
 						{
 							
 						}
 						else //block 
 						{
 							sales_InvoiceRepository.updateInvoiceStaus(ran.getDiliverylist(),ran.getSalesreturnid());
 						}
 	 				if(dChallanRepository.checkDelivery_challanRETURN(ran.getDiliverylist())) 
 						{
 	 					
 							deliverystatus=true;
 						}
 	 				
 	 			}
 	 			
 	 			
 	 			if(deliverystatus == true) //no changes
 	 			{
 	 				
 	 			}
 	 			else //block sales return 
 	 			{
 	 				sales_OrderRepository.updatesalesorder_salesreturn(ran.getReferance_id(),ran.getSalesreturnid());
 	 			}
 	 			
 	 			
 	 			
 	 			
 	 			
 	 			
 	 		}
 	 		if(ran.getReturnbasedon().compareToIgnoreCase("Sales Invoice")==0) 
 	 		{
 	 			sales_InvoiceRepository.updatesales_invoice_salesreturn(ran.getReferance_id(),ran.getSalesreturnid()); 
 	 			
 	 			Sales_Invoice sales_inv = sales_InvoiceRepository.getSalesInvDetails(ran.getReferance_id()); //get sales_invoice from delivery challan
 	 			
 	 			
 	 					
 				if(sales_inv.getChallan().compareToIgnoreCase("Multiple")==0) 
 				{
 					
 					
 					String deliverychallanidmultiple[]=sales_inv.getReference_id().split(",");
 					
 					for(int i=0;i<deliverychallanidmultiple.length;i++) 
 					{
 						
 						
 								dChallanRepository.updatedeliverychallan_salesreturn(deliverychallanidmultiple[i],ran.getSalesreturnid());
 					 			
 					 			Delivery_challan delv_challan=dChallanRepository.getDeliveryChallanDtls(deliverychallanidmultiple[i]);//get loading_id from delv_challan 
 					 			
 					 			Wm_loading_advice retunnote = wm_loading_adviceRepository.getLoadingDetails(delv_challan.getReferance_id()); //get sales_order from loading advice
 					 			
 					 			
 					 			List<Wm_loading_advice> sales_order = new ArrayList<Wm_loading_advice>();
 					 			
 					 	 		sales_order.addAll(wm_loading_adviceRepository.loadingAdviceDetails(retunnote.getReferance_id()));
 					 			
 					 			//check salesorder size() if 1 then make changes or else check in delivery challan for more 
 					 			
 					 			if(sales_order.size() ==1) //here only one loading advise found for 1 sale order
 					 			{
 					 				System.out.println("3");
 					 				salesorder=retunnote.getReferance_id();
 					 				//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 					 			}
 					 			else
 					 			{
 					 				boolean delv_challan_status = false;
 					 				for(int k=0;k<sales_order.size();k++) // checking loading_id in delv_challan with NO
 					 				{
 					 					if(dChallanRepository.checkmultipledelvChallan(sales_order.get(k).getAdvice_id())) //if more than 1 then block
 					 					{
 					 						
 					 					}
 					 					else //block
 					 					{
 					 						delv_challan_status =true;
 					 					}
 					 				}
 					 				if(delv_challan_status == true)
 					 				{
 					 					salesorder=retunnote.getReferance_id();
 					 					//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 					 				}
 					 			}
 						
 	                }
 					
 					
 				}
 				else 
 				{
 					
 					dChallanRepository.updatedeliverychallan_salesreturn(sales_inv.getReference_id(),ran.getSalesreturnid());
 		 			
 		 			Delivery_challan delv_challan=dChallanRepository.getDeliveryChallanDtls(sales_inv.getReference_id());//get loading_id from delv_challan 
 		 			
 		 			Wm_loading_advice retunnote = wm_loading_adviceRepository.getLoadingDetails(delv_challan.getReferance_id()); //get sales_order from loading advice
 		 			
 		 			
 		 			List<Wm_loading_advice> sales_order = new ArrayList<Wm_loading_advice>();
 		 			
 		 	 		sales_order.addAll(wm_loading_adviceRepository.loadingAdviceDetails(retunnote.getReferance_id()));
 		 			
 		 			//check salesorder size() if 1 then make changes or else check in delivery challan for more 
 		 			
 		 			if(sales_order.size() ==1) //here only one loading advise found for 1 sale order
 		 			{
 		 				System.out.println("3");
 		 				salesorder=retunnote.getReferance_id();
 		 				//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 		 			}
 		 			else
 		 			{
 		 				boolean delv_challan_status = false;
 		 				for(int k=0;k<sales_order.size();k++) // checking loading_id in delv_challan with NO
 		 				{
 		 					if(dChallanRepository.checkmultipledelvChallan(sales_order.get(k).getAdvice_id())) //if more than 1 then block
 		 					{
 		 						
 		 					}
 		 					else //block
 		 					{
 		 						delv_challan_status =true;
 		 					}
 		 				}
 		 				if(delv_challan_status == true)
 		 				{
 		 					salesorder=retunnote.getReferance_id();
 		 					//sales_OrderRepository.updatesalesorder_salesreturn(retunnote.getReferance_id(),ran.getSalesreturnid());
 		 				}
 		 			}
 					
 				}
 	 		}
 	 		
 	 //ends here
 		}
 		else
 		{
 			ran.setReferance_id(ran.getReferance_id());
 		}
//starts here
 		*/
 		//ends here
 		
 		if(Utility.isNullOrEmpty(ran.getParty())) {
 			ran.setPartyname(cust_bussiness_partnerRepository.getCustomer(ran.getParty()).getCp_name());
		}else {ran.setPartyname("None");}
 		
 		if(Utility.isNullOrEmpty(ran.getBusinessunit())) {
			ran.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ran.getBusinessunit()).getBusinessunit_name());
		}else {ran.setBusinessunitname("None");}
		
 		if(op.isPresent()) {
			ran.setId(id);
		}
 		
 			return_approval_broker_dtlsRepository.updateReturn_approval_broker_dtls(op.get().getSalesreturnid(),"UPDATED");
 			
			Set<Return_approval_broker_dtls> ran1 = new HashSet<Return_approval_broker_dtls>();
			ran1.addAll(ran.getReturn_approval_broker_dtls());
			for(Return_approval_broker_dtls rabd : ran1)
			{
				rabd.setSalesreturnid(op.get().getSalesreturnid());
				rabd.setSalesreturnno(ran.getSalesreturnno());
				rabd.setCompany_id(ran.getCompany_id());
				rabd.setFin_year(ran.getFin_year());
				rabd.setModified_type(ran.getModified_type());
				rabd.setInserted_by(ran.getInserted_by());
				rabd.setInserted_on(ran.getInserted_on());
				rabd.setUpdated_by(ran.getUpdated_by());
				rabd.setUpdated_on(ran.getUpdated_on());
				rabd.setDeleted_by(ran.getDeleted_by());
				rabd.setDeleted_on(ran.getDeleted_on());
			
			}
			ran.setReturn_approval_broker_dtls(ran1);
			
			return_approval_docsRepository.updateReturn_approval_docs(op.get().getSalesreturnid(),"UPDATED");
			
			Set<Return_approval_docs> ran2 = new HashSet<Return_approval_docs>();
			ran2.addAll(ran.getReturn_approval_docs());
			for(Return_approval_docs rad : ran2)
			{
				rad.setSalesreturnid(op.get().getSalesreturnid());
				rad.setSalesreturnno(ran.getSalesreturnno());
				rad.setCompany_id(ran.getCompany_id());
				rad.setFin_year(ran.getFin_year());
				rad.setModified_type(ran.getModified_type());
				rad.setInserted_by(ran.getInserted_by());
				rad.setInserted_on(ran.getInserted_on());
				rad.setUpdated_by(ran.getUpdated_by());
				rad.setUpdated_on(ran.getUpdated_on());
				rad.setDeleted_by(ran.getDeleted_by());
				rad.setDeleted_on(ran.getDeleted_on());
			}
			ran.setReturn_approval_docs(ran2);
			
			if(ran.isJobwork()) 
			{
				ran.getReturn_approval_item_dtls().clear();
				
				return_approval_item_dtls_for_jobworkRepository.updateReturn_approval_item_dtls_for_jobwork(op.get().getSalesreturnid(),"UPDATED");
				
				Set<Return_approval_item_dtls_for_jobwork> jobSet = new HashSet<Return_approval_item_dtls_for_jobwork>();
				jobSet.addAll(ran.getReturn_approval_item_dtls_for_jobwork());
				for(Return_approval_item_dtls_for_jobwork jobDts : jobSet)
				{
					jobDts.setSalesreturnid(ran.getSalesreturnid());	
					jobDts.setSalesreturnno(ran.getSalesreturnno());
					
					if(Utility.isNullOrEmpty(jobDts.getJob_item())) {
						jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
					}else{jobDts.setJob_item_name("0");};
					
					if(Utility.isNullOrEmpty(jobDts.getJob_packing())){
						jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
					}else{jobDts.setJob_packing_name("0");}
					
					jobDts.setCompany_id(ran.getCompany_id());
					jobDts.setFin_year(ran.getFin_year());
					jobDts.setModified_type("INSERTED");
					jobDts.setInserted_by(ran.getInserted_by());
					jobDts.setInserted_on(ran.getInserted_on());
					jobDts.setUpdated_by(userRepository.getUserDetails(ran.getUsername()).getName());
					jobDts.setUpdated_on(ldt);
					jobDts.setDeleted_by("NA");
					jobDts.setDeleted_on(ldt);
					
					
				}
				ran.setReturn_approval_item_dtls_for_jobwork(jobSet);
				
				return_approval_item_dtls_for_jobwork_priceRepository.updateReturn_approval_item_dtls_for_jobwork_price(op.get().getSalesreturnid(),"UPDATED");
				
				Set<Return_approval_item_dtls_for_jobwork_price> serviceItem = new HashSet<Return_approval_item_dtls_for_jobwork_price>();
				serviceItem.addAll(ran.getReturn_approval_item_dtls_for_jobwork_price());
				for(Return_approval_item_dtls_for_jobwork_price service : serviceItem)
				{
					service.setSalesreturnid(ran.getSalesreturnid());	
					service.setSalesreturnno(ran.getSalesreturnno());
					
					if(Utility.isNullOrEmpty(service.getItem_service())) {
						service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
					}else{service.setItem_service_name("0");};
					
					if(Utility.isNullOrEmpty(service.getTaxcode())) {
						service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
					}else{service.setTaxcode_name("0");};
					
					service.setCompany_id(ran.getCompany_id());
					service.setFin_year(ran.getFin_year());
					service.setModified_type("INSERTED");
					service.setInserted_by(ran.getInserted_by());
					service.setInserted_on(ran.getInserted_on());
					service.setUpdated_by(userRepository.getUserDetails(ran.getUsername()).getName());
					service.setUpdated_on(ldt);
					service.setDeleted_by("NA");
					service.setDeleted_on(ldt);
				}
				ran.setReturn_approval_item_dtls_for_jobwork_price(serviceItem);
				
			}
			else
			{
				
				//System.out.println("sinvoice.isJobwork(): "+ran.isJobwork());
				ran.getReturn_approval_item_dtls_for_jobwork().clear();
				ran.getReturn_approval_item_dtls_for_jobwork_price().clear();
				
				return_approval_item_dtlsRepository.updateReturn_approval_item_dtls(op.get().getSalesreturnid(),"UPDATED");
				
				Set<Return_approval_item_dtls> ran3 = new HashSet<Return_approval_item_dtls>();
				ran3.addAll(ran.getReturn_approval_item_dtls());
				for(Return_approval_item_dtls raid : ran3)
				{
					raid.setSalesreturnid(op.get().getSalesreturnid());
					raid.setSalesreturnno(ran.getSalesreturnno());
					raid.setItemname(item_masterRepository.itemNameById(raid.getItemcode()).getItem_name());
					if(Utility.isNullOrEmpty(raid.getPacking())) {
						raid.setPackingname(item_masterRepository.itemNameById(raid.getPacking()).getItem_name());
					}else {raid.setPackingname("None");}
					
					if(Utility.isNullOrEmpty(raid.getTaxcode())) {
						raid.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(raid.getTaxcode()).getTax_name());
	 				}else {raid.setTaxcode_name("None");}
					
					if(ran.getReturnbasedon().compareToIgnoreCase("Sales Order")==0) 
			 		{
						if(Utility.isNullOrEmpty(ran.getDiliverylist())) 
			 	 		{
						   raid.setDelivery_cid(raid.getDelivery_cid());
			 	 		}
					}
					
					
					raid.setCompany_id(ran.getCompany_id());
					raid.setFin_year(ran.getFin_year());
					raid.setModified_type(ran.getModified_type());
					raid.setInserted_by(ran.getInserted_by());
					raid.setInserted_on(ran.getInserted_on());
					raid.setUpdated_by(ran.getUpdated_by());
					raid.setUpdated_on(ran.getUpdated_on());
					raid.setDeleted_by(ran.getDeleted_by());
					raid.setDeleted_on(ran.getDeleted_on());
					raid.setOrder_id(salesorder);
					
				}
				ran.setReturn_approval_item_dtls(ran3);
			}
			return_approval_party_dtlsRepository.updateReturn_approval_party_dtls(op.get().getSalesreturnid(),"UPDATED");
			
			Set<Return_approval_party_dtls> ran4 = new HashSet<Return_approval_party_dtls>();
			ran4.addAll(ran.getReturn_approval_party_dtls());
			for(Return_approval_party_dtls rapd : ran4) 				{
				
				rapd.setSalesreturnid(op.get().getSalesreturnid());
				rapd.setSalesreturnno(ran.getSalesreturnno());
				rapd.setCompany_id(ran.getCompany_id());
				rapd.setFin_year(ran.getFin_year());
				rapd.setModified_type(ran.getModified_type());
				rapd.setInserted_by(ran.getInserted_by());
				rapd.setInserted_on(ran.getInserted_on());
				rapd.setUpdated_by(ran.getUpdated_by());
				rapd.setUpdated_on(ran.getUpdated_on());
				rapd.setDeleted_by(ran.getDeleted_by());
				rapd.setDeleted_on(ran.getDeleted_on());
			 				
			}
			ran.setReturn_approval_party_dtls(ran4);
			
			return_approval_shipment_dtlsRepository.updateReturn_approval_shipment_dtls(op.get().getSalesreturnid(),"UPDATED");
			
			Set<Return_approval_shipment_dtls> ran5 = new HashSet<Return_approval_shipment_dtls>();
			ran5.add(ran.getReturn_approval_shipment_dtls());
			for(Return_approval_shipment_dtls rasd : ran5)
			{
				rasd.setSalesreturnid(op.get().getSalesreturnid());
				rasd.setSalesreturnno(ran.getSalesreturnno());
				rasd.setCompany_id(ran.getCompany_id());
				rasd.setFin_year(ran.getFin_year());
				rasd.setModified_type(ran.getModified_type());
				rasd.setInserted_by(ran.getInserted_by());
				rasd.setInserted_on(ran.getInserted_on());
				rasd.setUpdated_by(ran.getUpdated_by());
				rasd.setUpdated_on(ran.getUpdated_on());
				rasd.setDeleted_by(ran.getDeleted_by());
				rasd.setDeleted_on(ran.getDeleted_on());
			}
			if(!ran5.isEmpty())	{
				ran.setReturn_approval_shipment_dtls(ran5.iterator().next());
			}
			
			return_approval_trans_infoRepository.updateReturn_approval_trans_info(op.get().getSalesreturnid(),"UPDATED");
			
			Set<Return_approval_trans_info> ran6 = new HashSet<Return_approval_trans_info>();
			ran6.add(ran.getReturn_approval_trans_info());
			for(Return_approval_trans_info rati : ran6)
			{
				rati.setSalesreturnid(op.get().getSalesreturnid());
				rati.setSalesreturnno(ran.getSalesreturnno());
				if(Utility.isNullOrEmpty(rati.getVehicleid())) {
					rati.setVehicleno(vehicleMasterRepository.getVehicleDetails(rati.getVehicleid()).getVehicle_no());
				}else {rati.setVehicleno("None");}
				rati.setCompany_id(ran.getCompany_id());
				rati.setFin_year(ran.getFin_year());
				rati.setModified_type(ran.getModified_type());
				rati.setInserted_by(ran.getInserted_by());
				rati.setInserted_on(ran.getInserted_on());
				rati.setUpdated_by(ran.getUpdated_by());
				rati.setUpdated_on(ran.getUpdated_on());
				rati.setDeleted_by(ran.getDeleted_by());
				rati.setDeleted_on(ran.getDeleted_on());
			}
			if(!ran6.isEmpty())	{
				ran.setReturn_approval_trans_info(ran6.iterator().next());
			}
			
			return_approval_weight_dtlRepository.updateReturn_approval_weight_dtl(op.get().getSalesreturnid(),"UPDATED");
			
			Set<Return_approval_weight_dtl> ran7 = new HashSet<Return_approval_weight_dtl>();
			ran7.add(ran.getReturn_approval_weight_dtl());
			for(Return_approval_weight_dtl rawd : ran7)
			{
				rawd.setSalesreturnid(op.get().getSalesreturnid());
				rawd.setSalesreturnno(ran.getSalesreturnno());
				rawd.setCompany_id(ran.getCompany_id());
				rawd.setFin_year(ran.getFin_year());
				rawd.setModified_type(ran.getModified_type());
				rawd.setInserted_by(ran.getInserted_by());
				rawd.setInserted_on(ran.getInserted_on());
				rawd.setUpdated_by(ran.getUpdated_by());
				rawd.setUpdated_on(ran.getUpdated_on());
				rawd.setDeleted_by(ran.getDeleted_by());
				rawd.setDeleted_on(ran.getDeleted_on());
			}
			if(!ran7.isEmpty())	{
				ran.setReturn_approval_weight_dtl(ran7.iterator().next());
			}
			ran.setOrder_id(salesorder);
			
			
 		return return_approval_noteRepository.save(ran);
	}
	
	public StatusDTO salesReturnApprovalNoteUsage(String salesreturnid)
	 {
		String result = return_approval_noteRepository.salesReturnApprovalNoteUsage(salesreturnid);
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO= new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		return statusDTO;
	 }

	@Transactional
	public Return_approval_note deleteSalesReturnApprovalNotes(Return_approval_note salesReturnNote,Long id) {

		Optional<Return_approval_note> op = return_approval_noteRepository.findById(id);
		Return_approval_note return_approval_Note=return_approval_noteRepository.getReturnNoteDetails(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
			if(op.isPresent())	{
				return_approval_Note.setId(id);
			}
			
			return_approval_Note.setModified_type("DELETED");
			return_approval_Note.setInserted_by(op.get().getInserted_by());
			return_approval_Note.setInserted_on(op.get().getInserted_on());
			return_approval_Note.setUpdated_by(op.get().getUpdated_by());
			return_approval_Note.setUpdated_on(op.get().getUpdated_on());
			return_approval_Note.setDeleted_by(userRepository.getUserDetails(return_approval_Note.getUsername()).getName());
			return_approval_Note.setDeleted_on(ldt);
			
			if(op.get().isJobwork())
			{
				return_approval_item_dtls_for_jobworkRepository.updateReturn_approval_item_dtls_for_jobwork(op.get().getSalesreturnid(),"DELETED");
				return_approval_item_dtls_for_jobwork_priceRepository.updateReturn_approval_item_dtls_for_jobwork_price(op.get().getSalesreturnid(),"DELETED");
			}
			else
			{
				return_approval_noteRepository.return_approval_item_dtlsupdate(op.get().getSalesreturnid());
			}
			return_approval_noteRepository.return_approval_broker_dtlsupdate(op.get().getSalesreturnid());
			
			return_approval_noteRepository.return_approval_docsupdate(op.get().getSalesreturnid());
			
			
			
			return_approval_noteRepository.return_approval_party_dtlsupdate(op.get().getSalesreturnid());
			
			return_approval_noteRepository.return_approval_shipment_dtlsupdate(op.get().getSalesreturnid());
			
			return_approval_noteRepository.return_approval_trans_infoupdate(op.get().getSalesreturnid());
			
			return_approval_noteRepository.return_approval_weight_dtlupdate(op.get().getSalesreturnid());
			
			//revert starts here 
			
			return_approval_noteRepository.updatedeliverychallanSalesreturnReverseNew(op.get().getSalesreturnid());
			sales_InvoiceRepository.updateInvoiceStausReverseNew(op.get().getSalesreturnid());
			return_approval_noteRepository.updatesalesorderSalesreturnReverseNew(op.get().getSalesreturnid());
			
	 		/*if(op.get().getReturnbasedon().compareToIgnoreCase("Delivery Challan")==0) 
	 		{
	 			return_approval_noteRepository.updatedeliverychallanSalesreturnReverse(op.get().getReferance_id());
	 			//if(ran.getReturncriteria().compareToIgnoreCase("Full Return")==0)
	 			//{
	 				sales_InvoiceRepository.updateInvoiceStausReverse(op.get().getReferance_id());
	 			//}
	 		}
	 		if(op.get().getReturnbasedon().compareToIgnoreCase("Sales Order")==0) 
	 		{
	 			if(op.get().getDiliverylist().contains(",")) 
	 			{
	 				
	                String[] splitdelivery=op.get().getDiliverylist().split(",");
	 				
	 				for(int i=0;i<splitdelivery.length;i++) 
	 				{
	 					return_approval_noteRepository.updatedeliverychallanSalesreturnReverse(splitdelivery[i]);
	 					sales_InvoiceRepository.updateInvoiceStausReverse(splitdelivery[i]);
	 				}
	 			}
	 			else 
	 			{
	 				return_approval_noteRepository.updatedeliverychallanSalesreturnReverse(op.get().getDiliverylist());
						sales_InvoiceRepository.updateInvoiceStausReverse(op.get().getDiliverylist());
	 			}
	 			return_approval_noteRepository.updatesalesorderSalesreturnReverse(op.get().getReferance_id());
	 			
	 		}
	 		if(op.get().getReturnbasedon().compareToIgnoreCase("Sales Invoice")==0) 
	 		{
	 			return_approval_noteRepository.updatesalesinvoiceSalesreturnReverse(op.get().getReferance_id());
	 		}*/
			
			return return_approval_noteRepository.save(return_approval_Note);	
		}
	
	
	public  List<Return_approval_note> getcreditnoteapproval(long id)
	{
		Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		
		List<Return_approval_note> retunnote  =new ArrayList<Return_approval_note>();
		//System.out.println("check:"+op.get().getReferance_id());
		retunnote.add(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()));
		
		return retunnote;
	}
	
	public  List<Return_approval_item_dtls> getRtnAppNoteLowRateitemdetals(long id)
	{
		
		Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		System.out.println("re1f id:"+op.get().getReferance_id());
		List<Return_approval_item_dtls> itemdetails=new ArrayList<Return_approval_item_dtls>();
		System.out.println("ref id:"+op.get().getReferance_id());
		itemdetails.addAll(return_approval_noteRepository.getReutnitem(op.get().getReferance_id()));
		
		List<Sales_credit_note_item_dtls> creditnote_itemdetails =new ArrayList<Sales_credit_note_item_dtls>();
		creditnote_itemdetails.addAll(sales_credit_noteRepository.getcreditnote_itemdetails(op.get().getCreditnoteid()));
		
		
		List<Return_approval_item_dtls> reutnnotefinal =new ArrayList<Return_approval_item_dtls>();
		for(int i=0;i<itemdetails.size();i++) 
		{
			String Itemcode = itemdetails.get(i).getItemcode();
			creditnote_itemdetails.forEach((e)->
			{
			
				if(e.getItem_code().compareToIgnoreCase(Itemcode)==0) 
				{
					reutnnotefinal.addAll(itemdetails);
				}
			});

		}
		
		return reutnnotefinal;
		
	}
	
	 public List<Return_approval_item_dtlsDTO>getMultipleReturnApprovalitemlist(String salesreturnid)
	 {
		 System.out.println("sales return  :: "+salesreturnid);
		 String []splitsalesreturnapproval=salesreturnid.split(",");
		 
		 List<Return_approval_item_dtls> challanlist=new ArrayList<Return_approval_item_dtls>();
		 for(int i=0;i<splitsalesreturnapproval.length;i++) 
		 {
			 challanlist.addAll(return_approval_item_dtlsRepository.getReturnApprovalID(splitsalesreturnapproval[i]));
		 }
		 
		 Type listType=new TypeToken<List<Return_approval_item_dtlsDTO>>() {}.getType();
		 List<Return_approval_item_dtlsDTO> salesItemList=new ModelMapper().map(challanlist,listType);
		 
		 return salesItemList;
	 }
	 
	 public List<Return_approval_item_dtlsDTO>getMultipleReturnApprovalitemlistUpdate(Long id)
	 {
		 Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		 
		 String []splitreturnapproval=op.get().getReferance_id().split(",");
		 
		 List<Return_approval_item_dtls> challanlist=new ArrayList<Return_approval_item_dtls>();
		  for(int i=0;i<splitreturnapproval.length;i++) 
			 {
			  challanlist.addAll(return_approval_item_dtlsRepository.getReturnApprovalID(splitreturnapproval[i]));
			 }
		 
		 Type listType=new TypeToken<List<Return_approval_item_dtlsDTO>>() {}.getType();
		 List<Return_approval_item_dtlsDTO> salesItemList=new ModelMapper().map(challanlist,listType);
		 
		 return salesItemList;
	 }
	 
	 public  List<Return_approval_note> getreturnapprovalnote_salesreturnupdate(long id)
		{
			Optional<Sales_return_note> op=sales_return_noteRepository.findById(id);
			
			List<Return_approval_note> retunnote  =return_approval_noteRepository.getReturnApprovalDtlslist(op.get().getReferance_id());
			
			return retunnote;
		}
	 
	 public  List<Return_approval_item_dtls>getRtnAppNoteLowRateitemdetals_returnapp(String salesreturnid,long id)
		{
			Optional<Sales_return_note> op=sales_return_noteRepository.findById(id);
			
			List<Return_approval_item_dtls> itemdetails=new ArrayList<Return_approval_item_dtls>();
			System.out.println(op.get().getReferance_id() + " here  :: " + op.get().getSalesreturnnoteid());
			itemdetails.addAll(return_approval_noteRepository.getReutnitem(op.get().getReferance_id()));
			
			List<Sales_return_note_item_dtls> creditnote_itemdetails =new ArrayList<Sales_return_note_item_dtls>();
		
			
			creditnote_itemdetails.addAll(sales_return_noteRepository.getsalesreturnitemdetals(op.get().getSalesreturnnoteid()));
			
			List<Return_approval_item_dtls> reutnnotefinal =new ArrayList<Return_approval_item_dtls>();
			for(int i=0;i<itemdetails.size();i++) 
			{
				String Itemcode = itemdetails.get(i).getItemcode();
				creditnote_itemdetails.forEach((e)->
				{
				
					if(e.getItemcode().compareToIgnoreCase(Itemcode)==0) 
					{
						reutnnotefinal.addAll(itemdetails);
					}
				});
			}
			return itemdetails;
		}
	 
	public List<Map<String,Object>> retriveReturnAppJobworkPrice(String salesreturnid)
	{
		return return_approval_item_dtls_for_jobwork_priceRepository.retriveReturnAppJobworkPrice(salesreturnid);
	}
	
	public List<Map<String,Object>> retriveReturnAppJobwork(String salesreturnid)
	{
		return return_approval_item_dtls_for_jobworkRepository.retriveReturnAppJobwork(salesreturnid);
	}
	
	public List<Map<String,Object>> getSalesAllTransactionData(String returnbasedon,String customer,String returndate,String bunit)
	{
		
		List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		if(returnbasedon.compareToIgnoreCase("Sales Order")==0)
		{
			modelList.addAll(sales_OrderRepository.getSalesOrderReturnData(customer,returndate));
		}
		if(returnbasedon.compareToIgnoreCase("Delivery Challan")==0)
		{
			modelList.addAll(dChallanRepository.getDelvChallanReturnData(customer,returndate));
		}
		/*if(returnbasedon.compareToIgnoreCase("Sales Invoice")==0)
		{
			return sales_InvoiceRepository.getSalesOrderReturnData(customer,returndate,bunit);
		}*/
		
		return modelList;
	}

	
}
