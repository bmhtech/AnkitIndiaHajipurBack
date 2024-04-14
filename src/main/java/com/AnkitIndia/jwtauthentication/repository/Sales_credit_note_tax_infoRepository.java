package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_tax_info;

public interface Sales_credit_note_tax_infoRepository extends JpaRepository<Sales_credit_note_tax_info, Long>{

	@Query( "select s from Sales_credit_note_tax_info s where s.modified_type = 'INSERTED' and s.creditnoteid =:creditnoteid ")
	Sales_credit_note_tax_info getSales_credit_note_tax_info(@Param("creditnoteid") String creditnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_tax_info w SET w.modified_type =:mstatus WHERE w.creditnoteid = :creditnoteid")
    int updateSales_credit_note_tax_info(@Param("creditnoteid") String creditnoteid,@Param("mstatus") String mstatus);
}
