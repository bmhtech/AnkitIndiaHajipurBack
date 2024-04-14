package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Granulation_Dtls;
import com.AnkitIndia.jwtauthentication.model.Misclabreportfg_Dtls;

public interface Granulation_DtlsRepository extends JpaRepository<Granulation_Dtls, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Granulation_Dtls d SET d.modified_type ='UPDATED' WHERE d.granulationreportid =:granulationreportid  and d.modified_type='INSERTED'")
    int revertGranulation_Dtls(@Param("granulationreportid") String granulationreportid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Granulation_Dtls d SET d.modified_type ='DELETED' WHERE d.granulationreportid =:granulationreportid  and d.modified_type='INSERTED'")
    int deleteGranulation_Dtls(@Param("granulationreportid") String granulationreportid);

	 @Query("select d from Granulation_Dtls d where d.modified_type ='INSERTED' and d.granulationreportid =:granulationreportid order by slno")
	List<Granulation_Dtls> getdetails(@Param("granulationreportid") String granulationreportid);
}
