package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Binreport;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;

public interface Dailystockfinishgood_DtlsReository extends JpaRepository<Dailystockfinishgood_Dtls, Long>{
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailystockfinishgood_Dtls d SET d.modified_type ='UPDATED' WHERE d.dailystockid =:dailystockid  and d.modified_type='INSERTED'")
    int revertDailystockfinishgood_DtlsReository(@Param("dailystockid") String dailystockid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailystockfinishgood_Dtls d SET d.modified_type ='DELETED' WHERE d.dailystockid =:dailystockid  and d.modified_type='INSERTED'")
    int revertdeleteDailystockfinishgood_DtlsReository(@Param("dailystockid") String dailystockid);

	 @Query("select d from Dailystockfinishgood_Dtls d where d.modified_type ='INSERTED' and d.dailystockid =:dailystockid order by slno asc")
	List<Dailystockfinishgood_Dtls> getdetails(@Param("dailystockid") String dailystockid);
	
	@Query("select d from Dailystockfinishgood_Dtls d where d.modified_type ='INSERTED' and d.dailystockid =:dailystockid and d.item_code=:itemId")
	Dailystockfinishgood_Dtls getitemperiddetails(@Param("dailystockid") String dailystockid,@Param("itemId") String itemId);
	
	@Query(value ="select * from dailystockfinishgood_Dtls d where d.modified_type ='INSERTED' and d.date<:date and d.item_code=:itemId ORDER BY id DESC LIMIT 1",nativeQuery=true)
	Dailystockfinishgood_Dtls getitemperidDateDetails(@Param("date") String date,@Param("itemId") String itemId);
	
	@Query(value = "SELECT CASE  WHEN count(d)> 0 THEN true ELSE false END FROM Dailystockfinishgood_Dtls d where d.modified_type ='INSERTED' and d.date<:date and d.item_code=:itemId")
	Boolean checkItem(@Param("date") String date,@Param("itemId") String itemId);

	
	
	@Query(value = "SELECT CASE  WHEN COUNT(id)> 0 THEN (SELECT closingstock FROM dailystockfinishgood_Dtls  WHERE modified_type ='INSERTED' AND date<:date AND item_code=:itemId  ORDER BY id DESC LIMIT 1) ELSE 0 END FROM dailystockfinishgood_Dtls d WHERE d.modified_type ='INSERTED' AND d.date<:date and d.item_code=:itemId",nativeQuery=true)
	String closingstockitem(@Param("date") String date,@Param("itemId") String itemId);

}
