package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bin_master;

public interface BinMasterRepository extends JpaRepository<Bin_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(bin_id , 3 , length(bin_id))) FROM Bin_master where bin_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select b from Bin_master b where b.bin_active = :sts and b.modified_type != 'DELETED' ")
	List<Bin_master> getBinNameCode(@Param("sts") Boolean sts);
	
	@Query( "select b from Bin_master b where b.bin_active = :sts and b.warehouse_code =:wcode and b.modified_type != 'DELETED' ")
	List<Bin_master> getBinDescByWarehouse(@Param("sts") Boolean sts,@Param("wcode") String wcode);
	
	@Query("select bin_code,bin_description from Bin_master")
	List<Bin_master> getBinNCList();
	
	@Query( "select b from Bin_master b where b.bin_code =:bcode and b.modified_type != 'DELETED' ")
	Bin_master getBinDesc(@Param("bcode") String bcode);
	
	@Query("select max(substring(bin_code , 8, length(bin_code))) from Bin_master where bin_code like %:code% and company_id =:company ")
	String getBinSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("SELECT DISTINCT b.warehouse_code FROM Bin_master b where b.warehouse_code =:warehouse_code and b.modified_type != 'DELETED'")
    Optional<String> getBinDtlsThruWH(@Param("warehouse_code") String warehouse_code);
	
	
	@Query("select b from Bin_master b where  b.modified_type = 'INSERTED'")
	List<Bin_master> getbinlist();
	
	@Query("select b from Bin_master b where  b.modified_type = 'INSERTED' and b.bin_type=:bin_type ")
	List<Bin_master> getbinlistbygroup(@Param("bin_type") String bin_type);
	
	@Query("select b from Bin_master b where  b.modified_type = 'INSERTED' and b.bin_type=:bin_type and b.fin_year=:finyear")
	List<Bin_master> getbinlistbygroupbyyear(@Param("bin_type") String bin_type,@Param("finyear") String finyear);
	
	 @Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Bin_master pl where pl.modified_type = 'INSERTED' and  pl.bin_id=:bin_id and pl.bin_type=:bin_type and pl.fin_year=:finyear ")
	 Boolean checkexistbin(@Param("bin_type") String bin_type,@Param("bin_id") String bin_id,@Param("finyear") String finyear);
	
}
