package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Company_master;
import com.AnkitIndia.jwtauthentication.model.TransportationChgsMatrixMaster;

public interface TransportationChgsMatrixMasterRepository extends JpaRepository<TransportationChgsMatrixMaster, Long>{
	
	@Query("select max(substring(tcm_code , 8, length(tcm_code))) from TransportationChgsMatrixMaster where tcm_code like %:code% and company_id =:company ")
	String getTCMSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query("SELECT MAX(SUBSTRING(tcm_id , 4 , length(tcm_id))) FROM TransportationChgsMatrixMaster where tcm_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select t from TransportationChgsMatrixMaster t where t.id =:id")
	TransportationChgsMatrixMaster findTCMDetails(@Param("id") Long id);
	
	@Query( "select t from TransportationChgsMatrixMaster t where t.tcm_active = :sts and t.modified_type = 'INSERTED' ")
	List<TransportationChgsMatrixMaster> getTransChgNameCode(@Param("sts") Boolean sts);
	
	@Query("select tcm_id,tcm_description from TransportationChgsMatrixMaster where modified_type = 'INSERTED'")
	List<TransportationChgsMatrixMaster> getTransportationCMNCList();
	
	@Query( "select t from Transportation_chgs_matrix_details t where t.tcm_id = :t_id and t.modified_type = 'INSERTED' ORDER BY t.trans_charge_code")
	List<TransportationChgsMatrixMaster> transChrgsMatRetriveList(@Param("t_id") String t_id);
	
//	@Query("select t from TransportationChgsMatrixMaster t where t.modified_type = 'INSERTED'")
//	TransportationChgsMatrixMaster getTransportationList();
	
	@Query( "select t from TransportationChgsMatrixMaster t where t.modified_type = 'INSERTED' and t.tcm_active=1")
	TransportationChgsMatrixMaster findactivematrix();
	
	@Query( "select t from TransportationChgsMatrixMaster t where t.modified_type = 'INSERTED' and t.tcm_active=1 and t.trans_mode='Sales Order'")
	TransportationChgsMatrixMaster findactivematrixsale();
	
	@Query( "select t from TransportationChgsMatrixMaster t where t.modified_type = 'INSERTED' and t.tcm_active=1 and t.trans_mode='Purchase Order'")
	TransportationChgsMatrixMaster findactivematrixpurchase();
	
	@Query(value="SELECT * FROM transportation_chgs_matrix_details t WHERE t.modified_type='INSERTED' AND t.transportation_from=:transfrom AND t.transportation_to=:transto AND t.transporter LIKE %:trans_id% AND t.tcm_id=:tcm_id ", nativeQuery = true)
	List<Map<String,Object>> getTansChargeCode(@Param("trans_id") String trans_id,@Param("transfrom") String transfrom,@Param("transto") String transto,@Param("tcm_id") String tcm_id);
}
