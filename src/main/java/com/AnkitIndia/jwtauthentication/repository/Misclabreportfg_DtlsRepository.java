package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Misclabreportfg_Dtls;

public interface Misclabreportfg_DtlsRepository extends JpaRepository<Misclabreportfg_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Misclabreportfg_Dtls d SET d.modified_type ='UPDATED' WHERE d.misclabreportfgid =:misclabreportfgid  and d.modified_type='INSERTED'")
    int revertMisclabreportfg_DtlsRepository(@Param("misclabreportfgid") String misclabreportfgid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Misclabreportfg_Dtls d SET d.modified_type ='DELETED' WHERE d.misclabreportfgid =:misclabreportfgid  and d.modified_type='INSERTED'")
    int deleteMisclabreportfg_Dtls(@Param("misclabreportfgid") String misclabreportfgid);

	 @Query("select d from Misclabreportfg_Dtls d where d.modified_type ='INSERTED' and d.misclabreportfgid =:misclabreportfgid order by slno")
	List<Misclabreportfg_Dtls> getdetails(@Param("misclabreportfgid") String misclabreportfgid);
	
	

}
