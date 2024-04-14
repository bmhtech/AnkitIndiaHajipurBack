package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.security.services.Pur_Bill_app_chgs;
import com.AnkitIndia.jwtauthentication.transResponseDTO.PurchasebillviewchargeDTO;

public interface Pur_Bill_app_chgsRepository extends JpaRepository<Pur_Bill_app_chgs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_app_chgs w SET w.modified_type ='UPDATED' WHERE w.pur_bill_id = :pur_bill_id")
    int updatePur_Bill_app_chgsls(@Param("pur_bill_id") String pur_bill_id);
	
	@Query( "select p from Pur_Bill_app_chgs p where p.pur_bill_id = :pbid and p.modified_type ='INSERTED'" )
	List<Pur_Bill_app_chgs> purBillAppChargesRetriveList(@Param("pbid") String pbid);
	
	
	@Query( value="SELECT ledgername AS charges_name,SUM(amount) AS amount,add_less FROM  purchasebillcharges WHERE pur_bill_id=:pbid GROUP BY ledgername,add_less",nativeQuery=true )
	List<Object[]> purBillAppChargesview(@Param("pbid") String pbid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_app_chgs w SET w.modified_type ='DELETED' WHERE w.pur_bill_id = :pur_bill_id")
    int pur_Bill_app_chgsUpdate(@Param("pur_bill_id") String pur_bill_id);
	
}
