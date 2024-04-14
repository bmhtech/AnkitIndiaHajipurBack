package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Jw_grn_partywitem_details;

public interface Jw_grn_partywitem_detailsRepository extends JpaRepository<Jw_grn_partywitem_details, Long>{
	
	@Query("select j from Jw_grn_partywitem_details j where j.modified_type='INSERTED' and j.jw_grn_tag =:jw_grn_tag")
	List<Jw_grn_partywitem_details> getJobItemTagMaster(@Param("jw_grn_tag") String jw_grn_tag);
	
	
	@Query(value="SELECT IFNULL(rest_wt,0) FROM job_work_item_allocation_view WHERE  party=:party AND job_item=:job_item ",nativeQuery=true)
	String checkrestwt(@Param("job_item") String job_item,@Param("party") String party);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Jw_grn_partywitem_details w SET w.modified_type =:mstatus WHERE w.jw_grn_tag = :jw_grn_tag AND w.modified_type ='INSERTED'")
    int updateJwGrnPartywitemDetails(@Param("jw_grn_tag") String jw_grn_tag,@Param("mstatus") String mstatus);
	
	@Query(value="SELECT * from jw_grn_partywitem_details WHERE party=:party AND jw_grn_tag=:jw_grn_tag AND modified_type ='INSERTED' order by slno",nativeQuery=true)
	List<Map<String, Object>> getJwGrnPartywitemDetails(@Param("jw_grn_tag") String jw_grn_tag,@Param("party") String party);
	
	@Query(value="SELECT j.qty FROM jw_grn_partywitem_details j WHERE j.modified_type='INSERTED' AND j.job_item=:job_item AND j.party=:party AND j.jw_grn_tag=:jw_grn_tag",nativeQuery=true)
	double getpreviousQty(@Param("job_item") String job_item,@Param("party") String party,@Param("jw_grn_tag") String jw_grn_tag);
}
