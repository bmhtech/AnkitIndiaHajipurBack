package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_doc;

public interface Wm_unload_advice_docRepository extends JpaRepository<Wm_unload_advice_doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_doc w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_docUpdate(@Param("unadviceid") String unadviceid);
	
	@Query("select w from Wm_unload_advice_doc w where w.unadviceid = :code and w.modified_type = 'INSERTED'")
	List<Wm_unload_advice_doc> wmUnAdviceDocRetriveList(@Param("code") String code);
	
	@Query(value="select * from wm_unload_advice_doc w where w.modified_type = 'INSERTED' and w.unadviceid=:code",nativeQuery=true)
	 List<Map<String,Object>> wmUnAdviceDocRetriveListFast(@Param("code") String code);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_doc w SET w.modified_type ='DELETED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_docupdate(@Param("unadviceid") String unadviceid);
	
}
