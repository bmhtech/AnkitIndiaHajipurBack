package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_docs;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_supplier_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_trans_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_trans_info;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup;
import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_BillRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_shipment_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_supplier_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_trans_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_weight_dtlRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_trans_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_supplier_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
@Repository
public class Pur_return_approval_noteService_Imp implements Pur_return_approval_noteService{
	

	@Autowired
	Pur_return_approval_noteRepository pur_return_approval_noteRepository;
	
	@Autowired
	Pur_return_approval_weight_dtlRepository pur_return_approval_weight_dtlRepository;
	
	@Autowired
	Pur_return_approval_trans_infoRepository pur_return_approval_trans_infoRepository;
	
	@Autowired
	Pur_return_approval_shipment_dtlsRepository pur_return_approval_shipment_dtlsRepository;
	
	@Autowired
	Pur_return_approval_supplier_dtlsRepository pur_return_approval_supplier_dtlsRepository;
	
	@Autowired
	Pur_return_approval_docsRepository pur_return_approval_docsRepository;
	
	@Autowired
	Pur_return_approval_item_dtlsRepository pur_return_approval_item_dtlsRepository;
	
	@Autowired
	Pur_return_approval_broker_dtlsRepository pur_return_approval_broker_dtlsRepository;
	
	@Autowired
	Pur_return_approval_trans_dynRepository pur_return_approval_trans_dynRepository;
	
	@Autowired
	Pur_return_note_trans_dynRepository pur_return_note_trans_dynRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Pur_BillRepository pur_BillRepository;
	
	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Pur_good_receipt_item_detailsRepository pur_good_receipt_item_detailsRepository;
	
	public SequenceIdDTO getPRANSequenceId(String rtntype,String rtndate)
	{
		int slno=0;String prefix="PRA",rtype="";
		//String dt=Utility.convertDate(orderdate);
		String sno=pur_return_approval_noteRepository.countPRAOrder(rtntype,rtndate);
		if(rtntype.compareTo("Acceptance of Lower Rate")==0) {rtype="ALR";}
		if(rtntype.compareTo("Goods Return Due To Rejection")==0) {rtype="GDR";}
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = rtndate.split("-");
		prefix=prefix+"-"+rtype+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Pur_return_approval_note save(Pur_return_approval_note pran)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		
		if(pur_return_approval_noteRepository.countId() != null)
		{
			slno=Long.parseLong(pur_return_approval_noteRepository.countId());
		}
		String prefix="PRA";
 		String gen_sno=UniqueID.uniqueid(prefix, slno);
 		
 		pran.setPurreturnid(gen_sno);
 		
 		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pran.getPurreturnno());
		long nslno=0;String tprefix="PRA",rtype="";
		String tsno=pur_return_approval_noteRepository.countPRAOrder(pran.getPurreturntype(),pran.getPurreturndate());
		if(pran.getPurreturntype().compareTo("Acceptance of Lower Rate")==0) {rtype="ALR";}
		if(pran.getPurreturntype().compareTo("Goods Return Due To Rejection")==0) {rtype="GDR";}
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = pran.getPurreturndate().split("-");
		tprefix=tprefix+"-"+rtype+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pran.setPurreturnno(gen_tslno);
		System.err.println("Last:>>>"+pran.getPurreturnno());
		/*End checking transaction no for unique */
 		
 		pran.setModified_type("INSERTED");
 		pran.setInserted_by(userRepository.getUserDetails(pran.getUsername()).getName());
 		pran.setInserted_on(ldt);
 		pran.setUpdated_by("NA");
 		pran.setUpdated_on(ldt);
 		pran.setDeleted_by("NA");
 		pran.setDeleted_on(ldt);
 		
 		pran.setReapp_note_status(0);
 		pran.setWeighment_status(0);
 		pran.setPrn_status("Not Done");
 		
 		pran.setLoading_status("Not Done");
 		
 		if(pran.getSupplier().compareTo("0") !=0 && pran.getSupplier().compareTo("") !=0 && pran.getSupplier() !=null) {
			pran.setSuppliername(supp_bussiness_partnerRepository.getSupplierName(pran.getSupplier()).getBp_name());
		}else {pran.setSuppliername("None");}
		
		if(pran.getBusinessunit().compareTo("0") !=0 && pran.getBusinessunit().compareTo("") !=0 && pran.getBusinessunit() !=null) {
			pran.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(pran.getBusinessunit()).getBusinessunit_name());
		}else {pran.setBusinessunit_name("None");}
 		System.out.println("pran.getReturnbasedon()  "+ pran.getReturnbasedon());
		if(pran.getReturnbasedon().compareToIgnoreCase("GRN")==0)//grn
		{
			
				pur_good_receiptRepository.updateGRNPurReturn(pran.getReferance_id(),pran.getPurreturnid());
				Pur_good_receipt grn=pur_good_receiptRepository.getPurGoodRcptDtls(pran.getReferance_id());
				
				if(grn.isMultiunloadadvice()==true)//if multiple advice contain
				{
					
					String multiunload[]=grn.getReferance_id().split(",");
					//List<Wm_unload_advice> unloadlist=new ArrayList<Wm_unload_advice>();
					LinkedList<String> purlist = new LinkedList<String>();
					String finalunload="";
					for(int z=0;z<multiunload.length;z++) 
					{
						//unloadlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]));
						wm_unload_adviceRepository.updatereturnstatus(multiunload[z],pran.getPurreturnid());
						purlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]).getReferance_id());
						finalunload+="'"+multiunload[z]+"',";
					}
					
					String finaloutput=finalunload.substring(0, finalunload.length()-1);
					System.out.println("finaloutput "+ finaloutput );
					for(int p=0;p<purlist.size();p++) 
					{
						List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounloadmultiple(grn.getReferance_id(),purlist.get(p));
						boolean allcheck=false;
						for( int v=0;v<returnlist.size();v++) 
						{
							System.out.println("CHECK HERE "+returnlist.get(v));
							if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
							{
								allcheck=true;
							}
						}
						if(allcheck == true) 
						{
							
						}
						else //update purchase order and freeze it 
						{
							pur_OrderRepository.updatepurreturn(purlist.get(p),pran.getPurreturnid());
						}
						
					}
				
				}
				else 
				{
					Wm_unload_advice unload= wm_unload_adviceRepository.getUnloadId(grn.getReferance_id());// pur order id
					List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounload(grn.getReferance_id(),unload.getReferance_id());
					wm_unload_adviceRepository.updatereturnstatus(grn.getReferance_id(),pran.getPurreturnid());
					boolean allcheck=false;
					
					for( int v=0;v<returnlist.size();v++) 
					{
						if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
						{
							allcheck=true;
						}
					}
					if(allcheck == true) 
					{
						
					}
					else //update purchase order and freeze it 
					{
						pur_OrderRepository.updatepurreturn(unload.getReferance_id(),pran.getPurreturnid());
					}
					
					
				}
				
				//on basis of single grn changes are made as bellow
				pur_BillRepository.updatePurBillFullReturn(pran.getReferance_id(),pran.getPurreturnid());
			
		}
		
		if(pran.getReturnbasedon().compareToIgnoreCase("Purchase Order")==0)//pur order
		{
			
			//grnlist
			
			
			
			String multigrn[]=pran.getGrnlist().split(",");
			
			for(int a=0;a<multigrn.length;a++)
			{
				pur_good_receiptRepository.updateGRNPurReturn(multigrn[a],pran.getPurreturnid());
				Pur_good_receipt grn=pur_good_receiptRepository.getPurGoodRcptDtls(multigrn[a]);
				
				if(grn.isMultiunloadadvice()==true)//if multiple advice contain
				{


					String multiunload[]=grn.getReferance_id().split(",");//List<Wm_unload_advice> unloadlist=new ArrayList<Wm_unload_advice>();
				
					String finalunload="";
					for(int z=0;z<multiunload.length;z++) 
					{
						
						wm_unload_adviceRepository.updatereturnstatus(multiunload[z],pran.getPurreturnid());
						
						finalunload+="'"+multiunload[z]+"',";
					}
					
					String finaloutput=finalunload.substring(0, finalunload.length()-1);
					System.out.println("finaloutput "+ finaloutput );
					
						List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounloadmultiple(grn.getReferance_id(),pran.getReferance_id());//here change
						boolean allcheck=false;
						for( int v=0;v<returnlist.size();v++) 
						{
							System.out.println("CHECK HERE "+returnlist.get(v));
							if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
							{
								allcheck=true;
							}
						}
						if(allcheck == true) 
						{
							
						}
						else //update purchase order and freeze it 
						{
							pur_OrderRepository.updatepurreturn(pran.getReferance_id(),pran.getPurreturnid());
						}
						
					
					
				}
				else 
				{
					Wm_unload_advice unload= wm_unload_adviceRepository.getUnloadId(grn.getReferance_id());// pur order id
					List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounload(grn.getReferance_id(),unload.getReferance_id());
					wm_unload_adviceRepository.updatereturnstatus(grn.getReferance_id(),pran.getPurreturnid());
					boolean allcheck=false;
					
					for( int v=0;v<returnlist.size();v++) 
					{
						if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
						{
							allcheck=true;
						}
					}
					if(allcheck == true) 
					{
						
					}
					else //update purchase order and freeze it 
					{
						pur_OrderRepository.updatepurreturn(unload.getReferance_id(),pran.getPurreturnid());
					}
					
					
				}
				
				pur_BillRepository.updatePurBillFullReturn(grn.getReferance_id(),pran.getPurreturnid());
				
			}
			
			
			
			
		}
		
		if(pran.getReturnbasedon().compareToIgnoreCase("Purchase Bill")==0)//pur bill
		{//refid:PB00022
			pur_BillRepository.updatePurBillReturn(pran.getReferance_id(),pran.getPurreturnid());
			
			Pur_Bill purbill=pur_BillRepository.getPurBillNo(pran.getReferance_id());
			
			pur_good_receiptRepository.updateGRNPurReturn(purbill.getReferance_id(),pran.getPurreturnid());
			
			Pur_good_receipt grn=pur_good_receiptRepository.getPurGoodRcptDtls(purbill.getReferance_id());
			
			if(grn.isMultiunloadadvice()==true)//if multiple advice contain
			{
				
				String multiunload[]=grn.getReferance_id().split(",");
				//List<Wm_unload_advice> unloadlist=new ArrayList<Wm_unload_advice>();
				LinkedList<String> purlist = new LinkedList<String>();
				String finalunload="";
				for(int z=0;z<multiunload.length;z++) 
				{
					//unloadlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]));
					wm_unload_adviceRepository.updatereturnstatus(multiunload[z],pran.getPurreturnid());
					purlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]).getReferance_id());
				//	finalunload+="'"+multiunload[z]+"',";
				}
				
			//	String finaloutput=finalunload.substring(0, finalunload.length()-1);
				System.out.println("finaloutput "+ grn.getReferance_id());
				for(int p=0;p<purlist.size();p++) 
				{
					System.out.println(" pur oder "+purlist.get(p));
					List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounloadmultiple(grn.getReferance_id(),purlist.get(p));
					boolean allcheck=false;
					for( int v=0;v<returnlist.size();v++) 
					{
						System.out.println("//"+returnlist.get(v));
						if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
						{
							allcheck=true;
						}
					}
					if(allcheck == true) 
					{
						
					}
					else //update purchase order and freeze it 
					{
						pur_OrderRepository.updatepurreturn(purlist.get(p),pran.getPurreturnid());
					}
					
				}
			
			}
			else 
			{
				Wm_unload_advice unload= wm_unload_adviceRepository.getUnloadId(grn.getReferance_id());// pur order id
				List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounload(grn.getReferance_id(),unload.getReferance_id());
				System.out.println(grn.getReferance_id()+ " / "+pran.getPurreturnid() +" // "+ returnlist.size() + " // " + unload.getReferance_id());
				wm_unload_adviceRepository.updatereturnstatus(grn.getReferance_id(),pran.getPurreturnid());
				boolean allcheck=false;
				
				for( int v=0;v<returnlist.size();v++) 
				{
					System.out.println(" v :: "+returnlist.get(v));
					if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
					{
						allcheck=true;
					}
				}
				if(allcheck == true) 
				{
					
				}
				else //update purchase order and freeze it 
				{
					pur_OrderRepository.updatepurreturn(unload.getReferance_id(),pran.getPurreturnid());
				}
				
				
			}
			
		}
		
 			//Dynamic
			Set<Pur_return_approval_docs> pran1 = new HashSet<Pur_return_approval_docs>();
			pran1.addAll(pran.getPur_return_approval_docs());
			for(Pur_return_approval_docs prad : pran1)
			{
				prad.setPurreturnid(pran.getPurreturnid());
				prad.setPurreturnno(pran.getPurreturnno());
				prad.setCompany_id(pran.getCompany_id());
				prad.setFin_year(pran.getFin_year());
				prad.setModified_type(pran.getModified_type());
				prad.setInserted_by(pran.getInserted_by());
				prad.setInserted_on(pran.getInserted_on());
				prad.setUpdated_by(pran.getUpdated_by());
				prad.setUpdated_on(pran.getUpdated_on());
				prad.setDeleted_by(pran.getDeleted_by());
				prad.setDeleted_on(pran.getDeleted_on());
			}
			pran.setPur_return_approval_docs(pran1);
			
			//Dynamic
			Set<Pur_return_approval_item_dtls> pran2 = new HashSet<Pur_return_approval_item_dtls>();
			pran2.addAll(pran.getPur_return_approval_item_dtls());
			for(Pur_return_approval_item_dtls praid : pran2)
			{
				praid.setPurreturnid(pran.getPurreturnid());
				praid.setPurreturnno(pran.getPurreturnno());
				praid.setItemname(item_masterRepository.itemNameById(praid.getItemcode()).getItem_name());
				if(praid.getPacking().compareTo("")!=0 && praid.getPacking().compareTo("0")!=0) {
					praid.setPackingname(item_masterRepository.itemNameById(praid.getPacking()).getItem_name());
				}
				praid.setCompany_id(pran.getCompany_id());
				praid.setFin_year(pran.getFin_year());
				praid.setModified_type(pran.getModified_type());
				praid.setInserted_by(pran.getInserted_by());
				praid.setInserted_on(pran.getInserted_on());
				praid.setUpdated_by(pran.getUpdated_by());
				praid.setUpdated_on(pran.getUpdated_on());
				praid.setDeleted_by(pran.getDeleted_by());
				praid.setDeleted_on(pran.getDeleted_on());
			}
			pran.setPur_return_approval_item_dtls(pran2);
			
			//Dynamic
			Set<Pur_return_approval_broker_dtls> pran3 = new HashSet<Pur_return_approval_broker_dtls>();
			pran3.addAll(pran.getPur_return_approval_broker_dtls());
			for(Pur_return_approval_broker_dtls prabd : pran3)
			{
				prabd.setPurreturnid(pran.getPurreturnid());
				prabd.setPurreturnno(pran.getPurreturnno());
				prabd.setCompany_id(pran.getCompany_id());
				prabd.setFin_year(pran.getFin_year());
				prabd.setModified_type(pran.getModified_type());
				prabd.setInserted_by(pran.getInserted_by());
				prabd.setInserted_on(pran.getInserted_on());
				prabd.setUpdated_by(pran.getUpdated_by());
				prabd.setUpdated_on(pran.getUpdated_on());
				prabd.setDeleted_by(pran.getDeleted_by());
				prabd.setDeleted_on(pran.getDeleted_on());
			}
			pran.setPur_return_approval_broker_dtls(pran3);
			
			 		
			//Static
			Set<Pur_return_approval_weight_dtl> pran5 = new HashSet<Pur_return_approval_weight_dtl>();
			pran5.add(pran.getPur_return_approval_weight_dtl());
			for(Pur_return_approval_weight_dtl prawd : pran5)
			{
				prawd.setPurreturnid(pran.getPurreturnid());
				prawd.setPurreturnno(pran.getPurreturnno());
				prawd.setCompany_id(pran.getCompany_id());
				prawd.setFin_year(pran.getFin_year());
				prawd.setModified_type(pran.getModified_type());
				prawd.setInserted_by(pran.getInserted_by());
				prawd.setInserted_on(pran.getInserted_on());
				prawd.setUpdated_by(pran.getUpdated_by());
				prawd.setUpdated_on(pran.getUpdated_on());
				prawd.setDeleted_by(pran.getDeleted_by());
				prawd.setDeleted_on(pran.getDeleted_on());
			}
			
			if(!pran5.isEmpty())
			{
				pran.setPur_return_approval_weight_dtl(pran5.iterator().next());
			}
			
			//Static
			Set<Pur_return_approval_trans_info> pran6 = new HashSet<Pur_return_approval_trans_info>();
			pran6.add(pran.getPur_return_approval_trans_info());
			for(Pur_return_approval_trans_info prati : pran6)
			{
				prati.setPurreturnid(pran.getPurreturnid());
				prati.setPurreturnno(pran.getPurreturnno());
				prati.setCompany_id(pran.getCompany_id());
				prati.setFin_year(pran.getFin_year());
				prati.setModified_type(pran.getModified_type());
				prati.setInserted_by(pran.getInserted_by());
				prati.setInserted_on(pran.getInserted_on());
				prati.setUpdated_by(pran.getUpdated_by());
				prati.setUpdated_on(pran.getUpdated_on());
				prati.setDeleted_by(pran.getDeleted_by());
				prati.setDeleted_on(pran.getDeleted_on());
			}
			
			if(!pran6.isEmpty())
			{
				pran.setPur_return_approval_trans_info(pran6.iterator().next());
			}
			
			//Static
			Set<Pur_return_approval_shipment_dtls> pran7 = new HashSet<Pur_return_approval_shipment_dtls>();
			pran7.add(pran.getPur_return_approval_shipment_dtls());
			for(Pur_return_approval_shipment_dtls prasd : pran7)
			{
				prasd.setPurreturnid(pran.getPurreturnid());
				prasd.setPurreturnno(pran.getPurreturnno());
				prasd.setCompany_id(pran.getCompany_id());
				prasd.setFin_year(pran.getFin_year());
				prasd.setModified_type(pran.getModified_type());
				prasd.setInserted_by(pran.getInserted_by());
				prasd.setInserted_on(pran.getInserted_on());
				prasd.setUpdated_by(pran.getUpdated_by());
				prasd.setUpdated_on(pran.getUpdated_on());
				prasd.setDeleted_by(pran.getDeleted_by());
				prasd.setDeleted_on(pran.getDeleted_on());
			}
			
			if(!pran7.isEmpty())
			{
				pran.setPur_return_approval_shipment_dtls(pran7.iterator().next());
			}
			
			//Static
			Set<Pur_return_approval_supplier_dtls> pran8 = new HashSet<Pur_return_approval_supplier_dtls>();
			pran8.addAll(pran.getPur_return_approval_supplier_dtls());
			for(Pur_return_approval_supplier_dtls prasud : pran8)
			{
				prasud.setPurreturnid(pran.getPurreturnid());
				prasud.setPurreturnno(pran.getPurreturnno());
				prasud.setCompany_id(pran.getCompany_id());
				prasud.setFin_year(pran.getFin_year());
				prasud.setModified_type(pran.getModified_type());
				prasud.setInserted_by(pran.getInserted_by());
				prasud.setInserted_on(pran.getInserted_on());
				prasud.setUpdated_by(pran.getUpdated_by());
				prasud.setUpdated_on(pran.getUpdated_on());
				prasud.setDeleted_by(pran.getDeleted_by());
				prasud.setDeleted_on(pran.getDeleted_on());
			}
		    pran.setPur_return_approval_supplier_dtls(pran8);
 		
 		return pur_return_approval_noteRepository.save(pran);
		
	}
	
	public Pur_return_approval_note findOne(Long id) {
		Optional<Pur_return_approval_note> op=this.pur_return_approval_noteRepository.findById(id);
		return op.get();
	}
	
	public List<Pur_return_approval_note> getPurReturnApprovalNote(String company,String finyear)
	{
		List<Pur_return_approval_note> rtnAppNotesList=new ArrayList<Pur_return_approval_note>();
		rtnAppNotesList.addAll(pur_return_approval_noteRepository.findAllRtnAppNotes(company,finyear));
		return rtnAppNotesList;
	}
	
	public List<Pur_return_approval_noteDTO> getPurRtnAppNoteForLA(String bunit,String supplier,String tdate,String company,String finyear)
	{
		List<Pur_return_approval_note> appNote=new ArrayList<Pur_return_approval_note>();
		System.err.println("Got val: "+bunit+","+supplier+","+tdate+","+company+","+finyear);
		appNote.addAll(pur_return_approval_noteRepository.getPurRtnAppNoteForLA(bunit,supplier,tdate,company,finyear));
			
		Type listType=new TypeToken<List<Pur_return_approval_noteDTO>>() {}.getType();
		
		List<Pur_return_approval_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public List<Pur_return_approval_noteDTO> getPurRtnAppNoteThruWe(String bunit,String supplier,String tdate,String company,String finyear)
	{
		List<Pur_return_approval_note> appNote=new ArrayList<Pur_return_approval_note>();
		appNote.addAll(pur_return_approval_noteRepository.getPurRtnAppNoteThruWe(bunit,supplier,tdate,company,finyear));
			
		Type listType=new TypeToken<List<Pur_return_approval_noteDTO>>() {}.getType();
		
		List<Pur_return_approval_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public List<Pur_return_approval_noteDTO> getPurRtnAppNoteLowRate(String bunit,String supplier,String tdate,String company,String finyear)
	{
		List<Pur_return_approval_note> appNote=new ArrayList<Pur_return_approval_note>();
		appNote.addAll(pur_return_approval_noteRepository.getPurRtnAppNoteLowRate(bunit,supplier,tdate,company,finyear));
			
		Type listType=new TypeToken<List<Pur_return_approval_noteDTO>>() {}.getType();
		
		List<Pur_return_approval_noteDTO> appNoteLow=new ModelMapper().map(appNote,listType);
		
		return appNoteLow;
	}
	
	public Pur_return_approval_noteDTO getPurRtnAppNoteDtls(String purreturnid)
	{
		Pur_return_approval_note appNote=pur_return_approval_noteRepository.getPurRtnAppNoteDtls(purreturnid);
			
		Type listType=new TypeToken<Pur_return_approval_noteDTO>() {}.getType();
		
		Pur_return_approval_noteDTO appNoteDtls=new ModelMapper().map(appNote,listType);
		
		return appNoteDtls;
	}
	
	public List<Pur_return_approval_docsDTO> getPurReturnApprovalDoc(String purreturnid)
	{
		List<Pur_return_approval_docs> modelList=pur_return_approval_docsRepository.getPurReturnApprovalDoc(purreturnid);
		
		Type listType=new TypeToken<List<Pur_return_approval_docsDTO>>() {}.getType();
		
		List<Pur_return_approval_docsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_return_approval_item_dtlsDTO> getPurReturnApprovalID(String purreturnid)
	{
		List<Pur_return_approval_item_dtls> modelList=pur_return_approval_item_dtlsRepository.getPurReturnApprovalID(purreturnid);
		
		Type listType=new TypeToken<List<Pur_return_approval_item_dtlsDTO>>() {}.getType();
		
		List<Pur_return_approval_item_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_return_approval_broker_dtlsDTO> getPurReturnApprovalBD(String purreturnid)
	{
		List<Pur_return_approval_broker_dtls> modelList=pur_return_approval_broker_dtlsRepository.getPurReturnApprovalBD(purreturnid);
		
		Type listType=new TypeToken<List<Pur_return_approval_broker_dtlsDTO>>() {}.getType();
		
		List<Pur_return_approval_broker_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_return_approval_trans_dynDTO> getPurReturnApprovalTD(String purreturnid)
	{
		List<Pur_return_approval_trans_dyn> modelList=pur_return_note_trans_dynRepository.getPurReturnApprovalTD(purreturnid);
		
			
		Type listType=new TypeToken<List<Pur_return_approval_trans_dynDTO>>() {}.getType();
		
		List<Pur_return_approval_trans_dynDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public Pur_return_approval_weight_dtlDTO getPurReturnApprovalWD(String purreturnid)
	{
		Pur_return_approval_weight_dtl modelList=pur_return_approval_weight_dtlRepository.getPurReturnApprovalWD(purreturnid);
		
		Type listType=new TypeToken<Pur_return_approval_weight_dtlDTO>() {}.getType();
		
		Pur_return_approval_weight_dtlDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
		
	}
	
	public Pur_return_approval_trans_infoDTO getPurReturnApprovalTI(String purreturnid)
	{
		Pur_return_approval_trans_info modelList=pur_return_approval_trans_infoRepository.getPurReturnApprovalTI(purreturnid);
		
		Type listType=new TypeToken<Pur_return_approval_trans_infoDTO>() {}.getType();
		
		Pur_return_approval_trans_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
		
	}
	
	public Pur_return_approval_shipment_dtlsDTO getPurReturnApprovalSD(String purreturnid)
	{
		Pur_return_approval_shipment_dtls modelList=pur_return_approval_shipment_dtlsRepository.getPurReturnApprovalSD(purreturnid);
		
		Type listType=new TypeToken<Pur_return_approval_shipment_dtlsDTO>() {}.getType();
		
		Pur_return_approval_shipment_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
		
	}
	
	public Pur_return_approval_supplier_dtlsDTO getPurReturnApprovalSuD(String purreturnid)
	{
		Pur_return_approval_supplier_dtls modelList=pur_return_approval_supplier_dtlsRepository.getPurReturnApprovalWD(purreturnid);
		
		Type listType=new TypeToken<Pur_return_approval_supplier_dtlsDTO>() {}.getType();
		
		Pur_return_approval_supplier_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
		
	}
	
	@Transactional
	public  Pur_return_approval_note update(Pur_return_approval_note pran,long id)
	{
		Optional<Pur_return_approval_note> op=pur_return_approval_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
 		
 		pran.setPurreturnno(op.get().getPurreturnno());
 		pran.setModified_type("INSERTED");
 		pran.setInserted_by(op.get().getInserted_by());
 		pran.setInserted_on(op.get().getInserted_on());
 		pran.setUpdated_by(userRepository.getUserDetails(pran.getUsername()).getName());
 		pran.setUpdated_on(ldt);
 		pran.setDeleted_by("NA");
 		pran.setDeleted_on(ldt);
 		
 		pran.setReapp_note_status(0);
 		pran.setWeighment_status(0);
 		pran.setPrn_status("Not Done");
 		
 		pran.setLoading_status("Not Done");
 		
 		if(pran.getSupplier().compareTo("0") !=0 && pran.getSupplier().compareTo("") !=0 && pran.getSupplier() !=null) {
			pran.setSuppliername(supp_bussiness_partnerRepository.getSupplierName(pran.getSupplier()).getBp_name());
		}else {pran.setSuppliername("None");}
		
		if(pran.getBusinessunit().compareTo("0") !=0 && pran.getBusinessunit().compareTo("") !=0 && pran.getBusinessunit() !=null) {
			pran.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(pran.getBusinessunit()).getBusinessunit_name());
		}else {pran.setBusinessunit_name("None");}
 		
 		if(op.isPresent())
		{
			pran.setId(id);
		}
 		
 		pur_BillRepository.updateTimePurBillPurReturn(op.get().getPurreturnid());//bill
 		pur_good_receiptRepository.updateTimeGRNPurReturn(op.get().getPurreturnid());//grn
 		wm_unload_adviceRepository.updateTimeUnloadAdvicePurReturn(op.get().getPurreturnid());//unload
 		pur_OrderRepository.updateTimePurOrderPurReturn(op.get().getPurreturnid());//order
 		
 		if(pran.getReturnbasedon().compareToIgnoreCase("GRN")==0)//grn
		{
 			
				pur_good_receiptRepository.updateGRNPurReturn(pran.getReferance_id(),pran.getPurreturnid());
				Pur_good_receipt grn=pur_good_receiptRepository.getPurGoodRcptDtls(pran.getReferance_id());
				
				if(grn.isMultiunloadadvice()==true)//if multiple advice contain
				{
					
					String multiunload[]=grn.getReferance_id().split(",");
					//List<Wm_unload_advice> unloadlist=new ArrayList<Wm_unload_advice>();
					LinkedList<String> purlist = new LinkedList<String>();
					String finalunload="";
					for(int z=0;z<multiunload.length;z++) 
					{
						//unloadlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]));
						wm_unload_adviceRepository.updatereturnstatus(multiunload[z],pran.getPurreturnid());
						purlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]).getReferance_id());
						finalunload+="'"+multiunload[z]+"',";
					}
					
					String finaloutput=finalunload.substring(0, finalunload.length()-1);
					
					for(int p=0;p<purlist.size();p++) 
					{
						List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounloadmultiple(grn.getReferance_id(),purlist.get(p));
						boolean allcheck=false;
						for( int v=0;v<returnlist.size();v++) 
						{
							if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
							{
								allcheck=true;
							}
						}
						if(allcheck == true) 
						{
							
						}
						else //update purchase order and freeze it 
						{
							pur_OrderRepository.updatepurreturn(purlist.get(p),pran.getPurreturnid());
						}
						
					}
				
				}
				else 
				{
					Wm_unload_advice unload= wm_unload_adviceRepository.getUnloadId(grn.getReferance_id());// pur order id
					List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounload(grn.getReferance_id(),unload.getReferance_id());
					wm_unload_adviceRepository.updatereturnstatus(grn.getReferance_id(),pran.getPurreturnid());
					boolean allcheck=false;
					
					for( int v=0;v<returnlist.size();v++) 
					{
						if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
						{
							allcheck=true;
						}
					}
					if(allcheck == true) 
					{
						
					}
					else //update purchase order and freeze it 
					{
						pur_OrderRepository.updatepurreturn(unload.getReferance_id(),pran.getPurreturnid());
					}
					
					
				}
				
				//on basis of single grn changes are made as bellow
				pur_BillRepository.updatePurBillFullReturn(pran.getReferance_id(),pran.getPurreturnid());
			
		}
		
		if(pran.getReturnbasedon().compareToIgnoreCase("Purchase Order")==0)//pur order
		{

			
			//grnlist
			
			
			
			String multigrn[]=pran.getGrnlist().split(",");
			
			for(int a=0;a<multigrn.length;a++)
			{
				pur_good_receiptRepository.updateGRNPurReturn(multigrn[a],pran.getPurreturnid());
				Pur_good_receipt grn=pur_good_receiptRepository.getPurGoodRcptDtls(multigrn[a]);
				
				if(grn.isMultiunloadadvice()==true)//if multiple advice contain
				{


					String multiunload[]=grn.getReferance_id().split(",");//List<Wm_unload_advice> unloadlist=new ArrayList<Wm_unload_advice>();
				
					String finalunload="";
					for(int z=0;z<multiunload.length;z++) 
					{
						
						wm_unload_adviceRepository.updatereturnstatus(multiunload[z],pran.getPurreturnid());
						
						finalunload+="'"+multiunload[z]+"',";
					}
					
					String finaloutput=finalunload.substring(0, finalunload.length()-1);
					System.out.println("finaloutput "+ finaloutput );
					
						List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounloadmultiple(grn.getReferance_id(),pran.getReferance_id());//here change
						boolean allcheck=false;
						for( int v=0;v<returnlist.size();v++) 
						{
							System.out.println("CHECK HERE "+returnlist.get(v));
							if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
							{
								allcheck=true;
							}
						}
						if(allcheck == true) 
						{
							
						}
						else //update purchase order and freeze it 
						{
							pur_OrderRepository.updatepurreturn(pran.getReferance_id(),pran.getPurreturnid());
						}
						
					
					
				}
				else 
				{
					Wm_unload_advice unload= wm_unload_adviceRepository.getUnloadId(grn.getReferance_id());// pur order id
					List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounload(grn.getReferance_id(),unload.getReferance_id());
					wm_unload_adviceRepository.updatereturnstatus(grn.getReferance_id(),pran.getPurreturnid());
					boolean allcheck=false;
					
					for( int v=0;v<returnlist.size();v++) 
					{
						if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
						{
							allcheck=true;
						}
					}
					if(allcheck == true) 
					{
						
					}
					else //update purchase order and freeze it 
					{
						pur_OrderRepository.updatepurreturn(unload.getReferance_id(),pran.getPurreturnid());
					}
					
					
				}
				
				pur_BillRepository.updatePurBillFullReturn(grn.getReferance_id(),pran.getPurreturnid());
				
			}
			
			
			
			
		}
		
		if(pran.getReturnbasedon().compareToIgnoreCase("Purchase Bill")==0)//pur bill
		{//refid:PB00022
			pur_BillRepository.updatePurBillReturn(pran.getReferance_id(),pran.getPurreturnid());
			
			Pur_Bill purbill=pur_BillRepository.getPurBillNo(pran.getReferance_id());
			
			pur_good_receiptRepository.updateGRNPurReturn(purbill.getReferance_id(),pran.getPurreturnid());
			
			Pur_good_receipt grn=pur_good_receiptRepository.getPurGoodRcptDtls(purbill.getReferance_id());
			
			if(grn.isMultiunloadadvice()==true)//if multiple advice contain
			{
				
				String multiunload[]=grn.getReferance_id().split(",");
				//List<Wm_unload_advice> unloadlist=new ArrayList<Wm_unload_advice>();
				LinkedList<String> purlist = new LinkedList<String>();
				String finalunload="";
				for(int z=0;z<multiunload.length;z++) 
				{
					//unloadlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]));
					wm_unload_adviceRepository.updatereturnstatus(multiunload[z],pran.getPurreturnid());
					purlist.add(wm_unload_adviceRepository.getUnloadId(multiunload[z]).getReferance_id());
					finalunload+="'"+multiunload[z]+"',";
				}
				
				String finaloutput=finalunload.substring(0, finalunload.length()-1);
				
				for(int p=0;p<purlist.size();p++) 
				{
					List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounloadmultiple(grn.getReferance_id(),purlist.get(p));
					boolean allcheck=false;
					for( int v=0;v<returnlist.size();v++) 
					{
						if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
						{
							allcheck=true;
						}
					}
					if(allcheck == true) 
					{
						
					}
					else //update purchase order and freeze it 
					{
						pur_OrderRepository.updatepurreturn(purlist.get(p),pran.getPurreturnid());
					}
					
				}
			
			}
			else 
			{
				Wm_unload_advice unload= wm_unload_adviceRepository.getUnloadId(grn.getReferance_id());// pur order id
				List<String> returnlist= wm_unload_adviceRepository.checkpurorderuniquerespcttounload(grn.getReferance_id(),unload.getReferance_id());
				wm_unload_adviceRepository.updatereturnstatus(grn.getReferance_id(),pran.getPurreturnid());
				boolean allcheck=false;
				
				for( int v=0;v<returnlist.size();v++) 
				{
					if(returnlist.get(v).compareToIgnoreCase("No") == 0) 
					{
						allcheck=true;
					}
				}
				if(allcheck == true) 
				{
					
				}
				else //update purchase order and freeze it 
				{
					pur_OrderRepository.updatepurreturn(unload.getReferance_id(),pran.getPurreturnid());
				}
				
				
			}
			
		}
		
 			//Dynamic
 			pur_return_approval_docsRepository.updatePur_return_approval_docs(op.get().getPurreturnid());
			Set<Pur_return_approval_docs> pran1 = new HashSet<Pur_return_approval_docs>();
			pran1.addAll(pran.getPur_return_approval_docs());
			for(Pur_return_approval_docs prad : pran1)
			{
				prad.setPurreturnid(op.get().getPurreturnid());
				prad.setPurreturnno(pran.getPurreturnno());
				prad.setCompany_id(pran.getCompany_id());
				prad.setFin_year(pran.getFin_year());
				prad.setModified_type(pran.getModified_type());
				prad.setInserted_by(pran.getInserted_by());
				prad.setInserted_on(pran.getInserted_on());
				prad.setUpdated_by(pran.getUpdated_by());
				prad.setUpdated_on(pran.getUpdated_on());
				prad.setDeleted_by(pran.getDeleted_by());
				prad.setDeleted_on(pran.getDeleted_on());
			}
			pran.setPur_return_approval_docs(pran1);
			
			//Dynamic
			pur_return_approval_item_dtlsRepository.updatePur_return_approval_item_dtls(op.get().getPurreturnid());
			Set<Pur_return_approval_item_dtls> pran2 = new HashSet<Pur_return_approval_item_dtls>();
			pran2.addAll(pran.getPur_return_approval_item_dtls());
			for(Pur_return_approval_item_dtls praid : pran2)
			{
				praid.setPurreturnid(op.get().getPurreturnid());
				praid.setPurreturnno(pran.getPurreturnno());
				praid.setItemname(item_masterRepository.itemNameById(praid.getItemcode()).getItem_name());
				if(praid.getPacking().compareTo("")!=0 && praid.getPacking().compareTo("0")!=0) {
					praid.setPackingname(item_masterRepository.itemNameById(praid.getPacking()).getItem_name());
				}
				praid.setCompany_id(pran.getCompany_id());
				praid.setFin_year(pran.getFin_year());
				praid.setModified_type(pran.getModified_type());
				praid.setInserted_by(pran.getInserted_by());
				praid.setInserted_on(pran.getInserted_on());
				praid.setUpdated_by(pran.getUpdated_by());
				praid.setUpdated_on(pran.getUpdated_on());
				praid.setDeleted_by(pran.getDeleted_by());
				praid.setDeleted_on(pran.getDeleted_on());
			}
			pran.setPur_return_approval_item_dtls(pran2);
			
			//Dynamic
			pur_return_approval_broker_dtlsRepository.updatePur_return_approval_broker_dtls(op.get().getPurreturnid());
			Set<Pur_return_approval_broker_dtls> pran3 = new HashSet<Pur_return_approval_broker_dtls>();
			pran3.addAll(pran.getPur_return_approval_broker_dtls());
			for(Pur_return_approval_broker_dtls prabd : pran3)
			{
				prabd.setPurreturnid(op.get().getPurreturnid());
				prabd.setPurreturnno(pran.getPurreturnno());
				prabd.setCompany_id(pran.getCompany_id());
				prabd.setFin_year(pran.getFin_year());
				prabd.setModified_type(pran.getModified_type());
				prabd.setInserted_by(pran.getInserted_by());
				prabd.setInserted_on(pran.getInserted_on());
				prabd.setUpdated_by(pran.getUpdated_by());
				prabd.setUpdated_on(pran.getUpdated_on());
				prabd.setDeleted_by(pran.getDeleted_by());
				prabd.setDeleted_on(pran.getDeleted_on());
			}
			pran.setPur_return_approval_broker_dtls(pran3);
			
			
			//Static
			pur_return_approval_weight_dtlRepository.updatePur_return_approval_weight_dtl(op.get().getPurreturnid());
			Set<Pur_return_approval_weight_dtl> pran5 = new HashSet<Pur_return_approval_weight_dtl>();
			pran5.add(pran.getPur_return_approval_weight_dtl());
			for(Pur_return_approval_weight_dtl prawd : pran5)
			{
				prawd.setPurreturnid(op.get().getPurreturnid());
				prawd.setPurreturnno(pran.getPurreturnno());
				prawd.setCompany_id(pran.getCompany_id());
				prawd.setFin_year(pran.getFin_year());
				prawd.setModified_type(pran.getModified_type());
				prawd.setInserted_by(pran.getInserted_by());
				prawd.setInserted_on(pran.getInserted_on());
				prawd.setUpdated_by(pran.getUpdated_by());
				prawd.setUpdated_on(pran.getUpdated_on());
				prawd.setDeleted_by(pran.getDeleted_by());
				prawd.setDeleted_on(pran.getDeleted_on());
			}
			
			if(!pran5.isEmpty())
			{
				pran.setPur_return_approval_weight_dtl(pran5.iterator().next());
			}
			
			//Static
			pur_return_approval_trans_infoRepository.updatePur_return_approval_trans_info(op.get().getPurreturnid());
			Set<Pur_return_approval_trans_info> pran6 = new HashSet<Pur_return_approval_trans_info>();
			pran6.add(pran.getPur_return_approval_trans_info());
			for(Pur_return_approval_trans_info prati : pran6)
			{
				prati.setPurreturnid(op.get().getPurreturnid());
				prati.setPurreturnno(pran.getPurreturnno());
				prati.setCompany_id(pran.getCompany_id());
				prati.setFin_year(pran.getFin_year());
				prati.setModified_type(pran.getModified_type());
				prati.setInserted_by(pran.getInserted_by());
				prati.setInserted_on(pran.getInserted_on());
				prati.setUpdated_by(pran.getUpdated_by());
				prati.setUpdated_on(pran.getUpdated_on());
				prati.setDeleted_by(pran.getDeleted_by());
				prati.setDeleted_on(pran.getDeleted_on());
			}
			
			if(!pran6.isEmpty())
			{
				pran.setPur_return_approval_trans_info(pran6.iterator().next());
			}
			
			//Static
			pur_return_approval_shipment_dtlsRepository.updatePur_return_approval_shipment_dtls(op.get().getPurreturnid());
			Set<Pur_return_approval_shipment_dtls> pran7 = new HashSet<Pur_return_approval_shipment_dtls>();
			pran7.add(pran.getPur_return_approval_shipment_dtls());
			for(Pur_return_approval_shipment_dtls prasd : pran7)
			{
				prasd.setPurreturnid(op.get().getPurreturnid());
				prasd.setPurreturnno(pran.getPurreturnno());
				prasd.setCompany_id(pran.getCompany_id());
				prasd.setFin_year(pran.getFin_year());
				prasd.setModified_type(pran.getModified_type());
				prasd.setInserted_by(pran.getInserted_by());
				prasd.setInserted_on(pran.getInserted_on());
				prasd.setUpdated_by(pran.getUpdated_by());
				prasd.setUpdated_on(pran.getUpdated_on());
				prasd.setDeleted_by(pran.getDeleted_by());
				prasd.setDeleted_on(pran.getDeleted_on());
			}
			
			if(!pran7.isEmpty())
			{
				pran.setPur_return_approval_shipment_dtls(pran7.iterator().next());
			}
			
			
			//Static
			pur_return_approval_supplier_dtlsRepository.updatePur_return_approval_supplier_dtls(op.get().getPurreturnid());
			Set<Pur_return_approval_supplier_dtls> pran8 = new HashSet<Pur_return_approval_supplier_dtls>();
			pran8.addAll(pran.getPur_return_approval_supplier_dtls());
			for(Pur_return_approval_supplier_dtls prasud : pran8)
			{
				prasud.setPurreturnid(op.get().getPurreturnid());
				prasud.setPurreturnno(pran.getPurreturnno());
				prasud.setCompany_id(pran.getCompany_id());
				prasud.setFin_year(pran.getFin_year());
				prasud.setModified_type(pran.getModified_type());
				prasud.setInserted_by(pran.getInserted_by());
				prasud.setInserted_on(pran.getInserted_on());
				prasud.setUpdated_by(pran.getUpdated_by());
				prasud.setUpdated_on(pran.getUpdated_on());
				prasud.setDeleted_by(pran.getDeleted_by());
				prasud.setDeleted_on(pran.getDeleted_on());
			}
			
			pran.setPur_return_approval_supplier_dtls(pran8);
 		
 		return pur_return_approval_noteRepository.save(pran);
	}
	
	
	public List<Map<String,Object>> getReturnApprovalPopupData(String date,String bunit,String supplier,String returnbasedon,String finyear,String compid)
	{
		List<Map<String, Object>> purReturnList = new ArrayList<Map<String, Object>>();
		//System.out.println("date"+date+"//"+bunit+"//"+supplier+"//"+finyear+"//"+compid+"/"+returnbasedon);
		if(returnbasedon.compareToIgnoreCase("Purchase Bill")==0)
		{
			purReturnList.addAll(pur_BillRepository.getReturnPurBillData(date,bunit,supplier,finyear,compid));
		}
		
		if(returnbasedon.compareToIgnoreCase("GRN")==0)
		{
			purReturnList.addAll(pur_good_receiptRepository.getReturnGRNData(date,bunit,supplier,finyear,compid));
		}
		
		if(returnbasedon.compareToIgnoreCase("Purchase Order")==0)
		{
			purReturnList.addAll(pur_OrderRepository.getReturnPurOrderData(date,bunit,supplier,finyear,compid));
		}
		
		return purReturnList;
	}
	
	
	public List<Map<String,Object>> getgrnlistbypurorder(String pur_orderid)
	{
		List<Map<String, Object>> grnlist = new ArrayList<Map<String, Object>>();
		
		List<String>  unloadid=  wm_unload_adviceRepository.getunlaodidbyporder(pur_orderid);
		
		for(int v=0;v<unloadid.size();v++) 
		{
			grnlist.addAll(pur_good_receiptRepository.getlistbyunload(unloadid.get(v)));
		}
		
		
		return grnlist;
	}
	
	
	public List<Map<String,Object>> getgrnitemlist(String grnlist)
	{
		List<Map<String, Object>> grnitemlist = new ArrayList<Map<String, Object>>();
		
		String []splitgrn=grnlist.split(",");
		for(int i=0;i<splitgrn.length;i++) 
		 {
			grnitemlist.addAll(pur_good_receipt_item_detailsRepository.getgrnitemlist(splitgrn[i]));
		 }
		return grnitemlist;
	}
}
