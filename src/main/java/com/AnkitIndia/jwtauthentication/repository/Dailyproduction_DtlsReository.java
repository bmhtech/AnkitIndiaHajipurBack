package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;

public interface Dailyproduction_DtlsReository extends JpaRepository<Dailyproduction_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailyproduction_Dtls d SET d.modified_type ='UPDATED' WHERE d.dailyproductionid =:dailyproductionid  and d.modified_type='INSERTED'")
    int revertDailyproduction_Dtls(@Param("dailyproductionid") String dailyproductionid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailyproduction_Dtls d SET d.modified_type ='DELETED' WHERE d.dailyproductionid =:dailyproductionid  and d.modified_type='INSERTED'")
    int deleteDailyproduction_Dtls(@Param("dailyproductionid") String dailyproductionid);

	 @Query("select d from Dailyproduction_Dtls d where d.modified_type ='INSERTED' and d.dailyproductionid =:dailyproductionid order by slno asc")
	List<Dailyproduction_Dtls> getdetails(@Param("dailyproductionid") String dailyproductionid);
	
	 @Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Dailyproduction_Dtls pl where pl.modified_type = 'INSERTED' and  pl.item_code=:itemid ")
	 Boolean checkexistproductionrep(@Param("itemid") String itemid);
	
	@Query("select d from Dailyproduction_Dtls d where d.modified_type ='INSERTED' and d.dailyproductionid =:dailyproductionid and d.item_code=:itemid order by slno asc")
	Dailyproduction_Dtls getlastrowdetails(@Param("itemid") String itemid,@Param("dailyproductionid") String dailyproductionid);

}
