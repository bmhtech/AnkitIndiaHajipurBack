/*package com.AnkitIndia.jwtauthentication.security.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Item_master_inv_data1;
import com.AnkitIndia.jwtauthentication.model.Item_master_stat_info;
import com.AnkitIndia.jwtauthentication.model.Testusers;
import com.AnkitIndia.jwtauthentication.model.Testuserscontact;
import com.AnkitIndia.jwtauthentication.model.Testuserslog;
import com.AnkitIndia.jwtauthentication.repository.TestusersRepository;

@Service
public class TestusersService_Imp implements TestusersService {
	
	@Autowired
	TestusersRepository testusersRepository;
 
	@Transactional
	public Testusers Usersave(Testusers testusers)
	{
		List<Testuserslog> itemSet = new ArrayList <Testuserslog>();
		itemSet.addAll(testusers.getUsersLogs());
		for(Testuserslog itemDts : itemSet)
		{
			itemDts.setUserId(testusers.getId());
		}
		testusers.setUsersLogs(itemSet);
		
		Testuserscontact testuserscontact = new Testuserscontact();
		testusers.setTestuserscontact(testuserscontact);
		
		return  testusersRepository.save(testusers);
	}
}*/
