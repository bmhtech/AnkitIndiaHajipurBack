package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_goods_receipt_other_information;

public interface Pur_goods_receipt_other_informationRepository extends JpaRepository<Pur_goods_receipt_other_information, Long>{

	@Query( "select p from Pur_goods_receipt_other_information p where p.grn_id = :code and p.modified_type ='INSERTED'" )
	Pur_goods_receipt_other_information grnOtherInfoRetriveList(@Param("code") String code);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_goods_receipt_other_information w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatePur_goods_receipt_other_information(@Param("grn_id") String grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_goods_receipt_other_information w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int pur_goods_receipt_other_informationUpdate(@Param("grn_id") String grn_id);
}
