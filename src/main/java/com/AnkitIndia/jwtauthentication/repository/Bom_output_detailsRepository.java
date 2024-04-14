package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bom_output_details;

public interface Bom_output_detailsRepository extends JpaRepository<Bom_output_details, Long>{
	
	@Query( "select b from Bom_output_details b where b.production_id =:production_id and b.modified_type ='INSERTED' ORDER BY b.sl_no ASC")
	List<Bom_output_details> getBom_output_details(@Param("production_id") String production_id);
	
	/*@Query( "select b from Bom_output_details b where b.production_id =:production_id and b.modified_type ='INSERTED' and b.item =:itemid")
	Bom_output_details getBomOutputDtls(@Param("production_id") String production_id,@Param("itemid") String itemid);
	*/
	@Query( "select b from Bom_output_details b where b.production_id =:production_id and b.modified_type ='INSERTED' and b.item =:itemid and b.packing =:packingid")
	Bom_output_details getBomOutputDtls(@Param("production_id") String production_id,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Bom_output_details b SET b.modified_type ='UPDATED' WHERE b.production_id = :production_id")
    int updateBom_output_details(@Param("production_id") String production_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Bom_output_details w SET w.modified_type ='DELETED' WHERE w.production_id = :production_id")
    int bomOutputUpdate(@Param("production_id") String production_id);
}
