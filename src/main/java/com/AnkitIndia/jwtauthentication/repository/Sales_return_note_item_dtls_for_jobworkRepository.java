package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls_for_jobwork;

public interface Sales_return_note_item_dtls_for_jobworkRepository extends JpaRepository<Sales_return_note_item_dtls_for_jobwork, Long>{

	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_item_dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.salesreturnnoteid = :salesreturnnoteid")
    int updateSales_return_note_item_dtls_for_jobwork(@Param("salesreturnnoteid") String salesreturnnoteid,@Param("mstatus") String mstatus);

	@Query( value="select * from sales_return_note_item_dtls_for_jobwork s where s.modified_type = 'INSERTED' and s.salesreturnnoteid =:salesreturnnoteid",nativeQuery=true)
	List<Map<String,Object>> retriveSalesReturnNoteJobwork(@Param("salesreturnnoteid") String salesreturnnoteid);
}
