package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport_Dtls;

public interface Millbreakdownreport_DtlsRepository extends JpaRepository<Millbreakdownreport_Dtls, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Millbreakdownreport_Dtls d SET d.modified_type ='UPDATED' WHERE d.millbreakdownid =:millbreakdownid  and d.modified_type='INSERTED'")
    int revertMillbreakdownreportDtls(@Param("millbreakdownid") String millbreakdownid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Millbreakdownreport_Dtls d SET d.modified_type ='DELETED' WHERE d.millbreakdownid =:millbreakdownid  and d.modified_type='INSERTED'")
    int deleteMillbreakdownreportDtls(@Param("millbreakdownid") String millbreakdownid);

	 @Query("select d from Millbreakdownreport_Dtls d where d.modified_type ='INSERTED' and d.millbreakdownid =:millbreakdownid order by slno")
	List<Millbreakdownreport_Dtls> getdetails(@Param("millbreakdownid") String millbreakdownid);
	
}
