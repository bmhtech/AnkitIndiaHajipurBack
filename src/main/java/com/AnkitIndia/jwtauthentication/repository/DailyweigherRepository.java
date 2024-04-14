package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Dailyweigher;

public interface DailyweigherRepository extends JpaRepository<Dailyweigher, Long>{

	@Query("select COUNT(id) from Dailyweigher")
	String countId();
	
	@Query(value="select id,dwg_id,b_unit,b_unit_name,machine,oacumwt,cacumwt,oacumpcs,cacumpcs,totalbags,totalkgs,differencebags,differencekgs,weigherdate from dailyweigher where modified_type='INSERTED' ", nativeQuery=true)
	List<Map<String, Object>> getDailyweigherList();
	
	@Query(value="select slno,dwg_id,item_code,packing_item,bags,kgs from dailyweigher_dtls where modified_type='INSERTED' and dwg_id=:code order by slno", nativeQuery=true)
	List<Map<String, Object>> retriveDailyweigherDetails(@Param("code") String code);
	
	@Query(value="SELECT d.*,w.slno,w.item_code,w.item_name,w.kgs,w.packing_item,w.packing_item_name,w.bags FROM dailyweigher d,dailyweigher_dtls w WHERE d.modified_type='INSERTED' AND d.modified_type=w.modified_type AND d.dwg_id=w.dwg_id AND d.weigherdate=:wdate AND d.machine=:machine ORDER BY w.slno", nativeQuery=true)
	List<Map<String, Object>> DailyWeigherReport(@Param("wdate") String wdate,@Param("machine") String machine);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Dailyweigher_Dtls u SET u.modified_type ='UPDATED' WHERE u.dwg_id = :dwg_id and u.modified_type ='INSERTED'")
	int updateDailyweigherDtls(@Param("dwg_id") String dwg_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Dailyweigher_Dtls d SET d.modified_type ='DELETED' WHERE d.dwg_id =:dwg_id  and d.modified_type='INSERTED'")
    int deleteDailyweigherDtls(@Param("dwg_id") String dwg_id);
}
