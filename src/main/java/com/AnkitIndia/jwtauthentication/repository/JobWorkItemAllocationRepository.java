package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.JobWorkItemAllocation;

public interface JobWorkItemAllocationRepository extends JpaRepository<JobWorkItemAllocation, Long>
{
	@Query("Select count(id) from JobWorkItemAllocation")
	String countJobWorkItem();
	
	@Query(value="SELECT * FROM job_work_item_allocation WHERE modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> JobWorkItemAllocationList();
	
	@Query(value="SELECT * FROM job_work_item_allocation_view WHERE job_item=:item AND party=:party", nativeQuery=true)
	Map<String, Object> getItemQtythruLoading(@Param ("item") String item,
											  @Param("party") String party);
	
	@Query(value="SELECT CASE WHEN count(job_item)>0 THEN 1 ELSE 0 END FROM job_work_item_allocation_view WHERE job_item=:item AND party=:party", nativeQuery=true)
	int checkJobAllocationData(@Param ("item") String item,
											  @Param("party") String party);
	
	@Query(value="SELECT CASE WHEN rest_wt>=:packing_qty THEN 'Yes' ELSE 'No' END FROM job_work_item_allocation_view WHERE job_item=:item AND party=:party", nativeQuery=true)
	String allocationStatus(@Param ("item") String item,
											  @Param("party") String party,@Param("packing_qty") Double packing_qty);
	
	@Query(value="SELECT CASE WHEN (rest_wt+(SELECT SUM(item_qty) FROM wm_loading_advice_item_dtls_for_jobwork WHERE modified_type='INSERTED' AND advice_id=:advice_id))>=:packing_qty THEN 'Yes' ELSE 'No' END FROM job_work_item_allocation_view WHERE job_item=:item AND party=:party", nativeQuery=true)
	String allocationStatusUpdate(@Param ("item") String item,@Param ("advice_id") String advice_id,
											  @Param("party") String party,@Param("packing_qty") Double packing_qty);
	
	@Query(value="SELECT rest_wt FROM jw_allocation_grn WHERE party=:party", nativeQuery=true)
	Map<String, Object> getJwAllocationRestWt(@Param("party") String party);
}
