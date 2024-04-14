package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Grnregisterreport_Dtls;

public interface Grnregisterreport_DtlsReository extends JpaRepository<Grnregisterreport_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Grnregisterreport_Dtls d SET d.modified_type ='UPDATED' WHERE d.grnregisterid =:grnregisterid  and d.modified_type='INSERTED'")
    int revertGrnregisterreport_Dtls(@Param("grnregisterid") String grnregisterid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Grnregisterreport_Dtls d SET d.modified_type ='DELETED' WHERE d.grnregisterid =:grnregisterid  and d.modified_type='INSERTED'")
    int deleteGrnregisterreport_Dtls(@Param("grnregisterid") String grnregisterid);

	 @Query("select d from Grnregisterreport_Dtls d where d.modified_type ='INSERTED' and d.grnregisterid =:grnregisterid order by slno")
	List<Grnregisterreport_Dtls> getdetails(@Param("grnregisterid") String grnregisterid);
	
	@Query("select d.id,d.grnregisterid,d.slno,d.itemdesc,d.quantity,d.unit,d.rate from Grnregisterreport_Dtls d where d.grndate=:currDate and d.fin_year=:finyear and d.modified_type ='INSERTED' order by slno")
	List<Object[]> getStaticData(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
}
