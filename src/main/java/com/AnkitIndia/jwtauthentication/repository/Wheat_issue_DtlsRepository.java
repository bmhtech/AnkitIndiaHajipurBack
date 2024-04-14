package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheat_issue_Dtls;

public interface Wheat_issue_DtlsRepository extends JpaRepository<Wheat_issue_Dtls, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheat_issue_Dtls d SET d.modified_type ='UPDATED' WHERE d.wheatreceiveid =:wheatreceiveid  and d.modified_type='INSERTED'")
    int revertWheatreceiving_Issue_Dtls(@Param("wheatreceiveid") String wheatreceiveid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wheat_issue_Dtls d SET d.modified_type ='DELETED' WHERE d.wheatreceiveid =:wheatreceiveid  and d.modified_type='INSERTED'")
    int deleteWheatreceiving_Issue_Dtls(@Param("wheatreceiveid") String wheatreceiveid);

	 @Query("select d from Wheat_issue_Dtls d where d.modified_type ='INSERTED' and d.wheatreceiveid =:wheatreceiveid order by slno")
	List<Wheat_issue_Dtls> getIssueDetails(@Param("wheatreceiveid") String wheatreceiveid);
	
	
}
