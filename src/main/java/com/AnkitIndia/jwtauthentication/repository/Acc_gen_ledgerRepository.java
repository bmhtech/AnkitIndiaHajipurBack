package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_gen_ledger_master;
import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_gen_ledger_masterDTO;


public interface Acc_gen_ledgerRepository extends JpaRepository<Acc_gen_ledger_master, Long> {
	@Query("select COUNT(id) from Acc_gen_ledger_master")
	String countId();
	
	@Query( "select l from Acc_gen_ledger_master l where l.galedger_active = :sts and  l.modified_type != 'DELETED' ")
	List<Acc_gen_ledger_master> getAccLedgerList(@Param("sts") boolean sts);
}