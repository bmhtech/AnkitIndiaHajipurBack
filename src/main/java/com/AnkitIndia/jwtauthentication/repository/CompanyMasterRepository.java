package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Company_master;

public interface CompanyMasterRepository extends JpaRepository<Company_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(company_id , 3 , length(company_id))) FROM Company_master where company_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select c from Company_master c where c.modified_type = 'INSERTED' and c.company_id =:company")
	Company_master getCompanyDetails(@Param("company") String company);
	
	@Query( "select c from Company_licence_details c where c.company_id = :cp_id and c.modified_type ='INSERTED'")
	List<Company_master> compLiceRetriveList(@Param("cp_id") String cp_id);
	
	@Query("select max(substring(company_code , 8, length(company_code))) from Company_master where company_code like %:code% and company_id =:company ")
	String getCompanySequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query(value="SELECT company_name FROM `company_master` LIMIT 1",nativeQuery = true)
	String getCompanyName();
	
}
