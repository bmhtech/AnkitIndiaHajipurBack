
package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheatstock_Dtls;

public interface Wheatstock_DtlsRepository  extends JpaRepository<Wheatstock_Dtls, Long>{
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheatstock_Dtls d SET d.modified_type ='UPDATED' WHERE d.wheatreceiveid =:wheatreceiveid  and d.modified_type='INSERTED'")
    int revertWheatstock_Dtls(@Param("wheatreceiveid") String wheatreceiveid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheatstock_Dtls d SET d.modified_type ='DELETED' WHERE d.wheatreceiveid =:wheatreceiveid  and d.modified_type='INSERTED'")
    int deleteWheatreceiving_Dtls(@Param("wheatreceiveid") String wheatreceiveid);
	
	
	 @Query("select d from Wheatstock_Dtls d where d.modified_type ='INSERTED' and d.wheatreceiveid =:wheatreceiveid order by slno")
	List<Wheatstock_Dtls> getStockDetails(@Param("wheatreceiveid") String wheatreceiveid);
	
	@Query(value = "SELECT CASE  WHEN COUNT(id)> 0 THEN (SELECT closingbags FROM wheatstock_Dtls  WHERE modified_type ='INSERTED' AND date<:date AND wheat_grade=:itemId  ORDER BY id DESC LIMIT 1) ELSE 0 END AS closingbags,"
			+ "CASE  WHEN COUNT(id)> 0 THEN (SELECT closingloose FROM wheatstock_Dtls  WHERE modified_type ='INSERTED' AND date<:date AND wheat_grade=:itemId  ORDER BY id DESC LIMIT 1) ELSE 0 END AS closingloose,"
			+ "CASE  WHEN COUNT(id)> 0 THEN (SELECT closingqty FROM wheatstock_Dtls  WHERE modified_type ='INSERTED' AND date<:date AND wheat_grade=:itemId  ORDER BY id DESC LIMIT 1) ELSE 0 END AS closingqty "
			+ "FROM wheatstock_Dtls d WHERE d.modified_type ='INSERTED' AND d.date<:date and d.wheat_grade=:itemId",nativeQuery=true)
	List<Object[]> closingstockitem(@Param("date") String date,@Param("itemId") String itemId);

	
	
}