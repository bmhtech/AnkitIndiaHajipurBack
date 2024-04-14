package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Adjustmentdetails;
import com.AnkitIndia.jwtauthentication.model.Invoice_accountdetails;
import com.AnkitIndia.jwtauthentication.model.Party_bill_payment;
import com.AnkitIndia.jwtauthentication.model.Party_bill_payment_details;
import com.AnkitIndia.jwtauthentication.model.Partyoutstanding_invoice;
import com.AnkitIndia.jwtauthentication.repository.AdjustmentdetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_accountdetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OutstandingledgerRepository;
import com.AnkitIndia.jwtauthentication.repository.Party_bill_paymentRepository;
import com.AnkitIndia.jwtauthentication.repository.Partyoutstanding_invoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Receipt_accounttransactionRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class Party_bill_paymentService_Imp implements Party_bill_paymentService{

	@Autowired
	Party_bill_paymentRepository party_bill_paymentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Partyoutstanding_invoiceRepository partyoutstanding_invoiceRepository;
	
	@Autowired
	OutstandingledgerRepository outstandingledgerRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Receipt_accounttransactionRepository receipt_accounttransactionRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	AdjustmentdetailsRepository adjustmentdetailsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Invoice_accountdetailsRepository invoice_accountdetailsRepository;
	
	@Autowired
	Sales_InvoiceRepository sales_InvoiceRepository;
	
	static String transtype="Paid Cash";
	
	@Transactional
	public Party_bill_payment save(Party_bill_payment pBill_payment) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="PBL";
		if(party_bill_paymentRepository.getBillPaySequenceId(prefix) != null ) {
			slno=Long.parseLong(party_bill_paymentRepository.getBillPaySequenceId(prefix));
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix="PC/"+pBill_payment.getFin_year()+"/";
		String tsno=party_bill_paymentRepository.getBillPaySequenceNo(tprefix);
				
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pBill_payment.setPbill_no(gen_tslno);
		/*End checking transaction no for unique */
		
		pBill_payment.setPbill_id(gen_sno);
		pBill_payment.setModified_type("INSERTED");
		pBill_payment.setInserted_by(userRepository.getUserDetails(pBill_payment.getUsername()).getName());
		pBill_payment.setInserted_on(ldt);
		pBill_payment.setUpdated_by("NA");
		pBill_payment.setUpdated_on(ldt);
		pBill_payment.setDeleted_by("NA");
		pBill_payment.setDeleted_on(ldt);
		
		if(Utility.isNullOrEmpty(pBill_payment.getBusiness_unit())) {
			pBill_payment.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pBill_payment.getBusiness_unit()).getBusinessunit_name());
		}else {pBill_payment.setBusiness_unitname("None");}
		
		if(Utility.isNullOrEmpty(pBill_payment.getParty())) {
			pBill_payment.setPartyname(cust_bussiness_partnerRepository.getCustomer(pBill_payment.getParty()).getCp_name());
		}else {pBill_payment.setPartyname("None");}
		
		if(Utility.isNullOrEmpty(pBill_payment.getPay_to())) {
			pBill_payment.setPay_to_lname(ledgermasterRepository.getLedgerDetails(pBill_payment.getPay_to()).getLedgername());
		}else {pBill_payment.setPay_to_lname("None");}
		
		if(Utility.isNullOrEmpty(pBill_payment.getPledgerid())) {
			pBill_payment.setPledgername(ledgermasterRepository.getLedgerDetails(pBill_payment.getPledgerid()).getLedgername());
		}else {pBill_payment.setPledgername("None");}
		
		Set<Party_bill_payment_details> pDetails=new HashSet<Party_bill_payment_details>();
		pDetails.addAll(pBill_payment.getParty_bill_payment_details());
		for(Party_bill_payment_details payDtls:pDetails) 
		{
			payDtls.setPbill_id(gen_sno);
			payDtls.setFin_year(pBill_payment.getFin_year());
			payDtls.setCompany_id(pBill_payment.getCompany_id());
			payDtls.setUsername(pBill_payment.getUsername());
			payDtls.setModified_type("INSERTED");
			payDtls.setInserted_by(userRepository.getUserDetails(pBill_payment.getUsername()).getName());
			payDtls.setInserted_on(ldt);
			payDtls.setUpdated_by("NA");
			payDtls.setUpdated_on(ldt);
			payDtls.setDeleted_by("NA");
			payDtls.setDeleted_on(ldt);
		}
		pBill_payment.setParty_bill_payment_details(pDetails);
		
		Set<Adjustmentdetails> adjustDetails=new HashSet<Adjustmentdetails>();
		
		/********************************* Static Start **********************************/
		String result="",vcode="",finalcode="",parent ="",ledgerid="",uniquevoucher="";
		
		ledgerid=ledgermasterRepository.getLedgerDetails(pBill_payment.getPay_to()).getLedgerid();
		System.out.println("Got Code: "+vcode+","+pBill_payment.getEntrydate()+","+pBill_payment.getFin_year());
		String[] d = pBill_payment.getEntrydate().split("-"),ss = pBill_payment.getFin_year().split("-");
		String mon = d[1],day = d[0],yr = ss[0].substring(2, 4),yr1 = ss[1].substring(2, 4);
		String rcode ="";
		
		int num = 0,num1 = 0;
		if(ledgerid.substring(0,5).equals("CB002"))
		{
			vcode="RC";rcode = "RC" + yr + yr1 + mon + day;
			System.err.println("Prefix Code: "+rcode);
			if(receipt_accounttransactionRepository.countRcptId(rcode,pBill_payment.getFin_year()) !=null) {
				num=Integer.parseInt(receipt_accounttransactionRepository.countRcptId(rcode,pBill_payment.getFin_year()));
			}
		}
		else if(ledgerid.substring(0,5).equals("CB001"))
		{
			vcode="RB";rcode = "RB" + yr + yr1 + mon + day;
			System.err.println("Prefix Code: "+rcode);
			if(receipt_accounttransactionRepository.countRcptId(rcode,pBill_payment.getFin_year()) !=null) {
				num=Integer.parseInt(receipt_accounttransactionRepository.countRcptId(rcode,pBill_payment.getFin_year()));
			}
		}else {System.out.println("Not Found !!!");}
		uniquevoucher = Utility.acVoucherGen(num, vcode, pBill_payment.getEntrydate(),pBill_payment.getFin_year());
		
		if(ledgerid.substring(0,5).equals("CB002"))
		{
			vcode="RC";
			if(receipt_accounttransactionRepository.getMaxVoucherId(vcode, pBill_payment.getBusiness_unit(), pBill_payment.getFin_year()) !=null) {
				num1=Integer.parseInt(receipt_accounttransactionRepository.getMaxVoucherId(vcode, pBill_payment.getBusiness_unit(), pBill_payment.getFin_year()));
			}
		}
		else if(ledgerid.substring(0,5).equals("CB001"))
		{
			vcode="RB";
			if(receipt_accounttransactionRepository.getMaxVoucherId(vcode, pBill_payment.getBusiness_unit(), pBill_payment.getFin_year()) !=null) {
				num1=Integer.parseInt(receipt_accounttransactionRepository.getMaxVoucherId(vcode, pBill_payment.getBusiness_unit(), pBill_payment.getFin_year()));
			}
		}else {System.out.println("Not Found !!!");}
		finalcode = Utility.acVoucherGen(num1,vcode,pBill_payment.getEntrydate(),pBill_payment.getFin_year());
		
		System.err.println("VOUCHER CODE: "+finalcode+" Unique: "+uniquevoucher);
		
		String sp[] = findParentSubGroupCode(ledgerid,pBill_payment.getBusiness_unit()).split("x@nk!tltdx");
		
			String subgrps="";	
			for (int i = 0; i < sp.length; i++) {
				if(sp[i].length()==5 && sp[i].trim() !="") {
					subgrps +=sp[i]+",";
				}else if(sp[i].length()==2 && sp[i].trim() !="") {
					subgrps +=sp[i]+",";
				}else {}
			}
			subgrps=subgrps.substring(0,(subgrps.length()-1));
			System.err.println("Subgrps:>> "+subgrps);
		
		System.err.println("Got Data:>>>>>>>> "+pBill_payment.getBusiness_unit()+" , "+pBill_payment.getEntrydate());
		
		int xy=receipt_accounttransactionRepository.saveReceiptAccount(pBill_payment.getBusiness_unit(), pBill_payment.getEntrydate(),
				finalcode, "Referenceno", "Referencedate", pBill_payment.getPay_to(),
				pBill_payment.getTotal_payamt(), "Drawnon","INSERTED",ldt,userRepository.getUserDetails(pBill_payment.getUsername()).getName(),
				pBill_payment.getFin_year(), pBill_payment.getUsername(),"Narration","Narration_dtls",uniquevoucher,
				subgrps,pBill_payment.getPay_to().substring(0, 2),pBill_payment.getPbill_id(),pBill_payment.getPbill_no());
		/********************************* Static End **********************************/
		String dledgerid="";
		for(Party_bill_payment_details accDtls:pDetails)
		{
			dledgerid=ledgermasterRepository.getLedgerDetails(pBill_payment.getPledgerid()).getLedgerid();
			
			//accountsInsertionPlus
			String sps[] = findParentSubGroupCode(dledgerid,pBill_payment.getBusiness_unit()).split("x@nk!tltdx");
			
				String subgrps1="";	
				for (int i = 0; i < sps.length; i++) 
				{
					if(sps[i].length()==5 && sps[i].trim() !="")
					{
						subgrps1 +=sps[i]+",";
					}
					else if(sps[i].length()==2 && sps[i].trim() !="")
					{
						subgrps1 +=sps[i]+",";
					}else {}
					
				}
				subgrps1=subgrps1.substring(0,(subgrps1.length()-1));
				System.err.println("Subgrps1:>> "+subgrps1);
			//////////////////////////////////////////////////////////////////
				
			int xz=receipt_accounttransactionRepository.callRcptAccInsertPlus(pBill_payment.getBusiness_unit(),pBill_payment.getEntrydate(),finalcode,uniquevoucher,
					dledgerid,(accDtls.getPayable_amt()*-1),"Narration",ldt,pBill_payment.getUsername(),pBill_payment.getFin_year(),subgrps1,dledgerid.substring(0, 2),"0",0.0,pBill_payment.getPbill_id(),pBill_payment.getPbill_no());	
				
			System.err.println("Update Status: "+xz);
			//////////////////////////////////////////////////////////////////
		}
		
		int k=1;
		for(Party_bill_payment_details pBill:pDetails)
		{
			System.err.println("Got Value Party: "+pBill_payment.getParty()+" Inv No: "+pBill.getInvoice_no()+" Pay Amt: "+pBill.getPayable_amt());
			//Party Outstanding Update
			int x=partyoutstanding_invoiceRepository.updatePartyOutstanding(pBill_payment.getParty(), pBill.getInvoice_no(), pBill.getPayable_amt());
			
			//Outstanding Update
			int y=outstandingledgerRepository.updateOutstandingledger(pBill_payment.getPledgerid(),pBill.getInvoice_no(), pBill.getPayable_amt());
			
			//Adjustmentdetails Insert
			Adjustmentdetails adjDtls=new Adjustmentdetails(k,pBill.getInvoice_no(),pBill_payment.getBusiness_unit(),pBill_payment.getPledgerid(),pBill_payment.getEntrydate(),
					outstandingledgerRepository.getOutstandingledgerDtls(pBill.getInvoice_no()).getVoucherno(),pBill_payment.getPbill_id(),pBill.getInvoice_date(), pBill.getPayable_amt(),
					pBill_payment.getBusiness_unit(), pBill.getInvoice_no(), uniquevoucher, pBill_payment.getModified_type(), pBill_payment.getInserted_on(), pBill_payment.getInserted_by(), pBill_payment.getInserted_on(), pBill_payment.getInserted_by(), pBill_payment.getFin_year(), pBill_payment.getUsername());
			
			adjustmentdetailsRepository.save(adjDtls);
			
			sales_InvoiceRepository.updateInvPaymentStatus(pBill.getInvoice_no(),true);
			k++;
		};
		
		//Invoice_accountdetails
		Invoice_accountdetails partyAccDtls=new Invoice_accountdetails(pBill_payment.getPbill_id(),pBill_payment.getPbill_no(),pBill_payment.getBusiness_unit(),pBill_payment.getBusiness_unitname(),pBill_payment.getParty(),pBill_payment.getPartyname(),pBill_payment.getEntrydate(),pBill_payment.getTotal_payamt(),"I",transtype,finalcode,pBill_payment.getModified_type(),pBill_payment.getInserted_on(),pBill_payment.getInserted_by(),pBill_payment.getFin_year(),pBill_payment.getUsername());
		Invoice_accountdetails accDtls=new Invoice_accountdetails(pBill_payment.getPbill_id(),pBill_payment.getPbill_no(),pBill_payment.getBusiness_unit(),pBill_payment.getBusiness_unitname(),pBill_payment.getPay_to(),pBill_payment.getPay_to_lname(),pBill_payment.getEntrydate(),(pBill_payment.getTotal_payamt()*-1),"A",transtype,finalcode,pBill_payment.getModified_type(),pBill_payment.getInserted_on(),pBill_payment.getInserted_by(),pBill_payment.getFin_year(),pBill_payment.getUsername());
		invoice_accountdetailsRepository.save(partyAccDtls);
		invoice_accountdetailsRepository.save(accDtls);
		
		return party_bill_paymentRepository.save(pBill_payment);
	}
	
	public List<Party_bill_payment> getPartyBillPayment(){
		List<Party_bill_payment> pList=new ArrayList<Party_bill_payment>();
		pList.addAll(party_bill_paymentRepository.findAll());
		
		List<Party_bill_payment> pInvoices = pList
			  .parallelStream()
			  .filter(c -> c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Party_bill_payment::getEntrydate))
			  .collect(Collectors.toList());
		
		return pInvoices;
	}
	
	public String findParentSubGroupCode(String ledgerid, String bunit) {
		String str="",x1="",x2="",x3="",x4="",x5="",x6="",x7="",x8="",x9="",x10="",x11="",parent ="";
		List<String> subcode = new ArrayList<String>();
		
		if(ledgerid.length()==10)
		{
			subcode.add(ledgerid.substring(0,5));
			if(subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 5)).isPresent()) {
				parent=subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 5)).get().getParent_subgroupcode();
			}
		}	
		else
		{
			subcode.add(ledgerid.substring(0,2));
			if(subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 2)).isPresent()) {
				parent=subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 2)).get().getParent_subgroupcode();
			}
		}
		subcode.add(parent);
		System.err.println("Parent: "+parent);
		if(parent.compareTo("") !=0)
		{
			System.err.println("Get values:>>>>>>>> Parent: "+parent+" ,Branch: "+bunit);
			if(subgroupmasterRepository.getParentSubGroup(parent).isPresent()) {
				x1 = subgroupmasterRepository.getParentSubGroup(parent).get().getParent_subgroupcode();
			}
			System.err.println("Parent x1: "+x1);
			subcode.add(x1);
		}	
		if(x1.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(x1).isPresent()) {
				x2 = subgroupmasterRepository.getParentSubGroup(x1).get().getParent_subgroupcode();
			}
			System.err.println("Parent x2: "+x2);
			subcode.add(x2);
		}
		if(x2.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(x2).isPresent()) {
				x3 = subgroupmasterRepository.getParentSubGroup(x2).get().getParent_subgroupcode();
			}
			System.err.println("Parent x3: "+x3);
			subcode.add(x3);
		}
		if(x3.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x3).isPresent()) {
				x4 = subgroupmasterRepository.getParentSubGroup(x3).get().getParent_subgroupcode();
			}
			System.err.println("Parent x4: "+x4);
			subcode.add(x4);
		}
		if(x4.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x4).isPresent()) {
				x5 = subgroupmasterRepository.getParentSubGroup(x4).get().getParent_subgroupcode();
			}
			System.err.println("Parent x5: "+x5);
			subcode.add(x5);
		}
		if(x5.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x5).isPresent()) {
				x6 = subgroupmasterRepository.getParentSubGroup(x5).get().getParent_subgroupcode();
			}
			System.err.println("Parent x6: "+x6);
			subcode.add(x6);
		}
		if(x6.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x6).isPresent()) {
				x7 = subgroupmasterRepository.getParentSubGroup(x6).get().getParent_subgroupcode();
			}
			System.err.println("Parent x7: "+x7);
			subcode.add(x7);
		}	
		if(x7.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x7).isPresent()) {
				x8 = subgroupmasterRepository.getParentSubGroup(x7).get().getParent_subgroupcode();
			}
			System.err.println("Parent x8: "+x8);
			subcode.add(x8);
		}
		if(x8.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x8).isPresent()) {
				x9 = subgroupmasterRepository.getParentSubGroup(x8).get().getParent_subgroupcode();
			}
			System.err.println("Parent x9: "+x9);
			subcode.add(x9);
		}
		if(x9.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x9).isPresent()) {
				x10 = subgroupmasterRepository.getParentSubGroup(x9).get().getParent_subgroupcode();
			}
			System.err.println("Parent x10: "+x10);
			subcode.add(x10);
		}
		if(x10.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x10).isPresent()) {
				x11 = subgroupmasterRepository.getParentSubGroup(x10).get().getParent_subgroupcode();
			}
			System.err.println("Parent x11: "+x11);
			subcode.add(x11);
		}
		
		for (int i = 0; i < subcode.size(); i++) {
			if (i == 0) {
				str += subcode.get(i);
			} else {
				str += "x@nk!tltdx" + subcode.get(i);
			}
		}
		
		return str;
	}

	public List<Partyoutstanding_invoice> getPartyOutstanding(String party,String bunit)
	{
		List<Partyoutstanding_invoice> pList=new ArrayList<Partyoutstanding_invoice>();
		pList.addAll(partyoutstanding_invoiceRepository.findAll());
		
		List<Partyoutstanding_invoice> pInvoices = pList
			  .parallelStream()
			  .filter(c -> c.getParty().equals(party) && c.getBusiness_unit().equals(bunit)
					  && c.getDuesamount() > 0 && c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Partyoutstanding_invoice::getInvoice_id))
			  .collect(Collectors.toList());
		
		return pInvoices;
	}
	
	public List<Partyoutstanding_invoice> getPartyBusinessUnit(String party)
	{
		List<Partyoutstanding_invoice> pList=new ArrayList<Partyoutstanding_invoice>();
		pList.addAll(partyoutstanding_invoiceRepository.findAll());
		
		List<Partyoutstanding_invoice> pBunit = pList
			  .parallelStream()
			  .filter(c -> c.getParty().equals(party) && c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Partyoutstanding_invoice::getInvoice_id))
			  .collect(Collectors.toList());
		
		return pBunit;
	}

	
}
