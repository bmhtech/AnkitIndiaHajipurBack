package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;

public interface GatepassChecklistRepository extends JpaRepository<GatepassChecklist, Long>{

	@Query("select COUNT(id) from GatepassChecklist")
	String countId();
	
	@Query("select p from GatepassChecklist p where p.modified_type='INSERTED' and p.checkin='Gate In'")
	List<GatepassChecklist> getGatepasschecklistin();
	
	@Query("select p from GatepassChecklist p where p.modified_type='INSERTED' and p.checkin='Gate Out Authorization'")
	List<GatepassChecklist> getGatepasschecklistout();
	
	@Query("select g from GatepassChecklist g where g.modified_type='INSERTED'")
	List<GatepassChecklist> findcheckList();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE GatepassChecklist p SET p.modified_type='UPDATED' WHERE p.checklist_code = :checklist_code and p.modified_type ='INSERTED'" )
	int updateCheckList(@Param("checklist_code") String checklist_code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM GatepassGetin_details pl where pl.modified_type != 'DELETED' and pl.checklist_code=:code")
	Boolean checkGatePassCheckListInUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM GatepassGateoutAuthorization_details pl where pl.modified_type != 'DELETED' and pl.checklist_code=:code")
	Boolean checkGatePassCheckListAuthUsage(@Param("code") String code);
}
