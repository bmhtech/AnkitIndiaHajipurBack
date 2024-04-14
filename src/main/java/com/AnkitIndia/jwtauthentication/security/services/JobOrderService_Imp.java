package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.repository.JobOrderRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class JobOrderService_Imp implements JobOrderService{
	
	@Autowired
	JobOrderRepository jobOrderRepository;
	
	public SequenceIdDTO getOSequenceId(String fin_year) {
		
		int slno=0;
		String sno=jobOrderRepository.countjoborder();
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String prefix="JWO-"+final_fyear+"-";
		String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	

}
