package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details_QcDetails;

public interface Pur_Quality_CheckRepository extends JpaRepository<Pur_Quality_Check, Long>{
	
	@Query("select COUNT(id) from Pur_Quality_Check")
	String countId();
	
	//@Query(value="select id,quality_check_id,qcno,qc_date,supplier,item_type,vehicle_name,vehicle_id,referenceid from pur_quality_check where fin_year=:finyear and modified_type='INSERTED' ORDER BY id DESC",nativeQuery=true)
	@Query(value="SELECT q.id,q.quality_check_id,q.qcno,q.qc_date,q.supplier,q.item_type,q.vehicle_name,q.vehicle_id,q.referenceid, (SELECT u.unadviceno FROM wm_unload_advice u  WHERE u.modified_type='INSERTED' AND u.unadviceid=q.referenceid) AS unadviceno FROM pur_quality_check q WHERE q.fin_year=:finyear AND q.modified_type='INSERTED' ORDER BY q.id DESC",nativeQuery=true)
	List<Map<String,Object>> getQClist(@Param("finyear") String finyear);
	
	@Query( "select P from Pur_Quality_Check_Details P where P.quality_check_id =:code ")
	List<Pur_Quality_Check> purQChkRetriveList(@Param("code") String code);
	
	@Query( "select q from Pur_Quality_Check_Details q where q.modified_type = 'INSERTED' AND q.quality_check_id = :quality_check_id ORDER BY q.sl_no")
	List<Pur_Quality_Check_Details> retriveQualityCheckDetails(@Param("quality_check_id") String quality_check_id);
	
	@Query( "select q from Pur_Quality_Check_Details_QcDetails q where q.modified_type = 'INSERTED' AND q.qc_code = :qcno AND q.quality_check_id = :qcid ORDER BY q.sl_no ASC")
	List<Pur_Quality_Check_Details_QcDetails> retriveQualityCheckDetailsQcDetails(@Param("qcno") String qcno,@Param("qcid") String qcid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Quality_Check_Details n SET n.modified_type ='UPDATED' WHERE n.quality_check_id = :quality_check_id and n.modified_type='INSERTED'")
    int updatePQCDetails(@Param("quality_check_id") String quality_check_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Quality_Check_Details_QcDetails n SET n.modified_type ='UPDATED' WHERE n.quality_check_id = :quality_check_id AND n.qc_code = :qc_code and n.modified_type='INSERTED'")
    int updatePQCQualityDetails(@Param("qc_code") String qc_code,@Param("quality_check_id") String quality_check_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Quality_Check_Details_QcDetails n SET n.modified_type ='UPDATED' WHERE n.item_code = :item_code AND n.packing = :packing AND n.quality_check_id = :quality_check_id and n.modified_type='INSERTED'")
    int updatePQCQualityDetailsupdate(@Param("item_code") String item_code,@Param("packing") String packing,@Param("quality_check_id") String quality_check_id);

	@Query(value="SELECT q.id,q.quality_check_id,q.qcno,q.qc_date,q.supplier,q.item_type,q.vehicle_name,q.vehicle_id,q.referenceid,(SELECT u.unadviceno FROM wm_unload_advice u  WHERE u.modified_type='INSERTED' AND u.unadviceid=q.referenceid) AS unadviceno FROM pur_quality_check q WHERE q.fin_year=:finyear AND q.vehicle_id=:vehicle AND q.modified_type='INSERTED' ORDER BY q.id DESC",nativeQuery=true)
	List<Map<String,Object>> searchQCwtVehicle(@Param("vehicle") String vehicle, @Param("finyear") String finyear);
	
	@Query(value="SELECT q.id,q.quality_check_id,q.qcno,q.qc_date,q.supplier,q.item_type,q.vehicle_name,q.vehicle_id,q.referenceid,(SELECT u.unadviceno FROM wm_unload_advice u  WHERE u.modified_type='INSERTED' AND u.unadviceid=q.referenceid) AS unadviceno FROM pur_quality_check q WHERE q.fin_year=:finyear AND q.qc_date BETWEEN :fromdate AND :todate AND q.modified_type='INSERTED' ORDER BY q.id DESC",nativeQuery=true)
	List<Map<String,Object>> searchQCwtDate(@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("finyear") String finyear);
	
	@Query(value="SELECT q.id,q.quality_check_id,q.qcno,q.qc_date,q.supplier,q.item_type,q.vehicle_name,q.vehicle_id,q.referenceid,(SELECT u.unadviceno FROM wm_unload_advice u  WHERE u.modified_type='INSERTED' AND u.unadviceid=q.referenceid) AS unadviceno FROM pur_quality_check q WHERE q.fin_year=:finyear AND q.vehicle_id=:vehicle AND q.qc_date BETWEEN :fromdate AND :todate AND q.modified_type='INSERTED' ORDER BY q.id DESC",nativeQuery=true)
	List<Map<String,Object>> searchQC(@Param("fromdate") String fromdate,@Param("todate") String todate,
			@Param("vehicle") String vehicle, @Param("finyear") String finyear);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Quality_Check_Details n SET n.modified_type ='DELETED' WHERE n.quality_check_id = :quality_check_id and n.modified_type='INSERTED'")
    int deletePQCDetails(@Param("quality_check_id") String quality_check_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Quality_Check_Details_QcDetails n SET n.modified_type ='DELETED' WHERE n.quality_check_id = :quality_check_id and n.modified_type='INSERTED'")
    int deletePQCQualityDetails(@Param("quality_check_id") String quality_check_id);
}
