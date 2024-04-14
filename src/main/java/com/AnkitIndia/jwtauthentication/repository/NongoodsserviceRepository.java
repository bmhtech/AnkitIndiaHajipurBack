package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Nongoodsservice;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_bank_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_party_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_terms_con;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_time_service;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_shipment_dtls;

public interface NongoodsserviceRepository extends JpaRepository<Nongoodsservice, Long>{

	@Query("select COUNT(id) from Nongoodsservice where fin_year=:finyear and service_type=:check")
	String countId(@Param("finyear") String finyear,@Param("check") boolean check);
	
	@Query("select COUNT(id) from Nongoodsservice")
	String genId();
	
	@Query(value="select id,nongoodsserviceid,b_unitname,ordertype,party_name,billing_from,billing_to,billdate,b_unit,service_type from nongoodsservice where fin_year=:finyear and modified_type='INSERTED' ORDER BY id DESC",nativeQuery=true)
	List<Map<String,Object>> getNonGoodsServicelist(@Param("finyear") String finyear);
	
	@Query( "select n from Nongoodsservice_terms_con n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	Nongoodsservice_terms_con getTermsCon(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_party_dtls n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	List<Nongoodsservice_party_dtls> getPartyDetails(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_bank_dtls n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	Nongoodsservice_bank_dtls getBankDetails(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_summary n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	Nongoodsservice_summary getSummary(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_summary_dyn n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	List<Nongoodsservice_summary_dyn> getSummaryDetails(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_time_service n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid order by n.slno")
	List<Nongoodsservice_time_service> getTimeDetails(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_docs n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	List<Nongoodsservice_docs> getDocuments(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_exit_clause n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	Nongoodsservice_exit_clause getExitclause(@Param("nongoodsid") String nongoodsid);
	
	@Query( "select n from Nongoodsservice_exit_clause_dyn n where n.modified_type = 'INSERTED' AND n.nongoodsserviceid = :nongoodsid")
	List<Nongoodsservice_exit_clause_dyn> getExitclauseDetails(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_terms_con n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateTermsConditiont(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_party_dtls n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updatePartyDetails(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_bank_dtls n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateBankDetails(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_summary n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateSummary(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_summary_dyn n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateSummaryDyn(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_time_service n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateTimeService(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_docs n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateDocuments(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_exit_clause n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateExitclause(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_exit_clause_dyn n SET n.modified_type ='UPDATED' WHERE n.nongoodsserviceid = :nongoodsid")
    int updateExitclauseDyn(@Param("nongoodsid") String nongoodsid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_terms_con n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertTermsCon(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_party_dtls n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertParty(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_bank_dtls n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertBankDetails(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_summary n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertSummary(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_summary_dyn n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertSummaryDetails(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_time_service n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertTimeService(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_docs n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertDocuments(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_exit_clause n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertExitClause(@Param("nongoodsid") String nongoodsid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodsservice_exit_clause_dyn n SET n.modified_type ='DELETED' WHERE n.nongoodsserviceid =:nongoodsid and n.modified_type='INSERTED'")
    int revertExitClauseDetails(@Param("nongoodsid") String nongoodsid);
	
	@Query(value="select n.nongoodsserviceid as nongoodsserviceid,n.serviceno as serviceno from nongoodsservice n where modified_type='INSERTED' and n.ordertype=:ordertype and n.b_unit=:bunit and n.party=:party and n.billing_to>=:srndate",nativeQuery=true)
	List<Map<String,Object>> getNonServiceOrderAllList(@Param("ordertype") String ordertype,@Param("bunit") String bunit,@Param("party") String party,@Param("srndate") String srndate);
	
}
