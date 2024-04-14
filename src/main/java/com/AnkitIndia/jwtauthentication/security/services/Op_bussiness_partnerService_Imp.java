package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_bill_addr;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_broker;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_delv_from;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Info;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partner_accontRepository;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partner_bill_addrRepository;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partner_delv_fromRepository;
import com.AnkitIndia.jwtauthentication.repository.Op_bussiness_partner_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Trans_InfoDTO;

@Service
public class Op_bussiness_partnerService_Imp implements Op_bussiness_partnerService{
	
	
	@Autowired
	Op_bussiness_partnerRepository op_bussiness_partnerRepository;
	
	@Autowired
	Op_bussiness_partner_delv_fromRepository op_bussiness_partner_delv_fromRepository;
	
	@Autowired
	Op_bussiness_partner_brokerRepository op_bussiness_partner_brokerRepository;
	
	@Transactional
	public Op_bussiness_partner save(Op_bussiness_partner op_bussiness_partner) {
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(op_bussiness_partnerRepository.countId() != null ) {
			slno=Long.parseLong(op_bussiness_partnerRepository.countId());
		}
		String prefix="OBP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		op_bussiness_partner.setBp_Id(gen_sno);
		op_bussiness_partner.setCompany_id("xxxx");
		op_bussiness_partner.setFin_year("2019-2020");
		op_bussiness_partner.setModified_type("INSERTED");
		op_bussiness_partner.setInserted_by("xxxx");
		op_bussiness_partner.setInserted_on(ldt);
		op_bussiness_partner.setUpdated_by("NA");
		op_bussiness_partner.setUpdated_on(ldt);
		op_bussiness_partner.setDeleted_by("NA");
		op_bussiness_partner.setDeleted_on(ldt);
		
		
		Set<Op_bussiness_partner_address> bpAddressSet=new HashSet<Op_bussiness_partner_address>();
		bpAddressSet.add(op_bussiness_partner.getOp_bussiness_partner_address());
		for(Op_bussiness_partner_address bpadd:bpAddressSet) 
		{
			bpadd.setBp_Id(gen_sno);
			bpadd.setCompany_id("xxxx");
			bpadd.setFin_year("2019-2020");
			bpadd.setModified_type("INSERTED");
			bpadd.setInserted_by("xxxx");
			bpadd.setInserted_on(ldt);
			bpadd.setUpdated_by("NA");
			bpadd.setUpdated_on(ldt);
			bpadd.setDeleted_by("NA");
			bpadd.setDeleted_on(ldt);
		}
		if(!bpAddressSet.isEmpty())
		{
			op_bussiness_partner.setOp_bussiness_partner_address(bpAddressSet.iterator().next());
		}
		
		
		Set<Op_bussiness_partner_bill_addr> bpBillAddressSet=new HashSet<Op_bussiness_partner_bill_addr>();
		bpBillAddressSet.add(op_bussiness_partner.getOp_bussiness_partner_bill_addr());
		for(Op_bussiness_partner_bill_addr bpbilladd:bpBillAddressSet) 
		{
			bpbilladd.setBp_Id(gen_sno);
			bpbilladd.setCompany_id("xxxx");
			bpbilladd.setFin_year("2019-2020");
			bpbilladd.setModified_type("INSERTED");
			bpbilladd.setInserted_by("xxxx");
			bpbilladd.setInserted_on(ldt);
			bpbilladd.setUpdated_by("NA");
			bpbilladd.setUpdated_on(ldt);
			bpbilladd.setDeleted_by("NA");
			bpbilladd.setDeleted_on(ldt);
		}
		if(!bpBillAddressSet.isEmpty())
		{
			op_bussiness_partner.setOp_bussiness_partner_bill_addr(bpBillAddressSet.iterator().next());
		}
		
		
		Set<Op_bussiness_partner_delv_from> bpDelFromSet=new HashSet<Op_bussiness_partner_delv_from>();
		bpDelFromSet.addAll(op_bussiness_partner.getOp_bussiness_partner_delv_from());
		for(Op_bussiness_partner_delv_from bpdelvfrom:bpDelFromSet) 
		{
			bpdelvfrom.setBp_Id(gen_sno);
			bpdelvfrom.setCompany_id("xxxx");
			bpdelvfrom.setFin_year("2019-2020");
			bpdelvfrom.setModified_type("INSERTED");
			bpdelvfrom.setInserted_by("xxxx");
			bpdelvfrom.setInserted_on(ldt);
			bpdelvfrom.setUpdated_by("NA");
			bpdelvfrom.setUpdated_on(ldt);
			bpdelvfrom.setDeleted_by("NA");
			bpdelvfrom.setDeleted_on(ldt);
		}
		
		op_bussiness_partner.setOp_bussiness_partner_delv_from(bpDelFromSet);

	
		
		Set<Op_bussiness_partner_accont> bpAccountSet=new HashSet<Op_bussiness_partner_accont>();
		bpAccountSet.add(op_bussiness_partner.getOp_bussiness_partner_accont());
		for(Op_bussiness_partner_accont bpAccount:bpAccountSet) 
		{
			bpAccount.setBp_Id(gen_sno);
			bpAccount.setCompany_id("xxxx");
			bpAccount.setFin_year("2019-2020");
			bpAccount.setModified_type("INSERTED");
			bpAccount.setInserted_by("xxxx");
			bpAccount.setInserted_on(ldt);
			bpAccount.setUpdated_by("NA");
			bpAccount.setUpdated_on(ldt);
			bpAccount.setDeleted_by("NA");
			bpAccount.setDeleted_on(ldt);
		}
		if(!bpAccountSet.isEmpty())
		{
			op_bussiness_partner.setOp_bussiness_partner_accont(bpAccountSet.iterator().next());
		}
		
		
		Set<Op_bussiness_partner_statutory> bpStatSet=new HashSet<Op_bussiness_partner_statutory>();
		bpStatSet.add(op_bussiness_partner.getOp_bussiness_partner_statutory());
		for(Op_bussiness_partner_statutory bpStat:bpStatSet) 
		{
			bpStat.setBp_Id(gen_sno);
			bpStat.setCompany_id("xxxx");
			bpStat.setFin_year("2019-2020");
			bpStat.setModified_type("INSERTED");
			bpStat.setInserted_by("xxxx");
			bpStat.setInserted_on(ldt);
			bpStat.setUpdated_by("NA");
			bpStat.setUpdated_on(ldt);
			bpStat.setDeleted_by("NA");
			bpStat.setDeleted_on(ldt);
		}
		if(!bpStatSet.isEmpty())
		{
			op_bussiness_partner.setOp_bussiness_partner_statutory(bpStatSet.iterator().next());
		}
		
		Set<Op_bussiness_partner_broker> bpBrokerSet=new HashSet<Op_bussiness_partner_broker>();
		bpBrokerSet.addAll(op_bussiness_partner.getOp_bussiness_partner_broker());
		for(Op_bussiness_partner_broker bpBroker:bpBrokerSet) 
		{
			bpBroker.setBp_Id(gen_sno);
			bpBroker.setCompany_id("xxxx");
			bpBroker.setFin_year("2019-2020");
			bpBroker.setModified_type("INSERTED");
			bpBroker.setInserted_by("xxxx");
			bpBroker.setInserted_on(ldt);
			bpBroker.setUpdated_by("NA");
			bpBroker.setUpdated_on(ldt);
			bpBroker.setDeleted_by("NA");
			bpBroker.setDeleted_on(ldt);
		}
		
		op_bussiness_partner.setOp_bussiness_partner_broker(bpBrokerSet);
		
		
			return op_bussiness_partnerRepository.save(op_bussiness_partner);
	}
	
	
	public List<Op_bussiness_partner> findAll(){
		return op_bussiness_partnerRepository.findAll();
	}
	
	public Op_bussiness_partner findOne(long id)
	{
		Optional<Op_bussiness_partner> op=this.op_bussiness_partnerRepository.findById(id);
		return op.get();
	}
	
	public List<Op_bussiness_partnerDTO> getOtherPartnerMNCList(){
		List<Op_bussiness_partner> modelList=op_bussiness_partnerRepository.otherPartnerMsNameList(true);
		
		Type listType=new TypeToken<List<Op_bussiness_partnerDTO>>() {}.getType();
		List<Op_bussiness_partnerDTO> itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	@Autowired
	Op_bussiness_partner_addressRepository op_bussiness_partner_addressRepository;
	
	 public Op_bussiness_partner_addressDTO oBPAddressRetriveList(String code)
	 {
		 Op_bussiness_partner_address modelList=op_bussiness_partner_addressRepository.oBPAddressRetriveList(code);
		 Type listType = new TypeToken<Op_bussiness_partner_addressDTO>() {}.getType();

		 Op_bussiness_partner_addressDTO obpAddress= new ModelMapper().map(modelList,listType);
			
		return obpAddress;
	 }
	 
	 @Autowired
	 Op_bussiness_partner_bill_addrRepository op_bussiness_partner_bill_addrRepository;
	 
	 public Op_bussiness_partner_bill_addrDTO oBPBillAddressRetriveList(String code)
	 {
		 Op_bussiness_partner_bill_addr modelList=op_bussiness_partner_bill_addrRepository.oBPBillAddressRetriveList(code);
		 Type listType = new TypeToken<Op_bussiness_partner_bill_addrDTO>() {}.getType();

		 Op_bussiness_partner_bill_addrDTO obpAddressdtls= new ModelMapper().map(modelList,listType);
			
		return obpAddressdtls;
	 }
	 
	 public List<Op_bussiness_partner_delv_fromDTO> oBPDelvFromRetriveList(String cp_id)
		{
			List<Op_bussiness_partner_delv_from> modelList=op_bussiness_partner_delv_fromRepository.oBPDelvFromRetriveList(cp_id);
			
			Type listType=new TypeToken<List<Op_bussiness_partner_delv_fromDTO>>() {}.getType();
			
			List<Op_bussiness_partner_delv_fromDTO> obpDlvFrm=new ModelMapper().map(modelList,listType);
			
			return obpDlvFrm;
		}
	 
	 
	 @Autowired
	 Op_bussiness_partner_accontRepository op_bussiness_partner_accontRepository;
	 
	 public Op_bussiness_partner_accontDTO oBPAccountRetriveList(String code)
	 {
		 Op_bussiness_partner_accont modelList=op_bussiness_partner_accontRepository.oBPAccountRetriveList(code);
		 Type listType = new TypeToken<Op_bussiness_partner_accontDTO>() {}.getType();

		 Op_bussiness_partner_accontDTO obpAccount= new ModelMapper().map(modelList,listType);
			
		return obpAccount;
	 }
	 
	 @Autowired
	 Op_bussiness_partner_statutoryRepository op_bussiness_partner_statutoryRepository;
	 
	 public Op_bussiness_partner_statutoryDTO oBPStatutoryRetriveList(String code)
	 {
		 Op_bussiness_partner_statutory modelList=op_bussiness_partner_statutoryRepository.oBPStatutoryRetriveList(code);
		 Type listType = new TypeToken<Op_bussiness_partner_statutoryDTO>() {}.getType();

		 Op_bussiness_partner_statutoryDTO obpStatutory= new ModelMapper().map(modelList,listType);
			
		return obpStatutory;
	 }
	 
	 public List<Op_bussiness_partner_brokerDTO> oBPBrokerRetriveList(String cp_id)
		{
			List<Op_bussiness_partner_broker> modelList=op_bussiness_partner_brokerRepository.oBPBrokerRetriveList(cp_id);
			
			Type listType=new TypeToken<List<Op_bussiness_partner_brokerDTO>>() {}.getType();
			
			List<Op_bussiness_partner_brokerDTO> obpBroker=new ModelMapper().map(modelList,listType);
			
			return obpBroker;
		}
	
}
