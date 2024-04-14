package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Otherparameterreport_Dtls;

public interface Otherparameterreport_DtlsRepository extends JpaRepository<Otherparameterreport_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Otherparameterreport_Dtls d SET d.modified_type ='UPDATED' WHERE d.otherparameterid =:otherparameterid  and d.modified_type='INSERTED'")
    int revertOtherParameterDtls(@Param("otherparameterid") String otherparameterid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Otherparameterreport_Dtls d SET d.modified_type ='DELETED' WHERE d.otherparameterid =:otherparameterid  and d.modified_type='INSERTED'")
    int deleteOtherParameterDtls(@Param("otherparameterid") String otherparameterid);

	 @Query("select d from Otherparameterreport_Dtls d where d.modified_type ='INSERTED' and d.otherparameterid =:otherparameterid order by slno")
	List<Otherparameterreport_Dtls> getdetails(@Param("otherparameterid") String otherparameterid);
}
