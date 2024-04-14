package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_tax_info;

public interface Stk_transfer_pur_inv_tax_infoRepository extends JpaRepository<Stk_transfer_pur_inv_tax_info, Long>{
	
	@Query( "select a from Stk_transfer_pur_inv_tax_info a where a.stk_trans_pur_inv_id =:purinvid and a.modified_type ='INSERTED' ")
	Stk_transfer_pur_inv_tax_info getStk_transfer_pur_inv_tax_info(@Param("purinvid") String purinvid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_tax_info s SET s.modified_type ='UPDATED' WHERE s.stk_trans_pur_inv_id = :purinvid")
    int updateStk_transfer_pur_inv_tax_info(@Param("purinvid") String purinvid);

}
