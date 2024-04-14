package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_musterroll_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_tax_info;

public interface Stk_transfer_pur_invRepository extends JpaRepository<Stk_transfer_pur_inv, Long>{
	
	@Query("select COUNT(id) from Stk_transfer_pur_inv")
	String countId();
	
	@Query("select COUNT(id) from Stk_transfer_pur_inv where stk_trans_pur_inv_date=:tdate and business_unit =:bunit")
	String getSTPISequenceId(@Param("tdate") String tdate,@Param("bunit") String bunit);

	@Query("select s from Stk_transfer_pur_inv s where s.modified_type = 'INSERTED' and s.company_id =:company and s.fin_year =:finyear ORDER BY s.stk_trans_pur_inv_id DESC ")
	List<Stk_transfer_pur_inv> getStkTranPurInvs(@Param("company") String company,@Param("finyear") String finyear);
	
	@Query( "select a from Stk_transfer_pur_inv_item_dtls a where a.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_pur_inv_item_dtls> stkPurInvItemRetriveList(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Query( "select a from Stk_transfer_pur_inv_musterroll_dtls a where a.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_pur_inv_musterroll_dtls> stkPurInvMusterRollRetriveList(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Query( "select a from Stk_transfer_pur_inv_docs a where a.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_pur_inv_docs> stkPurInvDocsRetriveList(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Query( "select a from Stk_transfer_pur_inv_tax_info a where a.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and a.modified_type ='INSERTED' ")
	Stk_transfer_pur_inv_tax_info stkPurInvTaxInfoRetriveList(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Query( "select a from Stk_transfer_pur_inv_bu_dtls a where a.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and a.modified_type ='INSERTED' ")
	Stk_transfer_pur_inv_bu_dtls stkPurInvBuRetriveList(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_item_dtls v SET v.modified_type ='UPDATED' WHERE v.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and v.modified_type = 'INSERTED'")
    int updateStkTransferPurInvItemDetails(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_musterroll_dtls v SET v.modified_type ='UPDATED' WHERE v.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and v.modified_type = 'INSERTED'")
    int updateStkTransferPurInvMusterrollDetails(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_tax_info v SET v.modified_type ='UPDATED' WHERE v.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and v.modified_type = 'INSERTED'")
    int stkTransferPurInvTaxUpdate(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_bu_dtls v SET v.modified_type ='UPDATED' WHERE v.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and v.modified_type = 'INSERTED'")
    int stkTransferPurInvBuUpdate(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_pur_inv_docs v SET v.modified_type ='UPDATED' WHERE v.stk_trans_pur_inv_id =:stk_trans_pur_inv_id and v.modified_type = 'INSERTED'")
    int stkTransferPurInvDocsUpdate(@Param("stk_trans_pur_inv_id") String stk_trans_pur_inv_id);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Stk_transfer_grn v SET v.stk_pur_inv_status =0 WHERE v.stk_grn_id =:refid and v.modified_type = 'INSERTED' ")
    int grnPurchaseStatusUpdate(@Param("refid") String refid);

	
}
