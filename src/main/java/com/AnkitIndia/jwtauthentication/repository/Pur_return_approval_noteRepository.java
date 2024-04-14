package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_dynDTO;

public interface Pur_return_approval_noteRepository extends JpaRepository<Pur_return_approval_note, Long>{

	@Query("select COUNT(id) from Pur_return_approval_note")
	String countId();
	
	@Query( "select p from Pur_return_approval_note p where p.modified_type = 'INSERTED' AND p.company_id =:company AND p.fin_year =:finyear ORDER BY p.purreturnid DESC")
	List<Pur_return_approval_note> findAllRtnAppNotes(@Param("company") String company,@Param("finyear") String finyear);
	
	@Query("select COUNT(id) from Pur_return_approval_note where purreturndate =:rtndate and purreturntype =:rtntype ")
	String countPRAOrder(@Param("rtntype") String rtntype,@Param("rtndate") String rtndate);
	
/*	
	

	
	
	
	*/

	@Query( "select c from Pur_return_approval_note c where c.modified_type = 'INSERTED' AND c.loading_status !='Done' AND c.businessunit =:bunit and c.supplier =:supplier and c.purreturndate <=:tdate and c.purreturntype='Goods Return Due To Rejection' AND c.company_id = :company AND c.fin_year =:finyear ORDER BY c.purreturnid DESC")
	List<Pur_return_approval_note> getPurRtnAppNoteForLA(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	@Query( "select c from Pur_return_approval_note c where c.modified_type = 'INSERTED' AND c.businessunit =:bunit and c.supplier =:supplier and c.purreturndate <=:tdate and c.company_id = :company AND c.fin_year =:finyear AND c.reapp_note_status='1' AND c.weighment_status='2' AND c.prn_status !='Done' ")
	List<Pur_return_approval_note> getPurRtnAppNoteThruWe(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	@Query( "select c from Pur_return_approval_note c where c.modified_type = 'INSERTED' AND c.businessunit =:bunit and c.supplier =:supplier and c.purreturndate <=:tdate and c.company_id = :company AND c.fin_year =:finyear AND c.purreturntype ='Acceptance of Lower Rate' ")
	List<Pur_return_approval_note> getPurRtnAppNoteLowRate(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	@Query( "select c from Pur_return_approval_note c where c.modified_type = 'INSERTED' AND c.purreturnid = :purreturnid ")
	Pur_return_approval_note getPurRtnAppNoteDtls(@Param("purreturnid") String purreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_note r SET r.reapp_note_status ='1',r.weighment_status='1',r.weighment_id =:wid WHERE r.purreturnid = :purreturnid")
    int updateStatus(@Param("purreturnid") String purreturnid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_note r SET r.reapp_note_status ='1',r.weighment_status='2',r.weighment_id =:wid WHERE r.purreturnid = :purreturnid")
    int updateAppStatus(@Param("purreturnid") String purreturnid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_note r SET r.loading_status =:lstatus WHERE r.purreturnid = :purreturnid")
    int updateLoadingStatus(@Param("purreturnid") String purreturnid,@Param("lstatus") String lstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_return_approval_note r SET r.prn_status ='Done' WHERE r.purreturnid = :purreturnid")
    int updatePurRtnAppStatus(@Param("purreturnid") String purreturnid);
	
}
