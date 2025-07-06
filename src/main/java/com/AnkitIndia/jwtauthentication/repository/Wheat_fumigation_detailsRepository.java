package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation_details;

public interface Wheat_fumigation_detailsRepository extends JpaRepository<Wheat_fumigation_details, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheat_fumigation_details b SET b.modified_type ='UPDATED' WHERE b.fumigation_id = :fumigation_id AND b.modified_type='INSERTED'")
    int updateWheat_fumigation_details(@Param("fumigation_id") String fumigation_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheat_fumigation_details w SET w.modified_type ='DELETED' WHERE w.fumigation_id = :fumigation_id AND w.modified_type='INSERTED'")
    int wheatFumigationDetailsUpdate(@Param("fumigation_id") String fumigation_id);
	
	@Query(value= "select * from wheat_fumigation_details where modified_type='INSERTED' AND fumigation_id=:fumigation_id", nativeQuery=true)
	List<Map<String,Object>> getWheatFumigationDetails(@Param("fumigation_id") String fumigation_id);
	
	@Query(value= "select i.*,f.fumigation_date from wheat_fumigation_details i,wheat_fumigation f where i.fumigation_id=f.fumigation_id AND i.modified_type='INSERTED' AND f.modified_type='INSERTED' AND i.stack_use='No'", nativeQuery=true)
	List<Map<String,Object>> getAllWheatFumiDtlsUnusedList();
	
	@Query(value= "select i.*,f.fumigation_date from wheat_fumigation_details i,wheat_fumigation f where i.fumigation_id=f.fumigation_id AND i.modified_type='INSERTED' AND f.modified_type='INSERTED' AND i.stack_use='Yes'", nativeQuery=true)
	List<Map<String,Object>> getAllWheatFumiDtlsUsedList();
	
	@Query(value= "select i.*,f.fumigation_date from wheat_fumigation_details i,wheat_fumigation f where i.fumigation_id=f.fumigation_id AND i.modified_type='INSERTED' AND f.modified_type='INSERTED'", nativeQuery=true)
	List<Map<String,Object>> getAllWheatFumiDtlsAllList();
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheat_fumigation_details p SET p.stack_open_date =:allocate_date,p.status=:status,p.stack_use=:stack_use,p.allocate_status=:allocate_status,p.wheat_fumi_qc=:wheat_fumi_qc WHERE p.id =:id AND p.fumigation_id =:fumigation_id AND p.modified_type = 'INSERTED'")
    int updateFumigationDetails(@Param("id") long id,@Param("fumigation_id") String fumigation_id,@Param("allocate_date") String allocate_date,@Param("stack_use") String stack_use,
    		@Param("status") String status,@Param("allocate_status") String allocate_status,@Param("wheat_fumi_qc") String wheat_fumi_qc);
	
}
