package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bom_input_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_details;

public interface Production_transaction_input_detailsRepository extends JpaRepository<Production_transaction_input_details, Long>{
	
	@Query( "select b from Production_transaction_input_details b where b.prod_trans_id =:prod_trans_id and b.modified_type ='INSERTED' ORDER BY b.sl_no")
	List<Production_transaction_input_details> getProdTranInputDetails(@Param("prod_trans_id") String prod_trans_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_transaction_input_details b SET b.modified_type ='UPDATED' WHERE b.prod_trans_id = :prod_trans_id")
    int updateProduction_transaction_input_details(@Param("prod_trans_id") String prod_trans_id);
	
	@Query( "select b from Production_transaction_input_details b where b.prod_trans_id =:prod_trans_id  and item=:item and b.modified_type ='INSERTED'")
	Production_transaction_input_details getpopupDetails(@Param("prod_trans_id") String prod_trans_id,@Param("item") String item);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_transaction_input_details w SET w.modified_type ='DELETED' WHERE w.prod_trans_id = :prod_trans_id")
    int production_transaction_input_detailsUpdate(@Param("prod_trans_id") String prod_trans_id);
}
