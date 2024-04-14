package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Docs;

public interface Pur_Bill_DocsRepository extends JpaRepository<Pur_Bill_Docs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_Docs w SET w.modified_type ='UPDATED' WHERE w.pur_bill_id = :pur_bill_id")
    int updatePur_Bill_Docs(@Param("pur_bill_id") String pur_bill_id);
	
	@Query( "select p from Pur_Bill_Docs p where p.pur_bill_id = :pbid and p.modified_type ='INSERTED'" )
	List<Pur_Bill_Docs> purBillDocRetriveList(@Param("pbid") String pbid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_Docs w SET w.modified_type ='DELETED' WHERE w.pur_bill_id = :pur_bill_id")
    int pur_Bill_DocsUpdate(@Param("pur_bill_id") String pur_bill_id);

}
