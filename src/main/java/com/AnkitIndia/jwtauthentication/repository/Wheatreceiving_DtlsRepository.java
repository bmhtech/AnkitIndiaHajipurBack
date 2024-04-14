package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;


public interface Wheatreceiving_DtlsRepository extends JpaRepository<Wheatreceiving_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheatreceiving_Dtls d SET d.modified_type ='UPDATED' WHERE d.wheatreceiveid =:wheatreceiveid  and d.modified_type='INSERTED'")
    int revertWheatreceiving_Dtls(@Param("wheatreceiveid") String wheatreceiveid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheatreceiving_Dtls d SET d.modified_type ='DELETED' WHERE d.wheatreceiveid =:wheatreceiveid  and d.modified_type='INSERTED'")
    int deleteWheatreceiving_Dtls(@Param("wheatreceiveid") String wheatreceiveid);

	 @Query("select d from Wheatreceiving_Dtls d where d.modified_type ='INSERTED' and d.wheatreceiveid =:wheatreceiveid order by slno")
	List<Wheatreceiving_Dtls> getdetails(@Param("wheatreceiveid") String wheatreceiveid);
	

}
