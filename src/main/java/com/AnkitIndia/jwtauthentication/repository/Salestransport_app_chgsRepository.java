package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Salestransport_app_chgs;

public interface Salestransport_app_chgsRepository extends JpaRepository<Salestransport_app_chgs, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Salestransport_app_chgs w SET w.modified_type ='UPDATED' WHERE w.sales_tranport_id = :sales_tranport_id")
    int salestransport_app_chgsupdate(@Param("sales_tranport_id") String sales_tranport_id);
	
	@Query(value="select * from salestransport_app_chgs p where p.sales_tranport_id =:sales_tranport_id and p.modified_type = 'INSERTED' ORDER BY p.slno", nativeQuery=true)
	List<Map<String,Object>> getSalestransportappchgsList(@Param("sales_tranport_id") String sales_tranport_id);
	
	/*@Modifying(clearAutomatically = true)
    @Query("UPDATE Salestransport_app_chgs w SET w.modified_type ='DELETED' WHERE w.sales_tranport_id = :sales_tranport_id")
    int salestransportAppChgsUpdate(@Param("sales_tranport_id") String sales_tranport_id);
	*/
	@Query(value = "SELECT * FROM salestransport_app_chgs WHERE modified_type='INSERTED' AND sales_tranport_id=:transportId", nativeQuery=true)
	List<Map<String, Object>> getSalesTransportChgs(@Param("transportId") String transportId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Salestransport_app_chgs w SET w.modified_type ='DELETED',w.deleted_by=:user,w.deleted_on=:ldt WHERE w.sales_tranport_id = :sales_tranport_id and w.modified_type='INSERTED'")
    int deleteSTACDetails(@Param("sales_tranport_id") String sales_tranport_id,
    		@Param("ldt") LocalDateTime ldt,@Param("user") String user);
}
