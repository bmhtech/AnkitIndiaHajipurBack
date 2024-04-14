package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Store_issue_note_details;

public interface Store_issue_note_detailsRepository extends JpaRepository<Store_issue_note_details, Long>{

	@Query(value="select * from store_issue_note_details where modified_type='INSERTED' AND store_issue_id=:store_issue_id order by slno", nativeQuery=true)
	List<Map<String, Object>> retriveStoreIssueNoteDetails(@Param("store_issue_id") String store_issue_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Store_issue_note_details w SET w.modified_type =:mstatus WHERE w.store_issue_id = :store_issue_id AND w.modified_type ='INSERTED'")
    int updateStoreIssueDetails(@Param("store_issue_id") String store_issue_id,@Param("mstatus") String mstatus);
}
