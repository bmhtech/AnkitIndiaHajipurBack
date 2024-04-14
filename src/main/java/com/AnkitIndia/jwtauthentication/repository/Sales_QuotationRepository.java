package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;

public interface Sales_QuotationRepository extends JpaRepository< Sales_Quotation, Long> {
	
	@Query("select COUNT(id) from Sales_Quotation")
	String countId();
	
	@Query("select COUNT(id) from Sales_Quotation where quotation_date =:orderdate and quo_type =:type ")
	String countQuotationOrder(@Param("orderdate") String orderdate,@Param("type") String type);
	
	@Query( "select s from Sales_Quotation s where s.modified_type = 'INSERTED' ")
	List<Sales_Quotation> salesQuotationList();
	
	@Query( "select s from Sales_Quotation s where s.modified_type = 'INSERTED' ORDER BY s.quotation_id DESC ")
	List<Sales_Quotation> findSalesQuotations();
	
	@Query( "select s from Sales_Quotation s where s.modified_type = 'INSERTED' and s.q_status =:qstatus and s.sale_orderused=0 AND s.quotationcheckpoint='YES' and s.terminate=0")
	List<Sales_Quotation> salesQuotationThruStatus(@Param("qstatus") String qstatus);
	
	@Query( "select s from Sales_Quotation s where s.modified_type = 'INSERTED' and s.quotation_id =:quot_id ")
	Sales_Quotation getSalesQuotDetails(@Param("quot_id") String quot_id);
	
	@Query(value="SELECT * FROM sales_quotation s WHERE s.modified_type = 'INSERTED' AND s.quotation_date =:cur_date", nativeQuery = true)
	List<Map<String,Object>> getSalesQuotationsList(@Param("cur_date") String cur_date);
	
	@Query(value="SELECT * FROM sales_quotation s WHERE s.modified_type = 'INSERTED' AND s.quotation_date>=:fromdate AND s.quotation_date<=:todate", nativeQuery = true)
	List<Map<String,Object>> searchSaleQuotation(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation s SET s.sale_orderused =1 WHERE  s.quotation_id =:referenceid")
    int updatequationstationorder(@Param("referenceid") String referenceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation s SET s.sale_orderused =0 WHERE  s.quotation_id =:referenceid")
    int revertupdatequationstationorder(@Param("referenceid") String referenceid);
	
	@Query(value = "{call checkSalesQuotationUsage(:quot_id)}", nativeQuery = true)
	String checkSalesQuotationUsage(@Param("quot_id") String quot_id);
	
	@Query( "select w from Sales_Quotation w where w.id =:id")
	Sales_Quotation getQuotationDetails(@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation s SET s.terminate=1 , s.updated_by=:username WHERE  s.id =:id")
    int SalesQuotationTerminate(@Param("id") long id,@Param("username") String username);
}
