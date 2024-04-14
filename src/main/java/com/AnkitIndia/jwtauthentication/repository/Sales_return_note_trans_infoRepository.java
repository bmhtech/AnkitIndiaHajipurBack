package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_trans_info;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_trans_infoDTO;

public interface Sales_return_note_trans_infoRepository extends JpaRepository<Sales_return_note_trans_info, Long>{
	
	@Query( "select c from Sales_return_note_trans_info c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid")
	Sales_return_note_trans_info getSalesReturnNoteTI(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_trans_info w SET w.modified_type =:mstatus WHERE w.salesreturnnoteid = :salesreturnnoteid")
    int updateSales_return_note_trans_info(@Param("salesreturnnoteid") String salesreturnnoteid,@Param("mstatus") String mstatus);

}
