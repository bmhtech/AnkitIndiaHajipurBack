package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Prod_summary_dtls;


public interface ProdsummarydtlsRepository extends JpaRepository<Prod_summary_dtls, Long>{
	
	@Query(value="select * from prod_summary_dtls where prod_sum_id =:prod_sum_id and modified_type='INSERTED' ORDER BY sl_no ASC",nativeQuery=true)
	List<Map<String,Object>> getProdSumDtls(@Param("prod_sum_id") String prod_sum_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Prod_summary_dtls d SET d.modified_type ='UPDATED' WHERE d.prod_sum_id =:prod_sum_id  and d.modified_type='INSERTED'")
    int retriveProdSumDtls(@Param("prod_sum_id") String prod_sum_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Prod_summary_dtls d SET d.modified_type ='DELETED' WHERE d.prod_sum_id =:prod_sum_id  and d.modified_type='INSERTED'")
    int deleteProdSumDtls(@Param("prod_sum_id") String prod_sum_id);

	@Query("select d from Prod_summary_dtls d where d.prod_sum_id =:prod_sum_id and d.modified_type='INSERTED'")
	List<Prod_summary_dtls> retriveProdSumDtlslist(@Param("prod_sum_id") String prod_sum_id);
	
}