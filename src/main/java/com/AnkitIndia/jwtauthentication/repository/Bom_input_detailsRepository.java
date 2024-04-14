package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bom_input_details;

public interface Bom_input_detailsRepository extends JpaRepository<Bom_input_details, Long>{
	
	@Query("select b from Bom_input_details b where b.production_id =:production_id and b.modified_type ='INSERTED' ORDER BY b.sl_no ASC")
	List<Bom_input_details> getBomInputDetails(@Param("production_id") String production_id);
	
	@Query(value="SELECT * FROM production_transaction_input t WHERE t.production_id=:prodid ORDER BY t.sl_no ASC", nativeQuery = true)
	List<Map<String,Object>> getBomInputDetailsnew(@Param("prodid") String prodid);

	
	/*@Query("select b from Bom_input_details b where b.production_id =:production_id and b.modified_type ='INSERTED' and b.item =:itemid")
	Bom_input_details getBomInputDtls(@Param("production_id") String production_id,@Param("itemid") String itemid);*/
	
	@Query("select b from Bom_input_details b where b.production_id =:production_id and b.modified_type ='INSERTED' and b.item =:itemid and b.packing =:packingid")
	Bom_input_details getBomInputDtls(@Param("production_id") String production_id,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Bom_input_details b SET b.modified_type ='UPDATED' WHERE b.production_id = :production_id")
    int updateBom_input_details(@Param("production_id") String production_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Bom_input_details w SET w.modified_type ='DELETED' WHERE w.production_id = :production_id")
    int bomInputUpdate(@Param("production_id") String production_id);
}
