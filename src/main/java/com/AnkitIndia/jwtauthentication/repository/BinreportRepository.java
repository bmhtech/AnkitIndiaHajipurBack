package com.AnkitIndia.jwtauthentication.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Binreport;



public interface BinreportRepository extends JpaRepository<Binreport, Long>{
	
	@Query("select COUNT(id) from Binreport")
	String countId();
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Binreport pl where pl.modified_type = 'INSERTED' ")
	Boolean checkbinreport();
	
	@Query(value ="select * from Binreport d where  d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id DESC LIMIT 1",nativeQuery=true)
	Binreport getlastrowdata(@Param("finyear") String finyear);
	
	@Query(value ="select d from Binreport d where  d.fin_year=:finyear and d.modified_type='INSERTED'")
	List<Binreport> getbillreportlist(@Param("finyear") String finyear);
	
}
