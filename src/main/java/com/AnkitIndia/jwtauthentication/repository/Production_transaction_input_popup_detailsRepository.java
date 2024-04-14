package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_details;
import com.AnkitIndia.jwtauthentication.model.Production_transaction_input_popup_details;

public interface Production_transaction_input_popup_detailsRepository extends JpaRepository<Production_transaction_input_popup_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_transaction_input_popup_details b SET b.modified_type ='UPDATED' WHERE b.prod_trans_id = :prod_trans_id")
    int updateProduction_transaction_input_popup_details(@Param("prod_trans_id") String prod_trans_id);
	
	@Query( "select b from Production_transaction_input_popup_details b where b.prod_popupid =:prod_popupid and b.modified_type ='INSERTED'")
	List<Production_transaction_input_popup_details> getinputpopupdetails(@Param("prod_popupid") String prod_popupid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Production_transaction_input_popup_details w SET w.modified_type ='DELETED' WHERE w.prod_trans_id = :prod_trans_id")
    int production_transaction_input_popup_detailsUpdate(@Param("prod_trans_id") String prod_trans_id);
	
	
}
