package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Loading_point;

public interface Loading_pointRepository extends JpaRepository<Loading_point,Long>{
	
	@Query("SELECT MAX(SUBSTRING(loading_id , 3 , length(loading_id))) FROM Loading_point where loading_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select l from Loading_point l where l.business_unit = :bunit")
	List<Loading_point> getLoadingPointThruBU(@Param("bunit") String bunit);
	
	@Query( "select l from Loading_point l where l.business_unit = :bunit and loading_id !=:lpoint")
	List<Loading_point> getLoadingPointThruBUDiff(@Param("bunit") String bunit,@Param("lpoint") String lpoint);
	
	@Query( "select l from Loading_point l where l.loading_id = :loading_id")
	Loading_point getLoadingPointDetails(@Param("loading_id") String loading_id);
	
	@Query("select max(substring(loading_no , 8, length(loading_no))) from Loading_point where loading_no like %:code% and company_id =:company ")
	String getLoadingPointSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_loading_advice pl where pl.modified_type != 'DELETED' and pl.load_point=:code")
	Boolean checkLoadingPointUsage(@Param("code") String code);
}
