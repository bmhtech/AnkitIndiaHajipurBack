package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_trans_dtls;

public interface Sales_credit_note_trans_dtlsRepository extends JpaRepository<Sales_credit_note_trans_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_trans_dtls w SET w.modified_type =:mstatus WHERE w.creditnoteid = :creditnoteid")
    int updateSales_credit_note_trans_dtls(@Param("creditnoteid") String creditnoteid,@Param("mstatus") String mstatus);
	
	@Query( "select s from Sales_credit_note_trans_dtls s where s.modified_type = 'INSERTED' and s.creditnoteid =:creditnoteid ")
	List<Sales_credit_note_trans_dtls> getSales_credit_note_trans_dtls(@Param("creditnoteid") String creditnoteid);

}
