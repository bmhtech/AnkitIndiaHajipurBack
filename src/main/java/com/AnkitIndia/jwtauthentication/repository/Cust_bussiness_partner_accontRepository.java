package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_accont;

public interface Cust_bussiness_partner_accontRepository extends JpaRepository<Cust_bussiness_partner_accont, Long>{

	@Query( "select c from Cust_bussiness_partner_accont c where c.cp_Id = :code and c.company_id=:company and c.modified_type = 'INSERTED'" )
	Cust_bussiness_partner_accont custAccountRetriveList(@Param("code") String code,@Param("company") String company);
	
	@Query( "select c from Cust_bussiness_partner_accont c where c.cp_Id = :code and c.modified_type = 'INSERTED'" )
	Cust_bussiness_partner_accont custAccountRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_accont c SET c.modified_type =:mstatus WHERE c.cp_Id =:cp_Id")
    int updateCust_bussiness_partner_accont(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
}
