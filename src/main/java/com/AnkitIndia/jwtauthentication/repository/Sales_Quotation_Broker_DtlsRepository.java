package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Broker_Dtls;

public interface Sales_Quotation_Broker_DtlsRepository extends JpaRepository<Sales_Quotation_Broker_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation_Broker_Dtls w SET w.modified_type ='UPDATED' WHERE w.quotation_id = :quotation_id AND w.modified_type='INSERTED'")
    int sales_Quotation_Broker_Dtlsupdate(@Param("quotation_id") String quotation_id);

	@Query( "select s from Sales_Quotation_Broker_Dtls s where s.modified_type = 'INSERTED' and s.quotation_id =:quot_id ORDER BY s.slno")
	List<Sales_Quotation_Broker_Dtls> getSalesQuotBrokerDtls(@Param("quot_id") String quot_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation_Broker_Dtls w SET w.modified_type ='DELETED' WHERE w.quotation_id = :quotation_id AND w.modified_type='INSERTED'")
    int sales_Quotation_Broker_DtlsDelete(@Param("quotation_id") String quotation_id);
}
