package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Gateinoutregister;

public interface GateinoutregisterRepository extends JpaRepository<Gateinoutregister, Long>{
	
	@Query("select COUNT(id) from Gateinoutregister")
	String countId();
	
	@Query("select d from Gateinoutregister d where d.reg_date=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id")
	List<Gateinoutregister> getGateinoutregisterList(@Param("currDate") String currDate,@Param("finyear") String finyear);

	/*@Modifying(clearAutomatically = true)
    @Query("UPDATE Gateinoutregister_Dtls d SET d.modified_type ='UPDATED' WHERE d.gateinoutregisterid =:gateinoutregisterid  and d.modified_type='INSERTED'")
    int revertGateinoutregister_Dtls(@Param("gateinoutregisterid") String gateinoutregisterid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Gateinoutregister_Dtls d SET d.modified_type ='DELETED' WHERE d.gateinoutregisterid =:gateinoutregisterid  and d.modified_type='INSERTED'")
    int deleteGateinoutregister_Dtls(@Param("gateinoutregisterid") String gateinoutregisterid);

	 @Query("select d from Gateinoutregister_Dtls d where d.modified_type ='INSERTED' and d.gateinoutregisterid =:gateinoutregisterid order by slno")
	List<Gateinoutregister_Dtls> getdetails(@Param("gateinoutregisterid") String gateinoutregisterid);
*/
	@Query(value = "{call getSearchreport(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Gateinoutregister> searchgateinoutRegister(@Param("tablename") String tablename
			,@Param("order_date") String order_date, 
			@Param("fromdate") String fromdate,
			@Param("todate") String todate,
			@Param("finyear") String finyear);
	
}
