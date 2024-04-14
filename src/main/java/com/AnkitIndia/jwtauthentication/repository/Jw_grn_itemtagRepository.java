package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Jw_grn_itemtag;

public interface Jw_grn_itemtagRepository extends JpaRepository<Jw_grn_itemtag, Long>{
	
	@Query("Select count(id) from Jw_grn_itemtag")
	String countJobItemTag();

	@Query(value="SELECT g.`grn_id`,g.`grn_no`,g.`grn_date`,g.`supplier`,SUM(i.pssd_mat_wt) AS tot_wt FROM pur_good_receipt g,pur_good_receipt_item_details i WHERE g.`grn_id`=i.`grn_id` AND g.`modified_type`='INSERTED' AND i.`modified_type`='INSERTED'  GROUP BY g.`grn_id`", nativeQuery=true)
	List<Map<String, Object>> getJwGRN();
	
	@Query(value="SELECT g.`grn_id`,g.`grn_no`,g.`grn_date`,g.`supplier`,SUM(i.pssd_mat_wt) AS tot_wt FROM pur_good_receipt g,pur_good_receipt_item_details i WHERE g.`grn_id`=i.`grn_id` AND g.`modified_type`='INSERTED' AND i.`modified_type`='INSERTED' AND g.`grn_id` NOT IN (SELECT grn_id FROM jw_grn_itemtag WHERE  `modified_type`='INSERTED' ) GROUP BY g.`grn_id`", nativeQuery=true)
	List<Map<String, Object>> getJwGRNunique();
	
	@Query(value="select i.* from item_master i where i.modified_type = 'INSERTED' and i.item_active =:status and i.item_type ='JOB WORK' and i.sales_item =:status", nativeQuery=true)
	List<Map<String, Object>> getJobItemList(@Param("status") boolean status);
	
	@Query(value="select * from jw_grn_itemtag where modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getJobItemTagMaster();
	
	//@Modifying(clearAutomatically = true)
   // @Query("UPDATE Pur_good_receipt w SET w.grnpartytag =1 WHERE w.grn_id = :grn_id AND w.grn_no = :grn_no AND w.modified_type ='INSERTED'")
   // int updatePurGRNPartyTag(@Param("grn_id") String grn_id,@Param("grn_no") String grn_no);
}
