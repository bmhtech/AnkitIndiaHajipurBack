package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.Charge_Masterdtls;


public interface Charge_MasterRepository extends JpaRepository<Charge_Master, Long>{
	

	@Query( "select c from Charge_Master c where c.modified_type != 'DELETED' ")
	List<Charge_Master> getChargeMasterList();
	
	@Query("select c from Charge_Masterdtls c where c.charge_id = :code and c.modified_type = 'INSERTED' ")
	List<Charge_Master> getChargeMasterdtlsList(@Param("code") String code);
	
	@Query("select c from Charge_Masterdtls c where c.charge_id = :code and c.modified_type = 'INSERTED' ")
	List<Charge_Masterdtls> getChargeMasterdtlsListnew(@Param("code") String code);
	
	
	@Query("select c from Charge_Masterdtls c where c.charge_id = :code and c.charge_name =:charge_name and  c.modified_type = 'INSERTED' ")
	Charge_Masterdtls getchargeaccount(@Param("code") String code,@Param("charge_name") String charge_name);
	
	@Query( "select c from Charge_Masterdtls c where c.charge_id = :c_id and c.modified_type = 'INSERTED'")
	List<Charge_Master> chargeRetriveList(@Param("c_id") String c_id);
	
	@Query("SELECT MAX(SUBSTRING(charge_id , 4 , length(charge_id))) FROM Charge_Master where charge_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	//@Query(value = "{call checkChargeMasterUsage(:code)}", nativeQuery = true)
	//String checkChargeMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkChargeMasterPurchaseUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Sales_Order pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkChargeMasterSalesUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Bill pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkChargeMasterPurBillUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Sales_Invoice pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkChargeMasterSalesInvUsage(@Param("code") String code);
	
	
	
	
}
