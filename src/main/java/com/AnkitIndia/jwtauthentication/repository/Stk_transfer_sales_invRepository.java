package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_tax_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_trans_dtls;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_bu_dtlsDTO;

public interface Stk_transfer_sales_invRepository extends JpaRepository<Stk_transfer_sales_inv, Long>{
	
	@Query("select COUNT(id) from Stk_transfer_sales_inv")
	String countId();
	
	@Query("select COUNT(id) from Stk_transfer_sales_inv where stk_sales_inv_date=:cdate and business_unit=:bunit")
	String getSTSISequenceId(@Param("cdate") String cdate,@Param("bunit") String bunit);
	
	@Query("select s from Stk_transfer_sales_inv s where s.modified_type = 'INSERTED' and s.company_id =:company and s.fin_year =:finyear ORDER BY s.stk_sales_inv_id DESC ")
	List<Stk_transfer_sales_inv> findAllStkTransSInv(@Param("company") String company,@Param("finyear") String finyear);

	@Query( "select a from Stk_transfer_sales_inv_item_dtls a where a.stk_sales_inv_id =:stk_sales_inv_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_sales_inv_item_dtls> getStk_transfer_invoice_item_details(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Query( "select a from Stk_transfer_sales_inv_trans_dtls a where a.stk_sales_inv_id =:stk_sales_inv_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_sales_inv_trans_dtls> getStk_transfer_invoice_trans_details(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Query( "select a from Stk_transfer_sales_inv_bu_dtls a where a.stk_sales_inv_id =:stk_sales_inv_id and a.modified_type ='INSERTED' ")
	Stk_transfer_sales_inv_bu_dtls getStkTransSalesInvBUDtls(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Query( "select a from Stk_transfer_sales_inv_docs a where a.stk_sales_inv_id =:stk_sales_inv_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_sales_inv_docs> getStkTransSalesInvDocs(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Query( "select a from Stk_transfer_sales_inv_shipment_dtls a where a.stk_sales_inv_id =:stk_sales_inv_id and a.modified_type ='INSERTED' ")
	Stk_transfer_sales_inv_shipment_dtls getStkTransShipDtls(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Query( "select a from Stk_transfer_sales_inv_tax_info a where a.stk_sales_inv_id =:stk_sales_inv_id and a.modified_type ='INSERTED' ")
	Stk_transfer_sales_inv_tax_info getStkTransSalesInvTaxInfo(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_sales_inv_item_dtls w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_transfer_sales_inv_item_dtlsUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_sales_inv_docs w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_transfer_sales_inv_docsUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_sales_inv_tax_info w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_transfer_sales_inv_tax_infoUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_sales_inv_bu_dtls w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_transfer_sales_inv_bu_dtlsUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_sales_inv_trans_dtls w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_transfer_sales_inv_trans_dtlsUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_sales_inv_shipment_dtls w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_transfer_sales_inv_shipment_dtlsUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan w SET w.challan_status ='NA',w.saleinvoice_status ='NO',w.sales_inv_id ='0' WHERE w.sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stk_Transfer_ChallanUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.stk_trans_inv_status ='No' WHERE w.advice_id =:advice_id")
    int updateStockInvLoadingStatus(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stocksaleitem_groupwise_hsnsumm w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stocksaleitem_groupwise_hsnsummUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stocksaleitem_groupwise_summ w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stocksaleitem_groupwise_summUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stocksaleitem_groupwise_taxsumm w SET w.modified_type ='DELETED' WHERE w.stk_sales_inv_id = :stk_sales_inv_id and w.modified_type = 'INSERTED'")
    int stocksaleitem_groupwise_taxsummUpdate(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	@Query("select s from Stocksaleitem_groupwise_hsnsumm s where s.modified_type = 'INSERTED' and s.stk_sales_inv_id = :stk_sales_inv_id")
	 List<Stocksaleitem_groupwise_hsnsumm> gethsn(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
	
	@Query("select s from Stocksaleitem_groupwise_taxsumm s where s.modified_type = 'INSERTED' and s.stk_sales_inv_id = :stk_sales_inv_id")
	 List<Stocksaleitem_groupwise_taxsumm> gettax(@Param("stk_sales_inv_id") String stk_sales_inv_id);
	
}
