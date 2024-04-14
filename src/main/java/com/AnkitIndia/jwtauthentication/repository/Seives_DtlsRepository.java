package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;

public interface Seives_DtlsRepository extends JpaRepository<Seives_Dtls, Long>{

	@Query("select d from Seives_Dtls d where d.itemid =:itemid and d.modified_type='INSERTED' ORDER BY slno")
	List<Seives_Dtls> getSeivesDetailsThruItemId(@Param("itemid") String itemid);
	
	@Query("select d from Seives_Dtls d where d.seivesid =:seivesid and d.modified_type='INSERTED' ORDER BY id")
	List<Seives_Dtls> getSeivesDetailsThruId(@Param("seivesid") String seivesid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Seives_Dtls d SET d.modified_type ='UPDATED' WHERE d.seivesid =:seivesid  and d.modified_type='INSERTED'")
    int revertSeives_Dtls(@Param("seivesid") String seivesid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Seives_Dtls d SET d.modified_type ='DELETED' WHERE d.seivesid =:seivesid  and d.modified_type='INSERTED'")
    int deleteSeives_Dtls(@Param("seivesid") String seivesid);
}
