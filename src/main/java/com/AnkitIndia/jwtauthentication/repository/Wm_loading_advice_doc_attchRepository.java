package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_doc_attch;

public interface Wm_loading_advice_doc_attchRepository extends JpaRepository<Wm_loading_advice_doc_attch, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_doc_attch w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_doc_attchupdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);

	@Query( "select w from Wm_loading_advice_doc_attch w where w.advice_id =:code and w.modified_type = 'INSERTED' ORDER BY w.id")
	List<Wm_loading_advice_doc_attch> loadingDocRetriveList(@Param("code") String code);
}
