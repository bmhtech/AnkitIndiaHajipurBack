package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.AnkitIndia.jwtauthentication.model.Binreportdetails;



public interface BinreportdetailsRepository extends JpaRepository<Binreportdetails, Long>{

	
	
	@Query(value ="select d from Binreportdetails d where  d.binreportid=:binreportid and d.modified_type='INSERTED'")
	List<Binreportdetails> getdetailsrow(@Param("binreportid") String binreportid);
	
	@Query(value ="select DISTINCT(bingroupid),bingroupname,sum(dip),sum(mt) from binreportdetails d where  d.binreportid=:binreportid and d.modified_type='INSERTED' GROUP BY d.bingroupname",nativeQuery=true)
	List<Object[]> getdetailscolumn(@Param("binreportid") String binreportid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Binreportdetails d SET d.modified_type ='UPDATED' WHERE d.binreportid =:binreportid  and d.modified_type='INSERTED'")
    int updateprevious(@Param("binreportid") String binreportid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Binreportdetails d SET d.modified_type ='DELETED' WHERE d.binreportid =:binreportid  and d.modified_type='INSERTED'")
    int deleteprevious(@Param("binreportid") String binreportid);
}
