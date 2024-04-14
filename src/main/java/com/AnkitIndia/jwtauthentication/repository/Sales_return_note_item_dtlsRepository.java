package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls;

public interface Sales_return_note_item_dtlsRepository extends JpaRepository<Sales_return_note_item_dtls, Long>{
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_item_dtls w SET w.modified_type =:mstatus WHERE w.salesreturnnoteid = :salesreturnnoteid")
    int updateSales_return_note_item_dtls(@Param("salesreturnnoteid") String salesreturnnoteid,@Param("mstatus") String mstatus);

	
	@Query( "select c from Sales_return_note_item_dtls c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid")
	List<Sales_return_note_item_dtls> getSalesReturnNoteID(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	
	@Query(value="select * from sales_return_note_item_dtls_for_jobwork c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid",nativeQuery=true)
	List<Map<String,Object>> getSalesReturnNoteIDjobwork(@Param("salesreturnnoteid") String salesreturnnoteid);
}
