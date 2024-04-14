package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;

public interface Cust_bussiness_partner_addressRepository extends JpaRepository<Cust_bussiness_partner_address, Long>{


	@Query( "select c from Cust_bussiness_partner_address c where c.cp_Id = :code and c.modified_type = 'INSERTED'" )
	Cust_bussiness_partner_address custAddRetriveList(@Param("code") String code);
	
	@Query( "select c from Cust_bussiness_partner_address c where c.cp_Id = :custid and c.modified_type = 'INSERTED'" )
	Cust_bussiness_partner_address getCustomerAddress(@Param("custid") String custid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_address w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_addressupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
	
	@Query(value="select * from cust_bussiness_partner_address where modified_type = 'INSERTED' and cp_id =:code",nativeQuery=true)
	Map<String, Object> getCustomerAddressDetails(@Param("code") String code);
}
