package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Summary_dyn;

public interface Sales_Quotation_Summary_dynRepository extends JpaRepository<Sales_Quotation_Summary_dyn, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation_Summary_dyn w SET w.modified_type ='UPDATED' WHERE w.quotation_id = :quotation_id AND w.modified_type='INSERTED'")
    int sales_Quotation_Summary_dynupdate(@Param("quotation_id") String quotation_id);
	
	@Query( "select s from Sales_Quotation_Summary_dyn s where s.modified_type = 'INSERTED' and s.quotation_id =:quot_id ORDER BY s.id")
	List<Sales_Quotation_Summary_dyn> getSalesQuotSummaryDtls(@Param("quot_id") String quot_id);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation_Summary_dyn w SET w.modified_type ='DELETED' WHERE w.quotation_id = :quotation_id AND w.modified_type='INSERTED'")
    int sales_Quotation_Summary_dynDelete(@Param("quotation_id") String quotation_id);
}
