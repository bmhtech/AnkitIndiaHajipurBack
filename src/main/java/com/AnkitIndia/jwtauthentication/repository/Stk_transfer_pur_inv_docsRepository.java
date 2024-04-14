package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_docs;

public interface Stk_transfer_pur_inv_docsRepository extends JpaRepository<Stk_transfer_pur_inv_docs, Long>{
	
	@Query( "select a from Stk_transfer_pur_inv_docs a where a.stk_trans_pur_inv_id =:purinvid and a.modified_type ='INSERTED' ")
	List<Stk_transfer_pur_inv_docs> getStk_transfer_pur_inv_docs(@Param("purinvid") String purinvid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_docs s SET s.modified_type ='UPDATED' WHERE s.stk_trans_pur_inv_id = :purinvid")
    int updateStk_transfer_pur_inv_docs(@Param("purinvid") String purinvid);

}
