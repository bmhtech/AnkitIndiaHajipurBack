package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls_One;

public interface Dailyproduction_Dtls_OneRepository extends JpaRepository<Dailyproduction_Dtls_One, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailyproduction_Dtls_One d SET d.modified_type ='UPDATED' WHERE d.dailyproductionid =:dailyproductionid  and d.modified_type='INSERTED'")
    int revertDailyproduction_Dtls1(@Param("dailyproductionid") String dailyproductionid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailyproduction_Dtls_One d SET d.modified_type ='DELETED' WHERE d.dailyproductionid =:dailyproductionid  and d.modified_type='INSERTED'")
    int deleteDailyproduction_Dtls1(@Param("dailyproductionid") String dailyproductionid);

	 @Query("select d from Dailyproduction_Dtls_One d where d.modified_type ='INSERTED' and d.dailyproductionid =:dailyproductionid ")
	List<Dailyproduction_Dtls_One> getdetails1(@Param("dailyproductionid") String dailyproductionid);
}
