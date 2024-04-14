package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Bp_Details;

public interface Pur_Bill_Bp_DetailsRepository extends JpaRepository<Pur_Bill_Bp_Details, Long>{
	
	@Query( "select w from Pur_Bill_Bp_Details w where w.pur_bill_id = :pur_bill_id and  w.modified_type = 'INSERTED'" )
	Pur_Bill_Bp_Details gePurBillBPRetrive(@Param("pur_bill_id") String pur_bill_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_Bp_Details w SET w.modified_type ='UPDATED' WHERE w.pur_bill_id = :pur_bill_id")
    int updatePur_Bill_Bp_Details(@Param("pur_bill_id") String pur_bill_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_Bp_Details w SET w.modified_type ='DELETED' WHERE w.pur_bill_id = :pur_bill_id")
    int pur_Bill_Bp_DetailsUpdate(@Param("pur_bill_id") String pur_bill_id);
	
}
