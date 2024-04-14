package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;

public interface Nongoodssrn_item_detailsRepository extends JpaRepository<Nongoodssrn_item_details, Long>{
	
	@Query( "select n from Nongoodssrn_item_details n where n.modified_type = 'INSERTED' AND n.srnid = :srnid order by n.slno")
	List<Nongoodssrn_item_details> getItemDetails(@Param("srnid") String srnid);
	
	@Query( "select n from Nonservicesrn_desc_details n where n.modified_type = 'INSERTED' AND n.srnid = :srnid AND n.serviceno = :serviceno")
	List<Nonservicesrn_desc_details> getSrnItemDetailsSerList(@Param("srnid") String srnid,@Param("serviceno") String serviceno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodssrn_item_details n SET n.modified_type ='UPDATED' WHERE n.srnid = :srnid")
    int updateItemDetails(@Param("srnid") String srnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nonservicesrn_desc_details n SET n.modified_type ='UPDATED' WHERE n.srnid = :srnid AND n.serviceno = :serviceno")
    int updateItemServiceDetails(@Param("srnid") String srnid,@Param("serviceno") String serviceno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nongoodssrn_item_details n SET n.modified_type ='DELETED' WHERE n.srnid =:srnid and n.modified_type='INSERTED'")
    int revertItem(@Param("srnid") String srnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Nonservicesrn_desc_details n SET n.modified_type ='DELETED' WHERE n.srnid =:srnid and n.modified_type='INSERTED'")
    int revertService(@Param("srnid") String srnid);
}
